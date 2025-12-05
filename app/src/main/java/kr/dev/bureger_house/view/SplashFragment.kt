package kr.dev.bureger_house.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kr.dev.bureger_house.BaseFragment
import kr.dev.bureger_house.R
import kr.dev.bureger_house.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {

    lateinit var binding:FragmentSplashBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
           Navigation.findNavController(binding.root).navigate(R.id.action_splashFragment_to_loginFragment,
               )
        }, 1000)
    }



}