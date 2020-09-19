package com.vwap.themoviesapp.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.vwap.themoviesapp.R
import com.vwap.themoviesapp.databinding.MovieDetailsBinding
import com.vwap.themoviesapp.model.MovieModel

class MovieDetailFragment : Fragment() {
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var binding: MovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding.item = args.model
        setupTransitionAnimation(args.model)
    }

    private fun setupTransitionAnimation(movieModel: MovieModel) {
        ViewCompat.setTransitionName(binding.label, movieModel.labelTransitionName)
        ViewCompat.setTransitionName(binding.image, movieModel.imageTransitionName)
        Handler().postDelayed(
            this::startPostponedEnterTransition,
            50
        ) // TODO: replace this with Coil's image loading listener
    }

}