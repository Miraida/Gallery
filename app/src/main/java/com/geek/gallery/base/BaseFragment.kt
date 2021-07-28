package com.geek.gallery.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var ui: VB
    private lateinit var launcher: ActivityResultLauncher<String>

    protected abstract fun bind(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = bind()
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()
    }

    private fun permissionResult() {
        launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) init()
            else checkPermission()
        }
    }

    private fun checkPermission() {
        launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    protected abstract fun init()
}