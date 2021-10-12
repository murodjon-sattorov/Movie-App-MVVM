package uz.murodjon_sattorov.myfilms.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import uz.murodjon_sattorov.myfilms.core.adapter.home.HomeAdapter
import uz.murodjon_sattorov.myfilms.core.adapter.home.HomePopularAdapter
import uz.murodjon_sattorov.myfilms.databinding.FragmentHomeBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/6/2021
 * @project My Films
 */
class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var adapter = HomeAdapter()
    private var popularAdapter = HomePopularAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()
        loadData()
    }

    private fun setListeners() {
        binding.refreshLayout.setOnRefreshListener(this)
        binding.homeList.adapter = adapter
        binding.homeList.layoutManager = LinearLayoutManager(requireContext())
        binding.homePopularMovies.adapter = popularAdapter
        binding.homePopularMovies.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun setObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.refreshLayout.isRefreshing = it!!
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.topRatedMovies.observe(viewLifecycleOwner, {
            val data = it
            data?.title = "Top Rated"
            adapter.addData(data!!)
        })

        viewModel.popularMovies.observe(viewLifecycleOwner, {
            val data = it
            data?.title = "Popular"
            popularAdapter.addData(data!!)
        })

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, {
            val data = it
            data?.title = "Now Playing"
            adapter.addData(data!!)
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, {
            val data = it
            data?.title = "Upcoming"
            adapter.addData(data!!)
        })
    }

    private fun loadData() {
        viewModel.getPopular()
        viewModel.getNowPlaying()
        viewModel.getTopRated()
        viewModel.getUpcoming()
    }

    override fun onRefresh() {
        adapter.clearData()
        popularAdapter.clearData()
        loadData()
    }

}