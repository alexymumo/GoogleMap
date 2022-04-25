package com.alexmumo.car.ui.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alexmumo.car.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 3000)
    }
}
