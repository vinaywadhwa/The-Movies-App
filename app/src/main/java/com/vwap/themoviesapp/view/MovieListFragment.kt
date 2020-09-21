package com.vwap.themoviesapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.vstechlab.easyfonts.EasyFonts
import com.vwap.themoviesapp.R
import com.vwap.themoviesapp.databinding.MovieListLayoutBinding
import com.vwap.themoviesapp.model.MovieModel
import com.vwap.themoviesapp.utils.setCustomFont
import com.vwap.themoviesapp.view_model.MoviesViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import timber.log.Timber

class MovieListFragment : Fragment() {

    private lateinit var binding: MovieListLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setActivityTitle()
        setupEmptyView()
        loadData()
        setupDelayedTransitionAnimation(view)
    }

    private fun setupEmptyView() {
        //in a larger project, this should be done with help
        // of a reusable component (say EmptyViewHelper)
        binding.emptyText.setCustomFont(EasyFonts.walkwayBold(binding.emptyText.context))
        binding.groupEmptyView.visibility = View.VISIBLE
    }

    private fun setActivityTitle() {
        activity?.title = getString(R.string.app_name)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.activity_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                val viewModel: MoviesViewModel by viewModels()
                viewModel.triggerRefresh()
                Toast.makeText(activity, "refreshing data..", Toast.LENGTH_SHORT).show()
            }
            R.id.about -> {
                try {
                    val url = getString(R.string.about_url)
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }catch (ignored:Exception){} // in case device can't handle the ACTION_VIEW intent
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setupDelayedTransitionAnimation(view: View) {
        postponeEnterTransition()
        (view.parent as? ViewGroup)?.doOnPreDraw {
            //to resume the delayed transition animation when all views have been measured
            // (i.e. Recycler view has been laid out, in our case )
            Handler().postDelayed({ startPostponedEnterTransition() }, 200)
        }
    }

    private fun loadData() {
        binding.loading.show() // show loading indicator

        val viewModel: MoviesViewModel by viewModels()
        viewModel.moviesList.observe(viewLifecycleOwner, {
            Timber.d("Observer in List Fragment - we've received an update!")
            setDataInRecyclerView(it)
            binding.loading.hide() // hide loading indicator
            if (it.isNotEmpty()) binding.groupEmptyView.visibility = View.GONE // hide empty view
        })
    }

    private fun setDataInRecyclerView(it: List<MovieModel>?) {
        binding.movieList.apply {
            if (adapter == null) { // first time initialisation of recyclerview
                layoutManager = GridAutoFitLayoutManager(
                    binding.root.context,
                    150
                )
                itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))

                adapter =
                    MoviesAdapter(layoutInflater, object : OnMovieClickListener {
                        override fun onMovieClick(
                            movieModel: MovieModel?,
                            image: View
                        ) {
                            showDetail(movieModel, image)
                        }
                    }).apply {
                        submitList(it)

                        //restores scroll position, only available in alpha version of RecyclerView as on 19Sep20
                        stateRestorationPolicy =
                            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                    }

                Timber.d("Setting adapter")

            } else { // recyclerview already initialised, just update the data
                val moviesAdapter = adapter as MoviesAdapter
                moviesAdapter.submitList(it)
                Timber.d("updating data")
            }

        }
    }

    /**
     *
     */
    private fun showDetail(movieModel: MovieModel?, image: View) {
        movieModel?.let {
            //get transition names from views, pass it on as extras paired with respective views
            val imageTransitionName: String = ViewCompat.getTransitionName(image).toString()
            val navController = NavHostFragment.findNavController(this)

            // The following if condition is a workaround to avoid the crash which
            // happens when clicking two items with two fingers simultaneously in the Grid.
            // See : https://stackoverflow.com/questions/51060762/illegalargumentexception-navigation-destination-xxx-is-unknown-to-this-navcontr

            // Navigation Components library ought to handle this itself someday.
            // Until then, we have to do their dirty laundry.. here goes
            if (navController.currentDestination?.id == R.id.movieListFragment) {
                navController.navigate(
                    MovieListFragmentDirections.showMovieDetail(movieModel, movieModel.title),
                    FragmentNavigatorExtras(
                        image to imageTransitionName
                    )
                )
            }
        }
    }
}