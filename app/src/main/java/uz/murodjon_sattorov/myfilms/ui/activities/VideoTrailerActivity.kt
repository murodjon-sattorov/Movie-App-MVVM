package uz.murodjon_sattorov.myfilms.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.databinding.ActivityVideoTrailerBinding

class VideoTrailerActivity : AppCompatActivity() {

    private lateinit var videoTrailerBinding: ActivityVideoTrailerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoTrailerBinding = ActivityVideoTrailerBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(videoTrailerBinding.root)

        lifecycle.addObserver(videoTrailerBinding.loadVideo)

        val videoId = intent.getStringExtra("videoId") as String

        videoTrailerBinding.loadVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

    }
}