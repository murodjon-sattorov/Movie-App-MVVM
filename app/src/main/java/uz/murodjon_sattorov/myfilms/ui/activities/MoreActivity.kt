package uz.murodjon_sattorov.myfilms.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import timber.log.Timber
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.core.adapter.more.MoreAdapter
import uz.murodjon_sattorov.myfilms.databinding.ActivityMoreBinding
import uz.murodjon_sattorov.myfilms.ui.activities.vm.MovieListViewModel

class MoreActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    MoreAdapter.OnMovieLoadListener {

    private lateinit var moreBinding: ActivityMoreBinding

    private val viewModel: MovieListViewModel by viewModels()
    private var categoryId: String = ""

    private var adapter = MoreAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moreBinding = ActivityMoreBinding.inflate(layoutInflater)
        setContentView(moreBinding.root)

        categoryId = intent.getStringExtra("categoryId").toString()

        val toolbar = moreBinding.toolbar
        toolbar.title = categoryId
        setSupportActionBar(toolbar)
        Log.d("TTTT", "onCreate: $intent")


        setLayout()
        setObserver()
        loadData()
    }

    private fun setLayout() {
        moreBinding.swipeRefreshLayout.setOnRefreshListener(this)
        moreBinding.movieList.adapter = adapter
        moreBinding.movieList.layoutManager = GridLayoutManager(this, 2)
        adapter.onMovieLoad = this
    }

    private fun setObserver() {
        viewModel.errorMessage.observe(this, {
            Timber.i("errors", it)
        })
        viewModel.isLoading.observe(this, {
            moreBinding.swipeRefreshLayout.isRefreshing = it!!
        })
        viewModel.loadMovies.observe(this, {
            val data = it!!
            adapter.addData(data.results)
        })
    }

    private fun loadData() {
        viewModel.getMovieList(categoryId)
    }

    override fun onRefresh() {
        adapter.clearData()
        loadData()
    }

    override fun onLoadNextPage() {
        viewModel.getMovieList(categoryId)
    }
}