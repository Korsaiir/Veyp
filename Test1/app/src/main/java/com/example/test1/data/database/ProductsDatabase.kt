package com.example.test1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test1.data.model.Product

@Database(
    entities = [Product::class],
    version = 1
)

abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}