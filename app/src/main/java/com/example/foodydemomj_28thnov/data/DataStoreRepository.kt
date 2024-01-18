package com.example.foodydemomj_28thnov.data

import android.content.Context
import android.health.connect.datatypes.MealType
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_DIET_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.PREFERENCES_DIET_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.example.foodydemomj_28thnov.constants.constants.Companion.PREFERENCES_MEAL_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.example.foodydemomj_28thnov.constants.constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectedMealType = preferencesKey<String>(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeID = preferencesKey<Int>(PREFERENCES_MEAL_TYPE_ID)

        val selectedDietType = preferencesKey<String>(PREFERENCES_DIET_TYPE)
        val selectedSDietTypeID = preferencesKey<Int>(PREFERENCES_DIET_TYPE_ID)
    }


    private val dataStore: DataStore<Preferences> = context.createDataStore(PREFERENCES_NAME)


    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeID: Int
    ) {
        dataStore.edit {
            it[PreferenceKeys.selectedMealType] = mealType
            it[PreferenceKeys.selectedMealTypeID] = mealTypeId
            it[PreferenceKeys.selectedDietType] = dietType
            it[PreferenceKeys.selectedSDietTypeID] = dietTypeID
        }
    }

    val readMealAndDietType: Flow<MealAndDietTypes> = dataStore.data

        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map {
            val selectedMealType = it[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = it[PreferenceKeys.selectedMealTypeID] ?: 0
            val selectedDietType = it[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = it[PreferenceKeys.selectedSDietTypeID] ?: 0

            MealAndDietTypes(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

}

data class MealAndDietTypes(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)