package com.example.movie_compose.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Parcelize
@Serializable
data class MovieUIData(
  @SerialName(value = "name")
  val name: String,
  @SerialName(value = "url")
  val url: String,
) : Parcelable
