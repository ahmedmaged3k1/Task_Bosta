package com.example.bosta_task.ui.features.common.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.bionic_time.data.dataSource.remoteDataSource.entities.Photos
import com.example.bosta_task.databinding.FragmentDetailsBinding
import com.example.bosta_task.ui.features.common.detailsFragment.adapters.PhotoRecyclerView
import com.example.bosta_task.ui.features.core.HelperFunctions
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()
    private val albumPhotosRecyclerViewAdapter = PhotoRecyclerView()
    private val viewModel: DetailsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (HelperFunctions.isInternetConnected(requireContext())) {
            initializeUsersAlbum()
            searchForPhoto()

        } else {
            HelperFunctions.noInternetConnection(requireContext(), requireActivity())

        }
    }

    private fun initializeUsersAlbum() {
        viewModel.getAlbumPhotos(args.albumsId)
        viewModel.albumPhotosList.observe(viewLifecycleOwner) {
            albumPhotosRecyclerViewAdapter.submitList(viewModel.albumPhotosList.value)
            binding.recyclerViewAlbums.adapter = albumPhotosRecyclerViewAdapter
            binding.shimmerFrameLayout.stopShimmer()
            binding.recyclerViewAlbums.visibility = View.VISIBLE
            binding.shimmerFrameLayout.visibility = View.INVISIBLE
        }

    }

    private fun searchForPhoto() {
        binding.editTextSearch.clearFocus()
        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String): Boolean {
                filerProducts(newText)
                return true
            }

        })
    }

    private fun filerProducts(text: String) {
        val filteredList = mutableListOf<Photos>()
        viewModel.albumPhotosList.value?.forEach {
            if (it.title.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                filteredList.add(it)

            }
        }
        if (filteredList.isEmpty()) {



            albumPhotosRecyclerViewAdapter.submitList(filteredList)
        } else {
            albumPhotosRecyclerViewAdapter.submitList(filteredList)


            binding.recyclerViewAlbums.adapter = albumPhotosRecyclerViewAdapter
        }
    }


}