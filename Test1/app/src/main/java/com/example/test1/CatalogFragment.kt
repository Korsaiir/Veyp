package com.example.test1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test1.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private lateinit var binding: FragmentCatalogBinding
    private val adapter = ProductAdapter()
    private val imageIdList = listOf(R.drawable.salt_pink, R.drawable.salt_king)
    private var index = 0

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(context, 2)
            rcView.adapter = adapter
            button4.setOnClickListener {
                if (index > 1) index = 0
                val product = Product(imageIdList[index], " price $index", " descr $index")
                adapter.addProduct(product)
                index++
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatalogBinding.bind(view)
        init()

        binding.favor.setOnClickListener {
            (activity as MainActivity).navigateToFragment(FavoriteFragment.newInstance())
        }
    }

}