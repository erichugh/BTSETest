package com.hughwu.btsetest.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hughwu.btsetest.R
import com.hughwu.btsetest.adapter.MarketListAdapter
import com.hughwu.btsetest.databinding.FragmentFuturesBinding
import com.hughwu.btsetest.viewmodel.MarketViewModel

class FuturesFragment: BaseViewBindingFragment<FragmentFuturesBinding>(R.layout.fragment_futures) {
    private val viewModel: MarketViewModel by activityViewModels()
    private val marketListAdapter: MarketListAdapter = MarketListAdapter()
    override fun initBinding(view: View): FragmentFuturesBinding = FragmentFuturesBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVM()
        binding.rvFutures.apply {
            adapter = marketListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeVM(){
        viewModel.liveDataMerger.observe(viewLifecycleOwner, Observer {
            marketListAdapter.updateList(it.filter { displayData ->  displayData.future == true }.toMutableList())
        })
    }

}