package com.example.test1.domain.network

import com.example.test1.data.model.Product
import retrofit2.http.GET

interface RestApi {
    @GET("product")
    suspend fun loadItems(): List<Product>
}