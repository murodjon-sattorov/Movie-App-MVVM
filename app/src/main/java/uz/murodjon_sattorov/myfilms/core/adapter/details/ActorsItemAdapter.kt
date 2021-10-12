package uz.murodjon_sattorov.myfilms.core.adapter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.murodjon_sattorov.myfilms.R
import uz.murodjon_sattorov.myfilms.core.helper.BASE_IMAGE_HIGH
import uz.murodjon_sattorov.myfilms.core.helper.GlideApp
import uz.murodjon_sattorov.myfilms.core.model.Actor
import uz.murodjon_sattorov.myfilms.core.model.Result
import uz.murodjon_sattorov.myfilms.databinding.ActorItemBinding
import uz.murodjon_sattorov.myfilms.shadowview.RenderScriptBlur
import uz.murodjon_sattorov.myfilms.shadowview.RenderScriptProvider

/**
 * Created by <a href="mailto: sattorovmurodjon43@gmail.com">Murodjon Sattorov</a>
 *
 * @author Murodjon
 * @date 10/11/2021
 * @project My Films
 */
class ActorsItemAdapter : RecyclerView.Adapter<ActorsItemAdapter.ViewHolder>() {

    private var data = ArrayList<Actor>()
    fun addData(result: List<Actor>) {
        data.clear()
        data.addAll(result)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ActorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(result: Actor) {
            binding.actorName.text = result.name

            val renderScriptProvider = RenderScriptProvider(binding.shadowView.context)
            binding.actorImage.clipToOutline = true
            binding.shadowView.blurScript = RenderScriptBlur(renderScriptProvider)
            GlideApp.with(binding.actorImage).load(BASE_IMAGE_HIGH + result.profile_path)
                .into(binding.actorImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActorItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
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