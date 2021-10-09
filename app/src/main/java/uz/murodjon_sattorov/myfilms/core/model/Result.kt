package uz.murodjon_sattorov.myfilms.core.model

import java.io.Serializable

data class Result(
    val adult: Boolean, // false
    val backdrop_path: String, // /8Y43POKjjKDGI9MH89NW0NAzzp8.jpg
    val genre_ids: List<Int>,
    val id: Int, // 550988
    val original_language: String, // en
    val original_title: String, // Free Guy
    val overview: String, // A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.
    val popularity: Double, // 8808.702
    val poster_path: String, // /xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg
    val release_date: String, // 2021-08-11
    val title: String, // Free Guy
    val video: Boolean, // false
    val vote_average: Float, // 7.9
    val vote_count: Int // 2162
): Serializable