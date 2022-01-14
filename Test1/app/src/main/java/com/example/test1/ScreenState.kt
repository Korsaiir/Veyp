package com.example.test1

sealed class ScreenState {
    data class DataLoaded(val products: List<Product>) : ScreenState()
    object Loading : ScreenState()
    data class Error(val error: String) : ScreenState()
}
