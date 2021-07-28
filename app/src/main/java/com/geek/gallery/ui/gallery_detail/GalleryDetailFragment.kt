package com.geek.gallery.ui.gallery_detail

import com.geek.gallery.R
import com.geek.gallery.base.BaseFragment
import com.geek.gallery.databinding.GalleryDetailFragmentBinding
import com.geek.gallery.ui.gallery.GalleryFragment
import java.util.*

class GalleryDetailFragment : BaseFragment<GalleryDetailFragmentBinding>() {

    override fun bind(): GalleryDetailFragmentBinding {
        return GalleryDetailFragmentBinding.inflate(layoutInflater)
    }

    override fun init() {
        val adapter = GalleryDetailAdapter(getList())
        ui.rvGalleryDetail.adapter = adapter

        setupListener()
    }

    private fun setupListener() {
        ui.backIv.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, GalleryFragment())
                .commit()
        }
    }

    private fun getList(): ArrayList<String> {
        if (arguments?.isEmpty != true) {
            return arguments?.getStringArrayList("IMAGES") as ArrayList<String>
        }
        return ArrayList<String>()
    }
}