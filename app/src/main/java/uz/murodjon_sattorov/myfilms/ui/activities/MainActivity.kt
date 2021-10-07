package uz.murodjon_sattorov.myfilms.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.ismaeldivita.chipnavigation.R
import uz.murodjon_sattorov.myfilms.databinding.ActivityMainBinding
import uz.murodjon_sattorov.myfilms.ui.fragments.*
import uz.murodjon_sattorov.myfilms.ui.fragments.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var chipNavigationBar: ChipNavigationBar
    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        chipNavigationBar = mainBinding.chipNavigationBar
        chipNavigationBar.setItemSelected(R.id.home, true)

        supportFragmentManager.beginTransaction()
            .replace(uz.murodjon_sattorov.myfilms.R.id.container, HomeFragment()).commit()

        chipNavigationBar.setOnItemSelectedListener {
            when (it) {
                uz.murodjon_sattorov.myfilms.R.id.home -> {
                    fragment = HomeFragment()
                }
                uz.murodjon_sattorov.myfilms.R.id.series -> {
                    fragment = SeriesFragment()
                }
                uz.murodjon_sattorov.myfilms.R.id.live_tv -> {
                    fragment = LiveTvFragment()
                }
                uz.murodjon_sattorov.myfilms.R.id.downloads -> {
                    fragment = DownloadsFragment()
                }
                uz.murodjon_sattorov.myfilms.R.id.profile -> {
                    fragment = ProfileFragment()
                }
            }
            if (fragment != null){
                supportFragmentManager.beginTransaction().replace(uz.murodjon_sattorov.myfilms.R.id.container, fragment!!).commit()
            }
        }

    }
}