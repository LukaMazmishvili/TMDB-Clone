package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.data.remote.model.MoviesDTO
import com.example.tmdbclone.domain.usecase.GetPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val getSearPagingUseCase: GetPagingUseCase) :
    BaseViewModel() {

    private val _dataState = MutableStateFlow<PagingData<MoviesDTO.MovieModelDto>>(
        PagingData.empty()
    )
    val dataState = _dataState.asStateFlow()

    fun getData(query: String) {
        viewModelScope.launch {
            getSearPagingUseCase.getSearchedMovies(query).cachedIn(viewModelScope).collect {
                _dataState.value = it
            }
        }
    }
}