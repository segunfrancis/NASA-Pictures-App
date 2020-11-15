package com.project.segunfrancis.nasapicturesapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import coil.ImageLoader
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.project.segunfrancis.nasapicturesapp.databinding.FragmentPictureListBinding
import com.project.segunfrancis.nasapicturesapp.ui.NasaViewModel
import com.project.segunfrancis.nasapicturesapp.util.Result.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PictureListFragment : Fragment() {

    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NasaViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pictureAdapter = PictureListAdapter(imageLoader)
        viewModel.pictureList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Success -> {
                    pictureAdapter.submitList(result.data)
                    Timber.d(result.data.toString())
                }
                is Error -> {
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
        binding.pictureListRecyclerView.apply {
            adapter = pictureAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}