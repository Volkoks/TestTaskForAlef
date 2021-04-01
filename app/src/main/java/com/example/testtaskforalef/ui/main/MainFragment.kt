package com.example.testtaskforalef.ui.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskforalef.App
import com.example.testtaskforalef.R
import com.example.testtaskforalef.data.state.AppState
import com.example.testtaskforalef.databinding.MainFragmentBinding
import com.example.testtaskforalef.ui.adapters.ItemClickListener
import com.example.testtaskforalef.ui.adapters.MainAdapter
import com.example.testtaskforalef.ui.fullimage.FullImageFragment
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
    private var binding: MainFragmentBinding? = null
    private var adapter: MainAdapter? = null

    private val itemListener: ItemClickListener<String> = object : ItemClickListener<String> {
        override fun onClick(data: String) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, FullImageFragment.newInstance(data))
                ?.addToBackStack("fullIamge")?.commit()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        viewModel.subscribeLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()

        binding?.mainFragmentRlRefresh?.setOnRefreshListener {
            viewModel.getData()
            stopRefreshingAnimate()
        }

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Succes -> {
                binding?.mainFragmentPbLoading?.visibility = ViewGroup.GONE
                val data = appState.dataImage
                if (adapter == null) {
                    adapter = MainAdapter(itemListener)
                    binding?.mainFragmentRvListImage?.adapter = adapter
                    data.let {
                        adapter?.dataList = data
                    }
                } else {
                    data.let {
                        adapter?.dataList = data
                    }
                }
            }
            is AppState.Loading -> {
                if (appState.progress == 1) binding?.mainFragmentPbLoading?.visibility =
                    ViewGroup.VISIBLE
            }
            is AppState.Error -> {
                val error = appState.error
                binding?.mainFragmentPbLoading?.visibility =
                    ViewGroup.GONE
                Toast.makeText(activity, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun stopRefreshingAnimate() {
        if (binding?.mainFragmentRlRefresh?.isRefreshing == true) {
            binding?.mainFragmentRlRefresh?.isRefreshing = false
        }
    }

    override fun onPause() {
        super.onPause()
        adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}