package com.example.tmdbclone.presentation.details.seeAll

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentSeeAllBinding
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.details.seeAll.adapter.SeeAllAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>(FragmentSeeAllBinding::inflate) {

    private val viewModel: SeeAllViewModel by viewModels()
    private val args: SeeAllFragmentArgs by navArgs()

    private val adapter by lazy {
        viewModel.getData()
        SeeAllAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun started() {
        (activity as MainActivity).hideToolBar()

        binding.tvToolBarTitle.text = args.title ?: ""
        setupViews()
    }

    override fun listeners() {
        with(binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            adapter.onItemClickListener = {
                findNavController().navigate(
                    SeeAllFragmentDirections.actionGlobalMovieDetailFragment(
                        it.title!!,
                        it.id!!
                    )
                )
            }
        }

    }

    private fun setupViews() {
        with(binding) {
            rvAll.adapter = adapter
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.dataState.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

}