package com.example.hw2k1

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.hw2k1.databinding.ActivityMainBinding
import com.example.hw2k1.extensions.load
import com.example.hw2k1.extensions.random
import com.example.hw2k1.extensions.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val images = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            Glide.with(this)
                .asBitmap().load(binding.editText.text.toString())
                .into(object : CustomTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                    ) {
                        images.add(binding.editText.text.toString())
                        binding.editText.setText("")
                        showToast("Submitted")
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        binding.editText.setText("")
                        showToast("Error")
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                })
        }

        binding.btnRandom.setOnClickListener {
            try {
                binding.imageView.load(images[random(images.size)])
            } catch (e: IllegalArgumentException) {
                showToast("Submit an image")
            }
        }

    }

}