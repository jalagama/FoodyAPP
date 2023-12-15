package com.example.foodydemomj_28thnov.data

import com.example.foodydemomj_28thnov.data.network.FoodRecipesApi
import com.example.foodydemomj_28thnov.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {

        return foodRecipesApi.getRecipes(queries)
    }
}