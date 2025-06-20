package com.example.movie_compose.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
@Serializable
data class Movie(
  var page: Int = 0,
  @SerialName(value = "name")
  val nameField: String,
  @SerialName(value = "url") val url: String,
) : Parcelable {

  val name: String
    get() = nameField.replaceFirstChar { it.uppercase() }

  val imageUrl: String
    get() = String()
}
