package uz.murodjon_sattorov.myfilms.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.core.helper.BASE_IMAGE_HIGH
import uz.murodjon_sattorov.myfilms.core.helper.GlideApp
import uz.murodjon_sattorov.myfilms.core.model.Result
import uz.murodjon_sattorov.myfilms.databinding.ItemPopularMovieHomeBinding
import uz.murodjon_sattorov.myfilms.shadowview.RenderScriptBlur
import uz.murodjon_sattorov.myfilms.shadowview.RenderScriptProvider

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/7/2021
 * @project My Films
 */
class HomePopularMovieListAdapter: RecyclerView.Adapter<HomePopularMovieListAdapter.ViewHolder>() {

    private var data = ArrayList<Result>()
    fun addData(result: List<Result>) {
        data.clear()
        data.addAll(result)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemPopularMovieHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Result) {

            val renderScriptProvider = RenderScriptProvider(binding.shadowView.context)

            binding.homeMovieImage.clipToOutline = true
            binding.shadowView.blurScript = RenderScriptBlur(renderScriptProvider)
            GlideApp.with(binding.homeMovieImage).load(BASE_IMAGE_HIGH + result.backdrop_path)
                .into(binding.homeMovieImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPopularMovieHomeBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_popular_movie_home, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}