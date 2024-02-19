package com.zabs.zabstest.fragents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zabs.zabstest.R
import com.zabs.zabstest.databinding.FragmentDashboardBinding
import com.zabs.zabstest.viewModel.AppViewModel

class Dashboard : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]

        binding.setting.setOnClickListener {
            viewModel.test="Dashboard to setting"
            findNavController().navigate(R.id.settings)
        }
    }

}