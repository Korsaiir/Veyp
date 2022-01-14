package com.example.test1.presentation.viewmodel

import android.content.Context
import com.example.test1.R
import com.example.test1.data.database.DatabaseProvider
import com.example.test1.domain.network.NetworkService
import com.example.test1.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class CatalogViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState
    private val vapeDao = DatabaseProvider.provideDatabase(context).productsDao()

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = ScreenState.Loading
                val vape = try {
                    NetworkService(context).loadItems().also {
                        vapeDao.insertAll(it)
                    }
                } catch (ex: IOException){
                    vapeDao.getAll()
                }
                _screenState.value = ScreenState.DataLoaded(vape)
            } catch(ex: Throwable) {
                _screenState.value = ScreenState.Error(context.getString(R.string.error))
            }
        }
    }
}