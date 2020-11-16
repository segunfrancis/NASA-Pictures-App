package com.project.segunfrancis.nasapicturesapp.ui.details

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.project.segunfrancis.nasapicturesapp.databinding.FragmentOnboardingBinding
import com.project.segunfrancis.nasapicturesapp.util.AppConstants.ON_BOARDING_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

@AndroidEntryPoint
class OnBoardingFragment : DialogFragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottieAnimationView.playAnimation()
        binding.okButton.setOnClickListener { requireDialog().dismiss() }
    }

    override fun onDismiss(dialog: DialogInterface) {
        val editor = preferences.edit()
        editor.putBoolean(ON_BOARDING_KEY, true)
        editor.apply()

        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}