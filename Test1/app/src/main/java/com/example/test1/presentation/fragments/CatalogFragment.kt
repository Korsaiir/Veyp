package com.example.test1.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test1.R
import com.example.test1.presentation.ScreenState
import com.example.test1.presentation.adapter.ProductAdapter
import com.example.test1.databinding.FragmentCatalogBinding
import com.example.test1.data.model.Product
import com.example.test1.presentation.viewmodel.CatalogViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private val viewModel by lazy { CatalogViewModel(requireContext(), lifecycleScope) }
    private lateinit var binding: FragmentCatalogBinding

    companion object {
        fun newInstance() = CatalogFragment()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)

        if (savedInstanceState == null) {
            viewModel.loadData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.loadData() }
        binding.buttonRefresh.setOnClickListener { viewModel.loadData() }
        viewModel.screenState.onEach {
            when (it) {
                is ScreenState.DataLoaded -> {
                    setLoading(false)
                    setError(null)
                    setData(it.products)
                }
                is ScreenState.Error -> {
                    setLoading(false)
                    setError(it.error)
                    setData(null)
                }
                is ScreenState.Loading -> {
                    setLoading(true)
                    setError(null)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rcView.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rcView.isVisible
    }

    private fun setData(products: List<Product>?) = with(binding) {
        swipeRefreshLayout.isVisible = products != null
        binding.rcView.layoutManager = GridLayoutManager(context, 2)
        rcView.adapter = ProductAdapter(
            products ?: emptyList()
        )
    }

    private fun setError(message: String?) = with(binding) {
        errorLayout.isVisible = message != null
        tvError.text = message
    }
}
