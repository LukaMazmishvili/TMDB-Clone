package com.example.tmdbclone.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMsg = MutableSharedFlow<String>()
    val errorMsg = _errorMsg.asSharedFlow()

    private val _networkStatus = MutableStateFlow<Boolean?>(null)
    val networkStatus = _networkStatus.asStateFlow()

    protected fun setErrorMsg(errorMsg: String) {
        viewModelScope.launch {
            _errorMsg.emit(errorMsg)
        }
    }

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }

    fun setNetworkStatus(networkStatus: Boolean?) {
        _networkStatus.value = networkStatus
    }

}