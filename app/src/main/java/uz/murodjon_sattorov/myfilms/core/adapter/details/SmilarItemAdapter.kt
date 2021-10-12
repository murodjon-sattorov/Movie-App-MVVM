package uz.murodjon_sattorov.myfilms.core.adapter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.core.model.MovieListResponse
import uz.murodjon_sattorov.myfilms.databinding.ItemHomeBinding

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/11/2021
 * @project My Films
 */
class SmilarItemAdapter : RecyclerView.Adapter<SmilarItemAdapter.ViewHolder>() {

    private val data = ArrayList<MovieListResponse>()
    fun addData(d: MovieListResponse) {
        data.add(d)
        notifyItemInserted(data.size)
    }

    inner class ViewHolder(var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        private val adapter = SmilarItemAdapter()
        fun bindData(movieListResponse: MovieListResponse) {
            binding.titleItemList.text = movieListResponse.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
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