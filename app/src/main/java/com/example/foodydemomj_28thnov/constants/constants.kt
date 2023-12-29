package com.example.foodydemomj_28thnov.constants

import android.widget.TextView
import androidx.databinding.BindingAdapter

class constants {

    companion object {

        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY =  "c370a04e35cf49ba837b25c9d6f9df37"

        //Api query keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILLING_INGREDIENTS = "fillIngredients"

        // Room database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"


    }

}