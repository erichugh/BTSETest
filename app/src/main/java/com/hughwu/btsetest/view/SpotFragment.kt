package com.hughwu.btsetest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hughwu.btsetest.R
import com.hughwu.btsetest.adapter.MarketListAdapter
import com.hughwu.btsetest.databinding.FragmentSpotBinding
import com.hughwu.btsetest.viewmodel.MarketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SpotFragment: BaseViewBindingFragment<FragmentSpotBinding>(R.layout.fragment_spot) {

    private val viewModel: MarketViewModel by activityViewModels()
    private val marketListAdapter: MarketListAdapter = MarketListAdapter()

    override fun initBinding(view: View): FragmentSpotBinding = FragmentSpotBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVM()
        binding.rvSpot.apply {
            adapter = marketListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeVM(){
        viewModel.liveDataMerger.observe(viewLifecycleOwner, Observer {
            marketListAdapter.updateList(it.filter { displayData ->  displayData.future == false }.toMutableList())
        })
    }
}