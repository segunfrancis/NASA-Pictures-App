package com.project.segunfrancis.nasapicturesapp.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import coil.ImageLoader
import com.project.segunfrancis.nasapicturesapp.R
import com.project.segunfrancis.nasapicturesapp.adapter.PictureListAdapter
import com.project.segunfrancis.nasapicturesapp.databinding.FragmentPictureListBinding
import com.project.segunfrancis.nasapicturesapp.util.EventObserver
import com.project.segunfrancis.nasapicturesapp.util.Origin
import com.project.segunfrancis.nasapicturesapp.util.Result.*
import com.project.segunfrancis.nasapicturesapp.util.showMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PictureListFragment : Fragment() {

    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PictureListViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pictureAdapter = PictureListAdapter(imageLoader, { position ->
            val direction =
                PictureListFragmentDirections.actionPictureListFragmentToPictureDetailsFragment(
                    position,
                    Origin.PICTURE_LIST_FRAGMENT
                )
            findNavController().navigate(direction)
        }, { likedNasaItem ->
            viewModel.addBookmark(likedNasaItem)
        }, { unlikedNasaItem ->
            viewModel.removeBookmark(unlikedNasaItem)
        }, { likeButton, nasaItem ->
            viewModel.isBookmark(nasaItem).observe(viewLifecycleOwner) {
                likeButton.isLiked = it
            }
        })

        viewModel.pictureList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Success -> {
                    pictureAdapter.submitList(result.data)
                    Timber.d(result.data.toString())
                }
                is Error -> {
                    val error = result.error
                    Toast.makeText(
                        requireContext(),
                        error.localizedMessage,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    Timber.e(error)
                }
            }
        }
        viewModel.bookmarkMessage.observe(viewLifecycleOwner, EventObserver {
            binding.root.showMessage(it)
        })
        binding.pictureListRecyclerView.apply {
            adapter = pictureAdapter
            layoutManager =
                GridLayoutManager(requireContext(), resources.getInteger(R.integer.span_count))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}