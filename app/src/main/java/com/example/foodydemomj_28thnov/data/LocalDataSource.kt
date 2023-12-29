package com.example.foodydemomj_28thnov.data

import com.example.foodydemomj_28thnov.data.database.RecipesDAO
import com.example.foodydemomj_28thnov.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDAO: RecipesDAO) {

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDAO.insertRecipes(recipesEntity)
    }
      fun readDataBase() : Flow<List<RecipesEntity>> {
         return recipesDAO.readRecipes()
     }
}