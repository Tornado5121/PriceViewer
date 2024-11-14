package com.zhadko.priceviewer.data.dataSource.dataStore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val APP_CONFIGURATION_PREFERENCE_NAME = "app_configuration"

val Context.dataStorePreferences by preferencesDataStore(name = APP_CONFIGURATION_PREFERENCE_NAME)
