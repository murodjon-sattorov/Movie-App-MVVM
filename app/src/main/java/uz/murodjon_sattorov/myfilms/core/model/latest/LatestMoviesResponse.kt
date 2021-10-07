package uz.murodjon_sattorov.myfilms.core.model.latest

data class LatestMoviesResponse(
    val adult: Boolean, // false
    val backdrop_path: Any, // null
    val belongs_to_collection: Any, // null
    val budget: Int, // 0
    val genres: List<Any>,
    val homepage: String,
    val id: Int, // 881775
    val imdb_id: String, // tt3731400
    val original_language: String, // en
    val original_title: String, // 樹海熟女狩り
    val overview: String, // After embezzling money from her company, Keiko and her boyfriend, Yamagata, flee to the sea of trees in her hometown. However, a mysterious incident awaits them there. A mysterious woman seduces Yamagata and a woman's screams come from the sea of trees. Behind it all lurks an abominable incident from the past...
    val popularity: Double, // 0.0
    val poster_path: Any, // null
    val production_companies: List<Any>,
    val production_countries: List<Any>,
    val release_date: String,
    val revenue: Int, // 0
    val runtime: Int, // 0
    val spoken_languages: List<Any>,
    val status: String, // Released
    val tagline: String,
    val title: String, // 樹海熟女狩り
    val video: Boolean, // false
    val vote_average: Double, // 0.0
    val vote_count: Int // 0
)