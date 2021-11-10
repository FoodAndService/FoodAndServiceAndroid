package com.foodandservice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.FoodAndServiceActivity
import com.foodandservice.R
import com.foodandservice.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomBar()

        viewModel.getState().observe(viewLifecycleOwner, {

        })


    }

    private fun showBottomBar(){
        val activity: FoodAndServiceActivity = activity as FoodAndServiceActivity
        activity.bottomBarVisibility(View.VISIBLE)
    }
}