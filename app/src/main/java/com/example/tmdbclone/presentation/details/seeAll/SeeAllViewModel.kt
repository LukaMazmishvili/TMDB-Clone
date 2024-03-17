package com.example.tmdbclone.presentation.details.seeAll

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Headings
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.usecase.GetPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(private val getPagingUseCase: GetPagingUseCase) :
    BaseViewModel() {

    private val _dataState = MutableStateFlow<PagingData<MoviesDTO.MovieModelDto>>(
        PagingData.empty()
    )
    val dataState = _dataState.asStateFlow()

    fun getData(heading: Headings) {
        viewModelScope.launch {
            getPagingUseCase.invoke(heading).collect {
                _dataState.value = it
            }
        }
    }
}