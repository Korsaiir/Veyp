package com.example.test1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val item_id: Int,
    val name : String,
    val description : String,
    val price: String,
    val category: String,
    val image: String,
    val reviews: List<Reviews>
)
