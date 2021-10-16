package uz.murodjon_sattorov.myfilms.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.core.adapter.details.ActorsItemAdapter
import uz.murodjon_sattorov.myfilms.core.adapter.home.HomePopularAdapter
import uz.murodjon_sattorov.myfilms.core.helper.BASE_IMAGE_HIGH
import uz.murodjon_sattorov.myfilms.core.helper.GlideApp
import uz.murodjon_sattorov.myfilms.core.helper.apiKey
import uz.murodjon_sattorov.myfilms.core.helper.baseApiUrl
import uz.murodjon_sattorov.myfilms.core.model.Result
import uz.murodjon_sattorov.myfilms.databinding.ActivityDetailsBinding
import uz.murodjon_sattorov.myfilms.ui.activities.vm.DetailViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityDetailsBinding

    private val viewModel: DetailViewModel by viewModels()

    private var adapter = ActorsItemAdapter()
    private var similarAdapter = HomePopularAdapter()
    private var youtubeKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = ActivityDetailsBinding.inflate(layoutInflater)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(detailsBinding.root)

        val intent: Result = intent.getSerializableExtra("result") as Result

        setListener()
        setObservable(intent)
        loadData(intent.id)

        detailsBinding.loadVideo.setOnClickListener {
            val intent = Intent(this, VideoTrailerActivity::class.java)
            intent.putExtra("videoId", youtubeKey)
            startActivity(intent)
        }

    }

    private fun setListener() {
        detailsBinding.actorsList.adapter = adapter
        detailsBinding.actorsList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        detailsBinding.similarList.adapter = similarAdapter
        detailsBinding.similarList.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservable(intent: Result) {
        viewModel.movieDetails.observe(this, {
            val data = it
            GlideApp.with(this).load(BASE_IMAGE_HIGH + intent.backdrop_path)
                .into(detailsBinding.detailsMovieImage)

            GlideApp.with(this).load(BASE_IMAGE_HIGH + intent.poster_path)
                .into(detailsBinding.posterImage)

            detailsBinding.movieTitle.text = data?.title
            detailsBinding.movieRating.text = data?.vote_average.toString()
            detailsBinding.movieRatingCount.text = data?.vote_count.toString()
            detailsBinding.movieDescription.text = data?.overview

            loadOverviewText()
        })
        viewModel.actorsDetails.observe(this, {
            val data = it
            adapter.addData(data!!.cast)
        })
        viewModel.similarMovies.observe(this, {
            val data = it
            data?.title = "Similar movies"
            similarAdapter.addData(data!!)
        })
        viewModel.loadVideoTrailer.observe(this, {
            val data = it
            youtubeKey = data!!.results[0].key
            Log.d("TBT", "setObservable: $data")
        })


    }

    private fun loadData(movieId: Int) {
        viewModel.getDetails(movieId)
        viewModel.getActors(movieId)
        viewModel.getSimilar(movieId)
        viewModel.getVideoTrailer(movieId)
    }

    private fun loadOverviewText() {
        detailsBinding.overviewLoad.setOnClickListener {
            detailsBinding.movieDescription.layoutParams.height =
                WindowManager.LayoutParams.WRAP_CONTENT
            detailsBinding.overviewLoad.visibility = GONE
        }
    }


}