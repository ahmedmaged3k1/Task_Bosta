package com.example.bosta_task.ui.features.common.homeFragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bosta_task.databinding.FragmentHomeBinding
import com.example.bosta_task.ui.features.core.HelperFunctions.isInternetConnected
import com.example.bosta_task.ui.features.core.HelperFunctions.noInternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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
            initializeUsersRemote()
        }
        else{
            noInternetConnection(requireContext(), requireActivity())

        }
    }
    private fun initializeUsersRemote() {
            viewModel.getAllUsers()
            viewModel.usersList.observe(viewLifecycleOwner) { userList ->
                binding.textViewUserName.text = userList?.get(0)?.name
            }

    }


}