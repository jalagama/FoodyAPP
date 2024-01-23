package com.example.foodydemomj_28thnov.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodydemomj_28thnov.constants.constants
import com.example.foodydemomj_28thnov.constants.constants.Companion.API_KEY
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_DIET_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_RECIPES_NUMBER
import com.example.foodydemomj_28thnov.constants.constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.foodydemomj_28thnov.constants.constants.Companion.QUERY_API_KEY
import com.example.foodydemomj_28thnov.constants.constants.Companion.QUERY_DIET
import com.example.foodydemomj_28thnov.constants.constants.Companion.QUERY_FILLING_INGREDIENTS
import com.example.foodydemomj_28thnov.constants.constants.Companion.QUERY_NUMBER
import com.example.foodydemomj_28thnov.constants.constants.Companion.QUERY_TYPE
import com.example.foodydemomj_28thnov.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
   private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE


    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect {
                values ->
                mealType = values.selectedMealType
                dietType = values.selectedDietType
            }
        }

        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILLING_INGREDIENTS] = "true"

        return queries
    }

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int)  =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType,mealTypeId, dietType,dietTypeId)
        }

}