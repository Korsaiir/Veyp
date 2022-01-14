package com.example.test1.network

import com.example.test1.Product
import retrofit2.http.GET

interface RestApi {
    @GET("product")
    suspend fun loadItems(): List<Product>
}