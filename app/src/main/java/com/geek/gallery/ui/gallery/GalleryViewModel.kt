package com.geek.gallery.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.gallery.model.GalleryImages

class GalleryViewModel : ViewModel() {

    var count = MutableLiveData<Int>()

    private var list = ArrayList<GalleryImages>()
    private var counter = 0

    fun addSelectedImage(file: GalleryImages) {
        if (file.isMatched) {
            count.value = ++counter
            list.add(file)
        } else {
            count.value = --counter
            list.remove(file)
        }
    }

    fun getListOfImages(): ArrayList<String> {
        val images = ArrayList<String>()

        for (i in list) {
            images.add(i.filePath)
        }
        return images
    }

}