package com.geek.gallery.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geek.gallery.R
import com.geek.gallery.ui.gallery.GalleryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, GalleryFragment())
            .commit()
    }
}