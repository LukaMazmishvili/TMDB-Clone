package com.example.tmdbclone.common

import android.view.LayoutInflater
import android.view.ViewGroup

typealias inflater<T> = (layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> T
