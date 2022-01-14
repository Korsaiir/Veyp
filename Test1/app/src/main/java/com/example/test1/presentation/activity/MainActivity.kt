package com.example.test1.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.test1.R
import com.example.test1.presentation.fragments.EntranceFragment
import com.example.test1.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        navigateToFragment(EntranceFragment.newInstance())
    }
    fun navigateToFragment(fmt: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.place_holder,fmt)
            .addToBackStack(fmt.javaClass.name)
            .commit()
    }
    
}