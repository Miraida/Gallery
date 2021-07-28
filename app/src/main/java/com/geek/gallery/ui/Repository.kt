package com.geek.gallery.ui

import android.database.Cursor
import android.os.Environment
import android.provider.MediaStore
import com.geek.gallery.model.GalleryImages
import com.geek.gallery.ui.main.MainActivity

class Repository {
    companion object {
        fun getImagePaths(activity: MainActivity): ArrayList<GalleryImages> {
            val imagePaths = ArrayList<GalleryImages>()
            val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

            if (isSDPresent) {
                val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)

                val orderBy = MediaStore.Images.Media.DEFAULT_SORT_ORDER

                val cursor: Cursor? = activity.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    columns,
                    null,
                    null,
                    orderBy
                )
                val count: Int = cursor?.count ?: 0

                for (i in 0 until count) {
                    cursor?.moveToPosition(i)
                    val dataColumnIndex: Int? = cursor?.getColumnIndex(MediaStore.Images.Media.DATA)
                    if (dataColumnIndex != null) {
                        cursor.getString(dataColumnIndex)?.let {
                            imagePaths.add(GalleryImages(it, false))
                        }
                    }
                }
                cursor?.close()
            }
            return imagePaths
        }
    }
}