package com.example.testtaskforalef.ui.fullimage

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testtaskforalef.App
import com.example.testtaskforalef.R
import com.example.testtaskforalef.databinding.FullImageFragmentBinding
import javax.inject.Inject


class FullImageFragment : Fragment(R.layout.full_image_fragment) {
    val TAG_IMG_URL = "com.example.testtaskforalef.ui.fullimage.IMG_URL"

    companion object {
        fun newInstance(dataImg: String) = FullImageFragment().apply {
            arguments = Bundle().apply {
                putString(TAG_IMG_URL, dataImg)
            }
        }
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FullImageViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FullImageViewModel::class.java)
    }
    private var binding: FullImageFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = FullImageFragmentBinding.bind(view)
        val urlImg = arguments?.getString(TAG_IMG_URL)
        binding?.fullImageFragmentIvFullImage?.let {
            Glide.with(view).load(urlImg)
                .listener(object : RequestListener<Drawable> {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding?.fullImageFragmentIvFullImage!!.background =
                            resources.getDrawable(R.drawable.image_warning, null)
                        Toast.makeText(
                            activity,
                            "An error occurred while loading",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                }).into(it)
        }
    }


}