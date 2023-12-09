package com.example.bosta_task.ui.features.common.mainNavigationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bosta_task.R
import com.example.bosta_task.databinding.FragmentMainNavigationBinding
import com.example.bosta_task.ui.features.common.homeFragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavigationFragment : Fragment() {


    private lateinit var binding: FragmentMainNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainNavigationBinding.inflate(inflater, container, false)
        replaceFragment(HomeFragment())
        navigationSelector()

        return binding.root
    }

    private fun navigationSelector() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                else -> {
                    false
                }
            }

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout, fragment)
        fragmentTransaction?.commit()

    }


}