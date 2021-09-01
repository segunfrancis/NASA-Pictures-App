package com.project.segunfrancis.nasapicturesapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import coil.ImageLoader
import com.project.segunfrancis.nasapicturesapp.databinding.FragmentPictureDetailsBinding
import com.project.segunfrancis.nasapicturesapp.model.NasaItem
import com.project.segunfrancis.nasapicturesapp.util.*
import com.project.segunfrancis.nasapicturesapp.util.AppConstants.ON_BOARDING_FRAGMENT_TAG
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PictureDetailsFragment : Fragment() {

    private var _binding: FragmentPictureDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PictureDetailsViewModel by viewModels()
    private val args: PictureDetailsFragmentArgs by navArgs()
    private var currentPage: Int = 0
    private var size: Int = 0

    @Inject
    lateinit var imageLoader: ImageLoader

    private val detailsPagerAdapter: DetailsPagerAdapter by lazy { DetailsPagerAdapter(imageLoader) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hasUserSeenOnBoarding.observe(viewLifecycleOwner) {
            if (!it)  // Not seen onBoarding screen
                OnBoardingFragment().show(childFragmentManager, ON_BOARDING_FRAGMENT_TAG)
        }

        loadData()
        setupObservers()
        setupClickListeners()

        binding.photoViewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun loadData() {
        if (args.origin == Origin.PICTURE_LIST_FRAGMENT) {
            viewModel.getPictureList()
        } else {
            viewModel.getAllBookmarks()
        }
    }

    private fun setupClickListeners() = with(binding) {
        previousButton.setOnClickListener {
            photoViewPager.setCurrentItem(currentPage - 1, true)
        }
        nextButton.setOnClickListener {
            photoViewPager.setCurrentItem(currentPage + 1, true)
        }
    }

    private fun setupObservers() {
        viewModel.pictureList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> handleSuccess(result.data)
                is Result.Error -> handleError(result.error)
            }
        }

        // Restore state of pager items position
        viewModel.adapterPosition.observe(viewLifecycleOwner, EventObserver {
            binding.photoViewPager.setCurrentItem(it, false)
        })
    }

    private fun handleSuccess(data: List<NasaItem>) {
        detailsPagerAdapter.submitList(data)
        size += data.size - 1
        binding.photoViewPager.apply {
            adapter = detailsPagerAdapter
            setCurrentItem(args.position, false)
            setPageTransformer(ZoomOutPageTransformer())
        }
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(
            requireContext(),
            error.localizedMessage,
            Toast.LENGTH_LONG
        )
            .show()
        Timber.e(error)
    }

    private val pageChangeCallback: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            currentPage = position
            when (position) {
                0 -> { // first item
                    binding.previousButton.makeGone()
                }
                size -> { // last item
                    binding.nextButton.makeGone()
                }
                else -> {
                    binding.previousButton.makeVisible()
                    binding.nextButton.makeVisible()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.setAdapterPosition(binding.photoViewPager.currentItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}