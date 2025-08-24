package com.example.movie_compose.data.network.model.res

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PopularMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

@Parcelize
data class Movie(
   @SerializedName("adult") val adult: Boolean,
   @SerializedName("backdrop_path") val backdropPath: String?,
   @SerializedName("genre_ids") val genreIds: List<Int>,
   @SerializedName("id") val id: Int,
   @SerializedName("original_language") val originalLanguage: String,
   @SerializedName("original_title") val originalTitle: String,
   @SerializedName("overview") val overview: String,
   @SerializedName("popularity") val popularity: Double,
   @SerializedName("poster_path") val posterPath: String?,
   @SerializedName("release_date") val releaseDate: String,
   @SerializedName("title") val title: String,
   @SerializedName("video") val video: Boolean,
   @SerializedName("vote_average") val voteAverage: Double,
   @SerializedName("vote_count") val voteCount: Int,
) : Parcelable {

   fun getPosterUrl(): String? {
       return posterPath?.let { 
           "https://image.tmdb.org/t/p/w500$it" 
       }
   }

   fun getBackdropUrl(): String? {
       return backdropPath?.let { 
           "https://image.tmdb.org/t/p/w1280$it" 
       }
   }

   fun getFormattedRating(): String {
       return String.format("%.1f", voteAverage)
   }

   fun getReleaseYear(): String? {
       return if (releaseDate.isNotEmpty()) {
           releaseDate.substring(0, 4)
       } else null
   }
}