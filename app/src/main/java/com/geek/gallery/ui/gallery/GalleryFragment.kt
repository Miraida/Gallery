package com.geek.gallery.ui.gallery

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.geek.gallery.R
import com.geek.gallery.`object`.Constant
import com.geek.gallery.base.BaseFragment
import com.geek.gallery.databinding.GalleryFragmentBinding
import com.geek.gallery.model.GalleryImages
import com.geek.gallery.ui.Repository
import com.geek.gallery.ui.gallery_detail.GalleryDetailFragment
import com.geek.gallery.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class GalleryFragment : BaseFragment<GalleryFragmentBinding>() {

    private lateinit var viewModel: GalleryViewModel
    private lateinit var snackbar: Snackbar

    override fun bind(): GalleryFragmentBinding {
        return GalleryFragmentBinding.inflate(layoutInflater)
    }

    override fun init() {
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        val adapter = GalleryAdapter(
            Repository.getImagePaths(requireActivity() as MainActivity),
            object : GalleryAdapter.OnItemClickListener {
                override fun onItemClick(file: GalleryImages) {
                    viewModel.addSelectedImage(file)
                    snackbar.show()
                }
            })
        ui.rvGallery.adapter = adapter

        initSnackBar()
    }

    private fun initSnackBar() {
        snackbar = Snackbar.make(ui.root, "", Snackbar.LENGTH_INDEFINITE)

        val view = layoutInflater.inflate(R.layout.snackbar,ui.root,false)
        val layout: Snackbar.SnackbarLayout = snackbar.view as Snackbar.SnackbarLayout

        context?.let { ContextCompat.getColor(it,R.color.transparent) }?.let {
            layout.setBackgroundColor(it)
        }
        layout.setPadding(0,0,0,0)

        setupListener(view)

        layout.addView(view, 0)
    }

    private fun setupListener(view: View) {
        view.findViewById<Button>(R.id.ready_btn).setOnClickListener {
            snackbar.dismiss()
            parentFragmentManager.beginTransaction().replace(R.id.container,getFragment()).commit()
        }

        viewModel.count.observe(this@GalleryFragment, {
            when (it) {
                0 -> snackbar.dismiss()
                1 -> view.findViewById<TextView>(R.id.counter_tv).text =
                    getString(R.string.selected_image)
                else -> view.findViewById<TextView>(R.id.counter_tv).text =
                    getString(R.string.selected_images, it)
            }
        })
    }

    private fun getFragment(): GalleryDetailFragment {
        val fragment = GalleryDetailFragment()
        val bundle = Bundle()

        bundle.putStringArrayList(Constant.KEY, viewModel.getListOfImages())
        fragment.arguments = bundle

        return fragment
    }

}