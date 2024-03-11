package com.example.tmdbclone.presentation.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tmdbclone.presentation.details.seeAll.SeeAllFragment
import com.example.tmdbclone.presentation.search.sub_search_fragments.pager_fragments.AllPageFragment

class SearchPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var count = listOf<Fragment?>()

    val tempList = mutableListOf<Fragment>()

    override fun getItemCount(): Int {

        for (each in this.count) {
            if (each != null && !tempList.contains(each)) {
                tempList.add(each)
            }
        }
        if (tempList.size == 3) {
            tempList[0] = AllPageFragment()
        }
        return count.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position in 0..itemCount) {
            true -> count[position]!!
            else -> {
                AllPageFragment()
            }
        }
    }
}
