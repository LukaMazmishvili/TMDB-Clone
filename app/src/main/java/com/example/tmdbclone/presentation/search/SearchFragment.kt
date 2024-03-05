package com.example.tmdbclone.presentation.search

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.iterator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tmdbclone.base.BaseFragment
import com.example.tmdbclone.databinding.FragmentSearchBinding
import com.example.tmdbclone.extension.insertTextViews
import com.example.tmdbclone.presentation.MainActivity
import com.example.tmdbclone.presentation.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    View.OnClickListener {

    private val moviesViewModel: MoviesViewModel by activityViewModels()

    private val searchViewModel: SearchViewModel by viewModels()

    private var query = MutableSharedFlow<String>()

    override fun started() {

//        val menuHost: MenuHost = requireActivity()
//
//        menuHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.toolbar_menu, menu)
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                val manager =
//                    requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//                (activity as MainActivity)
//                if (menuItem.itemId == R.id.search) {
//                    val searchView = menuItem.actionView as SearchView
//                    if (query.isNotEmpty()) {
//                        searchView.setQuery(query, false)
//                    }
//                    searchView.setBackgroundColor(requireActivity().getColor(R.color.app_green))
//                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                        override fun onQueryTextSubmit(query: String?): Boolean {
//                            query?.let {
//                                searchViewModel.getSearchSuggestions(it)
//                            }
//                            return true
//                        }
//
//                        override fun onQueryTextChange(newText: String?): Boolean {
//                            newText?.let {
//                                searchViewModel.getSearchSuggestions(newText)
//                            }
//                            return true
//                        }
//
//                    })
////                    searchView.setSearchableInfo(manager.getSearchableInfo())
//                }
//                return true
//            }
//
//        })

    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                moviesViewModel.trendingMoviesState.collect { list ->
                    val listOfStrings = mutableListOf<String>()
                    for (i in list) {
                        listOfStrings.add(i.title ?: i.originalName!!)
                    }
                    binding.root.insertTextViews(listOfStrings)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setToolBarTitle("Search")
    }

    override fun listeners() {

        for (each in binding.root) {
            if (each is TextView) {
                each.setOnClickListener(this)
            }
        }

    }

    override fun onClick(v: View?) {
        if (v is TextView) {
//            query = v.text.toString()
//            started()
        }
    }

}