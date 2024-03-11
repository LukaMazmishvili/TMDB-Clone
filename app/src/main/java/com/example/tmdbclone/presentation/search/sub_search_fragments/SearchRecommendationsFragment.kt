package com.example.tmdbclone.presentation.search.sub_search_fragments

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.data.remote.model.SearchSimilarModelDto
import com.example.tmdbclone.databinding.FragmentSearchRecommendationsBinding
import com.example.tmdbclone.presentation.search.SearchViewModel
import com.example.tmdbclone.presentation.search.SimilarSearchesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchRecommendationsFragment :
    BaseFragment<FragmentSearchRecommendationsBinding>(FragmentSearchRecommendationsBinding::inflate) {

    private val viewModel: SearchViewModel by activityViewModels()

    private val args: SearchRecommendationsFragmentArgs by navArgs()

    private val adapterSimilarSearches by lazy {
        SimilarSearchesAdapter()
    }

    override fun started() {
        binding.etTitle.setText(args.query)

        setupViews()
        search()

    }

    override fun listeners() {
        with(binding) {

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            adapterSimilarSearches.onItemClickListener = { item ->
                viewModel.getSearchedData(item.title ?: item.name!!)
            }

        }

    }

    private fun setupViews() {
        binding.rvSimilarSearches.adapter = adapterSimilarSearches
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getSimilarSearches(binding.etTitle.text.toString())
                viewModel.similarSearchesState.collect { list ->
                    adapterSimilarSearches.submitList(list)
                }
            }
        }
    }

    private fun search() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()

                viewModel.getSimilarSearches(query)
            }

        })
    }

}