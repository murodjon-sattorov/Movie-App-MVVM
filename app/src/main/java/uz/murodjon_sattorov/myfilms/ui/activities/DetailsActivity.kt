package uz.murodjon_sattorov.myfilms.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.view.Window
import android.view.WindowManager
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.core.helper.BASE_IMAGE_HIGH
import uz.murodjon_sattorov.myfilms.core.helper.GlideApp
import uz.murodjon_sattorov.myfilms.core.model.Result
import uz.murodjon_sattorov.myfilms.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = ActivityDetailsBinding.inflate(layoutInflater)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(detailsBinding.root)

        val intent: Result = intent.getSerializableExtra("result") as Result

        GlideApp.with(this).load(BASE_IMAGE_HIGH + intent.backdrop_path)
            .into(detailsBinding.detailsMovieImage)

        GlideApp.with(this).load(BASE_IMAGE_HIGH + intent.poster_path)
            .into(detailsBinding.posterImage)

        detailsBinding.movieTitle.text = intent.title
        detailsBinding.movieRating.text = intent.vote_average.toString()
        detailsBinding.movieRatingCount.text = intent.vote_count.toString()


    }
}