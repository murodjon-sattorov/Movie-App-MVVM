package uz.murodjon_sattorov.myfilms.core.model.details

data class MovieDetailsResponse(
    val adult: Boolean, // false
    val backdrop_path: String, // /8Y43POKjjKDGI9MH89NW0NAzzp8.jpg
    val belongs_to_collection: BelongsToCollection,
    val budget: Int, // 110000000
    val genres: List<Genre>,
    val homepage: String, // https://www.20thcenturystudios.com/movies/free-guy
    val id: Int, // 550988
    val imdb_id: String, // tt6264654
    val original_language: String, // en
    val original_title: String, // Free Guy
    val overview: String, // A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.
    val popularity: Double, // 8075.543
    val poster_path: String, // /xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String, // 2021-08-11
    val revenue: Int, // 324000000
    val runtime: Int, // 115
    val spoken_languages: List<SpokenLanguage>,
    val status: String, // Released
    val tagline: String, // Life's too short to be a background character.
    val title: String, // Free Guy
    val video: Boolean, // false
    val vote_average: Double, // 7.9
    val vote_count: Int // 2420
)