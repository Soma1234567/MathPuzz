package com.soma.mathpuzz.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.soma.mathpuzz.domain.repository.DataStoreOperations
import com.soma.mathpuzz.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore:DataStore<Preferences> by preferencesDataStore(Constants.PREFERENCE_NAME)

class DataStoreOperationsImpl(
    private val context:Context
):DataStoreOperations {
    private object PreferencesKey {
        val levelKey = intPreferencesKey(Constants.LEVEL_KEY)

    }
    val datastore = context.dataStore
    override suspend fun saveLevel(level: Int) {
        datastore.edit {preferences->
            preferences[PreferencesKey.levelKey] = level
        }
    }
    override fun readLevel(): Flow<Int> {
        return datastore.data
            .map { preferences->
                val level = preferences[PreferencesKey.levelKey]?:-1
                level
            }
    }
}