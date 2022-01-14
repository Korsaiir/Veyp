package com.example.test1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.test1.databinding.ProductItemBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    val productList = ArrayList<Product>()

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

    fun addProduct(product: Product){
        productList.add(product)
        notifyDataSetChanged()
    }

    fun clear(){
        productList.clear()
        notifyDataSetChanged()
    }

}