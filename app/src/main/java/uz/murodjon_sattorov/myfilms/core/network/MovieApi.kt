package uz.murodjon_sattorov.myfilms.core.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import uz.murodjon_sattorov.myfilms.core.model.MovieListResponse
import uz.murodjon_sattorov.myfilms.core.model.latest.LatestMoviesResponse

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/6/2021
 * @project My Films
 */
interface MovieApi {

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>


    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>


    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>


    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Single<MovieListResponse>


}