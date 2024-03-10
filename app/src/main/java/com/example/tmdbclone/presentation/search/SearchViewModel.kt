package com.example.tmdbclone.presentation.search

import androidx.lifecycle.viewModelScope
import com.example.tmdbclone.base.BaseViewModel
import com.example.tmdbclone.common.Resource
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchUseCase: GetSearchUseCase) :
    BaseViewModel() {

    private val _similarSearchesState =
        MutableSharedFlow<List<SearchSimilarModelDto.SimilarSearches>>()
    val similarSearchesState = _similarSearchesState.asSharedFlow()

    fun getSimilarSearches(query: String) {
        viewModelScope.launch {
            getSearchUseCase.getSimilarSearches(query).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideLoading()
                        _similarSearchesState.emit(response.data!!.results)
                    }

                    is Resource.Error -> {
                        hideLoading()
                        setErrorMsg(response.errorMsg)
                    }

                    is Resource.Loading -> showLoading()

                }
            }
        }
    }

}