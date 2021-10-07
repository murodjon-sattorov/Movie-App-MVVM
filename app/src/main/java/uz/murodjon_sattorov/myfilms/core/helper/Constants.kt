package uz.murodjon_sattorov.myfilms.core.helper

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/6/2021
 * @project My Films
 */

const val baseApiUrl = "https://api.themoviedb.org/3/"
const val apiKey = "43dd72d8d79dcbce00c80be4a7c4a962"

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
private const val IMAGE_SIZE_W500 = "w500"
private const val IMAGE_SIZE_W342 = "w342"
private const val IMAGE_SIZE_W185 = "w185"
private const val IMAGE_SIZE_W154 = "w154"

const val BASE_IMAGE_HIGH = BASE_IMAGE_URL + IMAGE_SIZE_W500
const val BASE_IMAGE_LARGE = BASE_IMAGE_URL + IMAGE_SIZE_W342
const val BASE_IMAGE_MEDIUM = BASE_IMAGE_URL + IMAGE_SIZE_W185
const val BASE_IMAGE_SMALL = BASE_IMAGE_URL + IMAGE_SIZE_W154