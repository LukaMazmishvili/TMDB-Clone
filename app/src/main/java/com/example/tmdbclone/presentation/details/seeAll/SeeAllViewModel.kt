package com.example.tmdbclone.presentation.details.seeAll

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.data.remote.model.PopularMovieDTO
import com.example.tmdbclone.domain.usecase.GetPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(private val getPagingUseCase: GetPagingUseCase) :
    BaseViewModel() {

    private val _dataState = MutableStateFlow<PagingData<PopularMovieDTO.MovieModelDto>>(
        PagingData.empty()
    )
    val dataState = _dataState.asStateFlow()

    init {
//        getData()
    }

    fun getData() {
        viewModelScope.launch {
            getPagingUseCase.invoke().collect {
                _dataState.value = it
            }
        }
    }
}