package com.example.test1

import kotlinx.serialization.Serializable

@Serializable
data class Reviews(
    val review_id: Int,
    val item_id: Int,
    val user_id: Int,
    val text: String,
    val date: String
)
