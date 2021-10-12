package uz.murodjon_sattorov.myfilms.ui.activities.vm

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
import uz.murodjon_sattorov.myfilms.core.model.details.MovieDetailsResponse
import uz.murodjon_sattorov.myfilms.core.network.NetworkingHelper

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/11/2021
 * @project My Films
 */
class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var networkingHelper = NetworkingHelper.getInstance()

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val disposables = CompositeDisposable()

    private val _movieDetails = MutableLiveData<MovieDetailsResponse?>()
    val movieDetails: LiveData<MovieDetailsResponse?> = _movieDetails
    fun getDetails(movieId: Int) {
        val disposable = networkingHelper.getMovieApi().getDetails(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieDetailsResponse>() {
                override fun onSuccess(t: MovieDetailsResponse?) {
                    t?.let {
                        _movieDetails.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private val _actorsDetails = MutableLiveData<MovieListResponse?>()
    val actorsDetails: LiveData<MovieListResponse?> = _actorsDetails
    fun getActors(movieId: Int) {
        val disposable = networkingHelper.getMovieApi().getActors(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    t?.let {
                        _actorsDetails.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
                    _errorMessage.postValue(e?.message)
                }

            })
        disposables.add(disposable)
    }


    private val _similarMovies = MutableLiveData<MovieListResponse?>()
    val similarMovies: LiveData<MovieListResponse?> = _similarMovies
    fun getSimilar(movieId: Int) {
        val disposable = networkingHelper.getMovieApi().getSimilar(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<MovieListResponse>() {
                override fun onSuccess(t: MovieListResponse?) {
                    t?.let {
                        _similarMovies.postValue(it)
                    }
                }

                override fun onError(e: Throwable?) {
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