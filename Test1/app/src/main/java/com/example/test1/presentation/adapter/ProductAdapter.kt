package com.example.test1.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test1.R
import com.example.test1.databinding.ProductItemBinding
import com.example.test1.data.model.Product

class ProductAdapter(
    private val productList: List<Product>,
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ProductItemBinding.bind(item)
        fun bind(product: Product) = with(binding) {
            Glide.with(itemView.context)
                .load(product.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(productImage)
            productPrice.text = product.price
            productDescription.text = product.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}