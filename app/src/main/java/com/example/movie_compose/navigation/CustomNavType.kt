package com.example.movie_compose.navigation

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.example.movie_compose.presentation.model.MovieUIData
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


object CustomNavType {
    val movieUiType = object : NavType<MovieUIData>(isNullableAllowed = false) {
        override fun put(bundle: Bundle, key: String, value: MovieUIData) {
            bundle.putParcelable(key, value)
        }

        override fun get(bundle: Bundle, key: String): MovieUIData? =
            BundleCompat.getParcelable(bundle, key, MovieUIData::class.java)

        override fun parseValue(value: String): MovieUIData {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: MovieUIData): String = Uri.encode(Json.encodeToString(value))
    }
}