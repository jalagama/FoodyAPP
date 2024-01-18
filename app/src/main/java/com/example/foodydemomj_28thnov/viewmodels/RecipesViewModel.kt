package com.example.foodydemomj_28thnov.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

     fun applyQueries() : HashMap<String,String> {

        val queries: HashMap<String,String> = HashMap()

        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_TYPE] =  DEFAULT_MEAL_TYPE
        queries[QUERY_DIET] =  DEFAULT_DIET_TYPE
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILLING_INGREDIENTS] = "true"

        return queries
    }
}