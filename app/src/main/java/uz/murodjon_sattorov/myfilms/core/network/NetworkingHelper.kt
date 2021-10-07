package uz.murodjon_sattorov.myfilms.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.murodjon_sattorov.myfilms.core.helper.baseApiUrl

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/7/2021
 * @project My Films
 */
class NetworkingHelper private constructor(){

    private var retrofit: Retrofit? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    companion object {
        private var networkingHelper: NetworkingHelper? = null
        fun getInstance(): NetworkingHelper {
            if (networkingHelper == null) {
                networkingHelper = NetworkingHelper()
            }
            return networkingHelper!!
        }
    }

    private fun getClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .build()

        return client
    }

    private fun loggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Timber.i("MyFilms %s", message)
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return interceptor
    }

    fun getMovieApi(): MovieApi {
        return retrofit!!.create(MovieApi::class.java)
    }

}