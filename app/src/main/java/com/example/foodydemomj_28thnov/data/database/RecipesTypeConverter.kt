package com.example.foodydemomj_28thnov.data.database

import androidx.room.TypeConverter
import com.example.foodydemomj_28thnov.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe) : String {

        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipes(data:String) : FoodRecipe {

        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }
}