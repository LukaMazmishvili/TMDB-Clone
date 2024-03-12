package com.example.tmdbclone.presentation.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllPageFragment

class SearchPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {


    private var fragmentsList = listOf<Fragment>()

    fun updateList(fragmentsList: List<Fragment>) {
        this.fragmentsList = fragmentsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position in 0..itemCount) {
            true -> fragmentsList.toList()[position]
            else -> {
                AllPageFragment()
            }
        }
    }
}
