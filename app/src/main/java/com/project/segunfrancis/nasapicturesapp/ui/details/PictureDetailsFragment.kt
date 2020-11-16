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
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.project.segunfrancis.nasapicturesapp.databinding.FragmentPictureDetailsBinding
import com.project.segunfrancis.nasapicturesapp.ui.NasaViewModel
import com.project.segunfrancis.nasapicturesapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PictureDetailsFragment : Fragment() {

    private var _binding: FragmentPictureDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NasaViewModel by viewModels()
    private val args: PictureDetailsFragmentArgs by navArgs()
    private var currentPage: Int = 0
    private var size: Int = 0

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsPagerAdapter = DetailsPagerAdapter(imageLoader)
        viewModel.pictureList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    detailsPagerAdapter.submitList(result.data)
                    size += result.data.size - 1
                    binding.photoViewPager.apply {
                        adapter = detailsPagerAdapter
                        setCurrentItem(args.position, false)
                    }
                    Timber.d(result.data.toString())
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        result.error.localizedMessage,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    FirebaseCrashlytics.getInstance()
                        .recordException(result.error) // Log exception to remote server
                }
            }
        }

        binding.photoViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
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
        })

        binding.previousButton.setOnClickListener {
            binding.photoViewPager.setCurrentItem(currentPage - 1, true)
        }
        binding.nextButton.setOnClickListener {
            binding.photoViewPager.setCurrentItem(currentPage + 1, true)
        }

        // Restore state of pager items position
        viewModel.adapterPosition.observe(viewLifecycleOwner, EventObserver {
            binding.photoViewPager.setCurrentItem(it, false)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.adapterPosition.value = Event(binding.photoViewPager.currentItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}