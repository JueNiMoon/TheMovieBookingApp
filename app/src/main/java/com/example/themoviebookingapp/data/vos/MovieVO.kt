package com.example.themoviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviebookingapp.persistance.typeconverters.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdsTypeConverter::class,
    GenresTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguagesTypeConverter::class,
)
data class MovieVO (
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @SerializedName("belongs_to_collection")
    @ColumnInfo(name = "belongs_to_collection")
    val belongsToCollection : CollectionVO?,

    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    val budget: Double?,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVO>?,

    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    val homepage:String?,

    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    val imdbId:String?,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int=0 ,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overView: String?,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountries: List<ProductionCountriesVO>?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    val revenue: Double?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime:Int?,

    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguagesVO>?,

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String?,

    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    val tagline:String?,


    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int=0,

    @ColumnInfo(name = "type")
    var type : String?

){
    fun getMovieTime() : String{
        var mMovieTime = ""
        runtime?.let {
            val hour = runtime /60
            val min = runtime % 60

            mMovieTime = "${hour}h ${min}m"
        }
        return mMovieTime
    }

    fun getRatingBasedOnFiveStars():Float{
        return voteAverage?.div(2)?.toFloat()?:0.0f
    }

}
const val NOW_PLAYING = "NOW_PLAYING"
const val UP_COMING = "UP_COMING"