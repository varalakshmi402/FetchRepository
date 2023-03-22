package com.example.fetch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetch.databinding.HiringListBinding


class HiringListFragment : Fragment() {
    private val hiringViewModel= HiringViewModel()

    private lateinit var adapter: HiringAdapter

    private lateinit var binding: HiringListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HiringListBinding.inflate(inflater)
        val view = binding.root
        adapter = HiringAdapter { hiringItem ->
            Toast.makeText(requireContext(), "ID: ${hiringItem.id} Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hiringViewModel.fetchRewardsItemList.observe(viewLifecycleOwner, Observer { fetchRewardsItemList ->
            adapter.bindData(fetchRewardsItemList)
            binding.progressBar.visibility = View.GONE
        })

        hiringViewModel.fetchItems()
    }

}