package com.example.test1.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: ProductsDatabase? = null
    fun provideDatabase(context: Context): ProductsDatabase {
        return db ?: Room.databaseBuilder(
            context.applicationContext,
            ProductsDatabase::class.java, "database"
        ).build().also { db = it }
    }
}