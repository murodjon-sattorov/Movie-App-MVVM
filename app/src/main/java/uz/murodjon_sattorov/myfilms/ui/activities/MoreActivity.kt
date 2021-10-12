package uz.murodjon_sattorov.myfilms.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.databinding.ActivityMoreBinding

class MoreActivity : AppCompatActivity() {

    private lateinit var moreBinding: ActivityMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moreBinding = ActivityMoreBinding.inflate(layoutInflater)
        setContentView(moreBinding.root)
    }
}