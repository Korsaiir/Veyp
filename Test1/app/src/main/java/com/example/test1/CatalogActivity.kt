package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test1.databinding.ActivityCatalogBinding
import kotlinx.android.synthetic.main.recycle_test.*

class CatalogActivity : AppCompatActivity() {
    lateinit var binding: ActivityCatalogBinding
    private val adapter = ProductAdapter()
    private val imageIdList = listOf(R.drawable.salt_pink, R.drawable.salt_king)
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_catalog)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@CatalogActivity, 2)
            rcView.adapter = adapter
            button4.setOnClickListener {
                if (index > 1) index = 0
                val product = Product(imageIdList[index], " price $index", " descr $index")
                adapter.addProduct(product)
                index++
            }
        }
    }
}