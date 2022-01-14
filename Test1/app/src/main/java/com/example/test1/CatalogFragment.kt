package com.example.test1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test1.databinding.FragmentCatalogBinding
import com.example.test1.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private var items: List<Product> = emptyList()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        binding.apply {
            progressBar.visibility = View.GONE
            rcView.layoutManager = GridLayoutManager(context, 2)
            rcView.adapter = ProductAdapter(listOf())
            swipeRefreshLayout.isRefreshing = false
        }
        Snackbar.make(
            requireView(),
            getString(R.string.error),
            Snackbar.LENGTH_SHORT
        ).show()
    }
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)

    private lateinit var binding: FragmentCatalogBinding

    companion object {
        fun newInstance() = CatalogFragment()
    }

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)

        loadItems()

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = true
                loadItems()
                swipeRefreshLayout.isRefreshing = false
            }
            favor.setOnClickListener {
                (activity as MainActivity).navigateToFragment(FavoriteFragment.newInstance())
            }
        }
    }

    @ExperimentalSerializationApi
    private fun loadItems() {
        scope.launch {
            items = NetworkService.loadItems()
            binding.apply {
                rcView.layoutManager = GridLayoutManager(context, 2)
                rcView.adapter = ProductAdapter(items)
                progressBar.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}