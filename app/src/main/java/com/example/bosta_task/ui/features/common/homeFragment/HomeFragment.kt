package com.example.bosta_task.ui.features.common.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bosta_task.R
import com.example.bosta_task.databinding.FragmentHomeBinding
import com.example.bosta_task.ui.features.common.homeFragment.adapters.UserAlbumRecyclerView
import com.example.bosta_task.ui.features.core.HelperFunctions.isInternetConnected
import com.example.bosta_task.ui.features.core.HelperFunctions.noInternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment()  {

    private lateinit var binding: FragmentHomeBinding
    private val userAlbumRecyclerViewAdapter = UserAlbumRecyclerView()
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (isInternetConnected(requireContext()))
        {
            initializeUser()
            initializeUsersAlbum()
        }
        else{
            noInternetConnection(requireContext(), requireActivity())

        }
    }
    private fun initializeUser() {
           viewModel.getUser()
            viewModel.usersList.observe(viewLifecycleOwner) { userList ->
                binding.textViewUserName.text = userList?.get(0)?.name
                binding.textViewUserAddress.text= getString(
                    R.string.user_address,
                    userList?.get(0)?.address?.city,
                    userList?.get(0)?.address?.street,
                    userList?.get(0)?.address?.zipcode
                )
                viewModel.getUserAlbums(userList[0].id  )

            }

    }
    private fun initializeUsersAlbum() {

      viewModel.userAlbumList.observe(viewLifecycleOwner){
          userAlbumRecyclerViewAdapter.submitList(viewModel.userAlbumList.value)
          binding.recyclerViewAlbums.adapter = userAlbumRecyclerViewAdapter
          binding.shimmerFrameLayout.stopShimmer()
          binding.recyclerViewAlbums.visibility=View.VISIBLE
          binding.shimmerFrameLayout.visibility=View.INVISIBLE
      }

    }



}