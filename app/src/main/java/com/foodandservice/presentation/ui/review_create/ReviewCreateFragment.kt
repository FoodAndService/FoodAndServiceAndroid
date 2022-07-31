package com.foodandservice.presentation.ui.review_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentReviewCreateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewCreateFragment : Fragment() {
    private lateinit var binding: FragmentReviewCreateBinding
    private val viewModel by viewModels<ReviewCreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_review_create, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.createReviewState.collect { state ->
                when (state) {
                    is ReviewCreateState.Success -> {
                        TODO("On success")
                    }
                    is ReviewCreateState.Error -> {
                        TODO("Show error")
                    }
                    is ReviewCreateState.Empty -> {}
                }
            }
        }

        binding.btnSendReview.setOnClickListener {

        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ratingBarReview.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, _ ->
                if (rating < 1.0f)
                    ratingBar.rating = 1.0f
            }
    }
}