package com.geek.gallery.ui.gallery_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.gallery.R
import com.geek.gallery.databinding.ItemGalleryRvBinding
import com.geek.gallery.exten.loadImage

class GalleryDetailAdapter(
    private val list: ArrayList<String>,
) :
    RecyclerView.Adapter<GalleryDetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var ui = ItemGalleryRvBinding.bind(itemView)

        fun onBind(file: String) {
            ui.imageIv.loadImage(file)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_rv, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size
}