package com.example.fetch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.fetch.databinding.HomeFragmentBinding
import kotlinx.coroutines.*

class HomeFragment:Fragment() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val hiringViewModel: HiringViewModel by navGraphViewModels(R.id.nav_graph)

    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fetchButton.setOnClickListener {
            coroutineScope.launch {
                temporarilyEnableLoading(4000)
                hiringViewModel.fetchItems()
                lifecycleScope.launch {
                    delay(400)
                    navigateToFetchRewardsListFragment()
                }
            }
        }

    }

    private fun navigateToFetchRewardsListFragment() {
        if (findNavController().currentDestination?.id != R.id.hiring_list) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHiringList())
        }
    }

    private fun temporarilyEnableLoading(delayTime: Long) {
        binding.progressBar.visibility = View.VISIBLE
        binding.fetchButton.isEnabled = false
        lifecycleScope.launch {
            delay(delayTime)
            disableLoading()
        }
    }

    private fun disableLoading() {
        binding.progressBar.visibility = View.GONE
        binding.fetchButton.isEnabled = true
    }
}