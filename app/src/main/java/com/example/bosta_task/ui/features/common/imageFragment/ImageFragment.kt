package com.example.bosta_task.ui.features.common.imageFragment

import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MenuItemCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.bosta_task.R
import com.example.bosta_task.databinding.FragmentImageBinding
import com.example.bosta_task.ui.features.core.HelperFunctions

class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private val args: ImageFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)


        return binding.root
    }
    override fun onResume() {
        super.onResume()
        if (HelperFunctions.isInternetConnected(requireContext())) {
            loadImage(args.imageLink)
            shareImage()

        } else {
            HelperFunctions.noInternetConnection(requireContext(), requireActivity())

        }
    }



    private fun loadImage(imageUri: String) {
        Glide.with(this)
            .load(imageUri)
            .into(binding.imageView)
    }
    private fun shareImage() {
        binding.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, args.imageLink)
            }

            startActivity(Intent.createChooser(shareIntent, "Share Image"))
        }
    }




}
