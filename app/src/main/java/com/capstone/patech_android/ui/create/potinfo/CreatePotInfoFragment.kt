package com.capstone.patech_android.ui.create.potinfo

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.navigation.navGraphViewModels
import com.capstone.patech_android.R
import com.capstone.patech_android.base.ViewModelFragment
import com.capstone.patech_android.databinding.FragmentCreatePotInfoBinding
import com.capstone.patech_android.ui.create.CreateViewModel
import com.capstone.patech_android.util.KeyBoardUtil
import com.capstone.patech_android.util.databinding.imageCoil
import com.capstone.patech_android.util.navigate
import com.capstone.patech_android.util.popBackStack

class CreatePotInfoFragment : ViewModelFragment<FragmentCreatePotInfoBinding, CreateViewModel>(
    R.layout.fragment_create_pot_info
) {
    override val viewModel: CreateViewModel by navGraphViewModels(R.id.create_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
        setSeekBarChangeListener()
        setSuccessCreateObserve()
    }

    private fun initView() {
        binding.seekBar.setPadding(0)

        viewModel.image.observe(viewLifecycleOwner) { image ->
            if (image != null) {
                binding.ivPhoto.imageCoil(image)
            }
        }
    }

    private fun addListener() {
        binding.btnBack.setOnClickListener {
            popBackStack()
        }
        binding.tvComplete.setOnClickListener {
            viewModel.postPlantCreate()
        }
    }

    private fun setSeekBarChangeListener() {
        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    if (seekBar != null) {
                        viewModel.ratio.value = seekBar.progress
                    }
                }
            }
        )
    }

    private fun setSuccessCreateObserve() {
        viewModel.successCreate.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                binding.progressBar.visibility = View.GONE
                navigate(R.id.actionCreatePotInfoFragmentToHomeFragment)
                KeyBoardUtil.hide(requireActivity())
                Toast.makeText(context, "새로운 파가 등록되었어요!", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }
}