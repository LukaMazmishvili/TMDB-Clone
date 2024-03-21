package com.example.tmdbclone.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tmdbclone.R
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentNoNetworkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoNetworkFragment :
    BaseFragment<FragmentNoNetworkBinding>(FragmentNoNetworkBinding::inflate) {

    override fun started() {

    }

    override fun listeners() {

    }

}