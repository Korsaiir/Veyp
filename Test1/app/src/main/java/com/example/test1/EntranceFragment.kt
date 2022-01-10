package com.example.test1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test1.MainActivity
import com.example.test1.R
import com.example.test1.databinding.FragmentEntranceBinding

class EntranceFragment : Fragment(R.layout.fragment_entrance) {
    private lateinit var binding: FragmentEntranceBinding

    companion object {
        fun newInstance() = EntranceFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEntranceBinding.bind(view)

        binding.btnEnter.setOnClickListener {
            (activity as MainActivity).navigateToFragment(CatalogFragment.newInstance())
        }
    }
}