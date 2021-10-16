package uz.murodjon_sattorov.myfilms.ui.activities.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.murodjon_sattorov.myfilms.core.helper.*
import uz.murodjon_sattorov.myfilms.core.model.MovieListResponse
import uz.murodjon_sattorov.myfilms.core.network.NetworkingHelper

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/15/2021
 * @project My Films
 */
class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private var networkingHelper = NetworkingHelper.getInstance()

    private val _loadMovies = MutableLiveData<MovieListResponse?>()
    val loadMovies: LiveData<MovieListResponse?> = _loadMovies

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val disposables = CompositeDisposable()

    private var page: Int = 1

    fun getMovieList(categoryId: String) {
        when (categoryId) {
            "Popular" -> {
                getPopular()
            }
            "Now Playing" -> {
                getNowPlaying()
            }
            "Top Rated" -> {
                getTopRated()
            }
            "Upcoming" -> {
                getUpcoming()
            }
        }
    }

    private fun getTopRated() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getTopRated(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    _isLoading.postValue(false)
                    t?.let {
                        _loadMovies.postValue(it)
                        page += 1
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private fun getPopular() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getPopular(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    _isLoading.postValue(false)
                    t?.let {
                        _loadMovies.postValue(it)
                        page += 1
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private fun getNowPlaying() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getNowPlaying(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    _isLoading.postValue(false)
                    t?.let {
                        _loadMovies.postValue(it)
                        page += 1
                    }
                }

                override fun onError(e: Throwable?) {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private fun getUpcoming() {
        _isLoading.postValue(true)

        val disposable = networkingHelper.getMovieApi()
            .getUpcoming(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    _isLoading.postValue(false)
                    t?.let {
                        _loadMovies.postValue(it)
                        page += 1
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
        page = 1
        super.onCleared()
    }

}