package com.hughwu.btsetest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hughwu.btsetest.R
import com.hughwu.btsetest.databinding.FragmentABinding
import com.hughwu.btsetest.viewmodel.MarketViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : BaseViewBindingFragment<FragmentABinding>(R.layout.fragment_a) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var currentTabIndex = 0

    private val viewModel: MarketViewModel by activityViewModels()
    override fun initBinding(view: View): FragmentABinding = FragmentABinding.bind(view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentNavHost = childFragmentManager.findFragmentById(R.id.nav_hostA)
        val mNavController = currentNavHost?.findNavController()

        //initCurrentTab
        mNavController?.navigate(R.id.spotFragment)
        currentTabIndex = 0
        setTabBG()

        viewModel.getMarketListAPI()
        binding.btnSpot.setOnClickListener {
            if(currentTabIndex != 0) {
                mNavController?.navigate(R.id.spotFragment)
                currentTabIndex = 0
                setTabBG()
            }
        }
        binding.btnFutures.setOnClickListener {
            if(currentTabIndex != 1) {
                mNavController?.navigate(R.id.futuresFragment)
                currentTabIndex = 1
                setTabBG()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.attachWebSocket()
    }

    override fun onPause() {
        super.onPause()
        viewModel.detachWebSocket()
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setTabBG(){
        when(currentTabIndex){
            0->{
                binding.btnSpot.setBackgroundResource(R.drawable.tab_selected)
                binding.btnFutures.setBackgroundResource(R.drawable.tab_normal)
            }
            1->{
                binding.btnSpot.setBackgroundResource(R.drawable.tab_normal)
                binding.btnFutures.setBackgroundResource(R.drawable.tab_selected)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentA.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentA().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}