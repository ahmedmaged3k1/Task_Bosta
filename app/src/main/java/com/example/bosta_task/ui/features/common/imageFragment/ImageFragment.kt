package com.example.bosta_task.ui.features.common.imageFragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.bosta_task.databinding.FragmentImageBinding
import com.example.bosta_task.ui.features.core.HelperFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private val args: ImageFragmentArgs by navArgs()
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor: Float = 1.0f

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
            scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
            zoom()


        } else {
            HelperFunctions.noInternetConnection(requireContext(), requireActivity())

        }
    }


    private fun loadImage(imageUri: String) {
        Glide.with(this)
            .load(imageUri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    MotionToast.darkToast(
                        requireActivity(),
                        duration = 13000L,
                        position = MotionToast.GRAVITY_BOTTOM,
                        font = ResourcesCompat.getFont(
                            requireContext(),
                            www.sanju.motiontoast.R.font.helvetica_regular
                        ),
                        style = MotionToastStyle.NO_INTERNET,
                        message = "Cannot Load Image , Poor Internet Connection ",
                        title = "Sorry"
                    )
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    binding.shimmerFrameLayout.hideShimmer()
                    binding.shimmerFrameLayout.visibility=View.INVISIBLE
                    binding.imageView.visibility = View.VISIBLE

                    return false
                }
            })
            .into(binding.imageView)
    }

    private fun shareImage() {
        binding.shareButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val uri = downloadImageAndShare()
                    withContext(Dispatchers.Main) {
                        shareImagee(uri)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.d("TAG", "shareImage:${e.message} ")
                }
            }
        }

    }

    private suspend fun downloadImageAndShare(): Uri = withContext(Dispatchers.IO) {
        val bmp = BitmapFactory.decodeStream(URL(args.imageLink).openStream())
        val outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
        val tempFile = File.createTempFile("temp", ".png")
        val fileOutputStream = FileOutputStream(tempFile)
        fileOutputStream.write(outputStream.toByteArray())
        fileOutputStream.close()
        FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            tempFile
        )
    }

    private fun shareImagee(uri: Uri) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.type = "image/png"
        startActivity(Intent.createChooser(shareIntent, "Share Image"))
    }

    private fun zoom() {
        binding.imageView.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f))

            binding.imageView.scaleX = scaleFactor
            binding.imageView.scaleY = scaleFactor

            return true
        }
    }


}
