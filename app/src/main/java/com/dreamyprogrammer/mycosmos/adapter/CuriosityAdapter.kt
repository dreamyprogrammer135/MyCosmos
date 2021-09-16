package com.dreamyprogrammer.mycosmos.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.dreamyprogrammer.mycosmos.R
import com.dreamyprogrammer.mycosmos.data.Curiosity
import com.dreamyprogrammer.mycosmos.data.Photo
import com.dreamyprogrammer.mycosmos.databinding.ItemCuriosityBinding


class CuriosityAdapter : RecyclerView.Adapter<CuriosityAdapter.ViewHolder?>() {

    private lateinit var binding: ItemCuriosityBinding
    private val curiosityList = ArrayList<Photo>()

    var listener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun itemClicked(curiosity: Photo)
    }

    fun addItems(curiosityResponseData: Curiosity) {
        curiosityResponseData.photos?.let {
            curiosityList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun clear() = curiosityList.clear()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCuriosityBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root as View, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(curiosityList[position])
    }

    override fun getItemCount() = curiosityList.size

    inner class ViewHolder(itemView: View, private val listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var photo: Photo

        init {
            listener?.let {
                binding.imagePhotoViewCuriosity.setOnClickListener {
                    listener.itemClicked(
                        photo
                    )
                }
            }
        }

        fun bind(curiosity: Photo) {
            with(binding) {
                curiosity.earthDate?.let {
                    datePhotoCuriosity.text = it
                }

                curiosity.imgSrc?.let {
                    imagePhotoViewCuriosity.load(it) {
                        itemView.context
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.bg_mars)
                    }
                }
                photo = curiosity
            }
        }

    }
}
