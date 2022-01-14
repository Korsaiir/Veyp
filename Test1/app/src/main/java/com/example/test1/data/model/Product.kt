package com.example.test1.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["item_id", "name", "description", "price", "category", "image"])
data class Product(
    @ColumnInfo var item_id: Int,
    @ColumnInfo var name: String,
    @ColumnInfo var description: String,
    @ColumnInfo var price: String,
    @ColumnInfo var category: String,
    @ColumnInfo var image: String,
    @Ignore var reviews: List<Reviews>
)  {
    constructor() : this(0,"","","","","", listOf())
}

