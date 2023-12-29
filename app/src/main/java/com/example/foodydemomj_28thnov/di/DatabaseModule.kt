package com.example.foodydemomj_28thnov.di

import android.content.Context
import androidx.room.Room
import com.example.foodydemomj_28thnov.constants.constants.Companion.DATABASE_NAME
import com.example.foodydemomj_28thnov.data.database.RecipesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule  {

    @Singleton
    @Provides
    fun provideRecipesDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,
        RecipesDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(recipesDataBase: RecipesDataBase) = recipesDataBase.recipesDao()
}