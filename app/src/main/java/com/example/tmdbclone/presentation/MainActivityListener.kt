package com.example.tmdbclone.presentation

interface MainActivityListener {

    fun hideToolBar()
    fun showToolBar()
    fun hideBottomNavigation()
    fun showBottomNavigation()
    fun setToolBarTitle(title: String)

}