package com.example.tmdbclone.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.tmdbclone.R
import com.example.tmdbclone.common.inflater
import com.example.tmdbclone.network.ConnectivityObserver
import com.example.tmdbclone.network.NetworkConnectivityObserver
import kotlinx.coroutines.launch

abstract class BaseFragment<out VB : ViewBinding>(
    private val inflater: inflater<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun started()
    abstract fun listeners()
    protected open fun observer() {}

    protected fun networkObserver(
        networkConnectivityObserver: ConnectivityObserver,
        viewModel: BaseViewModel
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                networkConnectivityObserver.observe().collect { networkStatus ->
                    when (networkStatus) {
                        ConnectivityObserver.Status.Lost, ConnectivityObserver.Status.Unavailable, ConnectivityObserver.Status.Losing -> {
                            viewModel.setNetworkStatus(false)
//                            findNavController().navigate(R.id.noNetworkFragment)
                        }

                        ConnectivityObserver.Status.Available -> {
                            viewModel.setNetworkStatus(true)
                        }

                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.inflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        started()
        listeners()
        observer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}