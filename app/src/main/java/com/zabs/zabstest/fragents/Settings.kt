package com.zabs.zabstest.fragents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zabs.zabstest.R
import com.zabs.zabstest.databinding.FragmentDashboardBinding
import com.zabs.zabstest.databinding.FragmentSettingsBinding
import com.zabs.zabstest.viewModel.AppViewModel


class Settings : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AppViewModel::class.java]

        binding.setting.text=viewModel.test
    }

}