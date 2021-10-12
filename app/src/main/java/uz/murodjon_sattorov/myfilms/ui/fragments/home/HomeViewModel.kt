package uz.murodjon_sattorov.myfilms.ui.fragments.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.murodjon_sattorov.myfilms.core.helper.apiKey
import uz.murodjon_sattorov.myfilms.core.model.MovieListResponse
import uz.murodjon_sattorov.myfilms.core.network.NetworkingHelper

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/7/2021
 * @project My Films
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var networkingHelper = NetworkingHelper.getInstance()

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val disposables = CompositeDisposable()

    private var page: Int = 1

    private val _topRatedMovies = MutableLiveData<MovieListResponse?>()
    val topRatedMovies: LiveData<MovieListResponse?> = _topRatedMovies
    fun getTopRated() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getTopRated(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    page = page.inc()
                    _isLoading.postValue(false)
                    t?.let {
                        _topRatedMovies.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private val _popularMovies = MutableLiveData<MovieListResponse?>()
    val popularMovies: LiveData<MovieListResponse?> = _popularMovies
    fun getPopular() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getPopular(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    page = page.inc()
                    _isLoading.postValue(false)
                    t?.let {
                        _popularMovies.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private val _nowPlayingMovies = MutableLiveData<MovieListResponse?>()
    val nowPlayingMovies: LiveData<MovieListResponse?> = _nowPlayingMovies
    fun getNowPlaying() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getNowPlaying(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    page = page.inc()
                    _isLoading.postValue(false)
                    t?.let {
                        _nowPlayingMovies.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private val _upcomingMovies = MutableLiveData<MovieListResponse?>()
    val upcomingMovies: LiveData<MovieListResponse?> = _upcomingMovies
    fun getUpcoming() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getUpcoming(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    page = page.inc()
                    _isLoading.postValue(false)
                    t?.let {
                        _upcomingMovies.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.dispose()
        disposables.clear()
        super.onCleared()
    }

}