package com.example.foodydemomj_28thnov.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodydemomj_28thnov.constants.constants.Companion.RECIPES_TABLE
import com.example.foodydemomj_28thnov.models.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: FoodRecipe) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}