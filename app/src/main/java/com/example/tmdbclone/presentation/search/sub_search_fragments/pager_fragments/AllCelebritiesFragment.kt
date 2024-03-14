package com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentAllCelebritiesBinding
import com.example.tmdbclone.presentation.search.SearchViewModel

class AllCelebritiesFragment :
    BaseFragment<FragmentAllCelebritiesBinding>(FragmentAllCelebritiesBinding::inflate) {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun started() {

    }

    override fun listeners() {

    }

}