package com.capstone.patech_android.ui.create.name

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreateNameBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.popBackStack

class CreateNameFragment : ViewModelFragment<FragmentCreateNameBinding, CreateViewModel>(
    R.layout.fragment_create_name
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
    }
}