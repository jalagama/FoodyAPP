package com.example.foodydemomj_28thnov.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodydemomj_28thnov.constants.NetworkResult
import com.example.foodydemomj_28thnov.data.Repository
import com.example.foodydemomj_28thnov.data.database.RecipesEntity
import com.example.foodydemomj_28thnov.models.FoodRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel

@Inject constructor(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {

    /** ROOM */

    var readRecipe: LiveData<List<RecipesEntity>>  = repository.local.readDataBase().asLiveData()

    private fun insertRecipes(recipesEntity: RecipesEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.local.insertRecipes(recipesEntity)
    }


    /** Retofit */
    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getReciPesSafeCall(queries)
    }

    suspend fun getReciPesSafeCall(queries: Map<String, String>) {

        recipesResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {

            try {

                val response = repository.remote.getRecipes(queries)

                recipesResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipesResponse.value!!.data

                if (foodRecipe != null) {
                    offlineCacheRecipe(foodRecipe)
                }

            } catch (e: Exception) {

                recipesResponse.value = NetworkResult.Error("Recipes Not found!")
            }

        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection!")
        }
    }

    private fun offlineCacheRecipe(foodRecipe: FoodRecipe) {
        var recipesEntity = RecipesEntity(foodRecipe)
        insertRecipes(recipesEntity)
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {

        when {

            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResult.Error("API key Limited")
            }

            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found!")
            }

            response.isSuccessful -> {

                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }

            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }


    private fun hasInternetConnection(): Boolean {

        val connectivityManger =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManger.activeNetwork ?: return false
        val capabilities = connectivityManger.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}