package com.geek.gallery.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.gallery.R
import com.geek.gallery.databinding.ItemGalleryRvBinding
import com.geek.gallery.exten.gone
import com.geek.gallery.exten.loadImage
import com.geek.gallery.exten.visible
import com.geek.gallery.model.GalleryImages

class GalleryAdapter(
    private val list: ArrayList<GalleryImages>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var ui = ItemGalleryRvBinding.bind(itemView)

        fun onBind(file: GalleryImages) {
            ui.imageIv.loadImage(file.filePath)

            if (file.isMatched) ui.selectedImgBg.visible()
            else ui.selectedImgBg.gone()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnLongClickListener {
            list[position].isMatched = !list[position].isMatched

            listener.onItemClick(
                list[position]
            )
            notifyDataSetChanged()
            true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_rv, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemClick(file: GalleryImages)
    }
}