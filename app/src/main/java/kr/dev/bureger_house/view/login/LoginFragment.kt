package kr.dev.bureger_house.view.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kr.dev.bureger_house.R
import kr.dev.bureger_house.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding
    private  val viewModel by viewModels<LoginFragmentVM> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.apply {
//            userNameLiveData.observe(viewLifecycleOwner,userNameObserver)
//        }

       var  email: Editable? =  binding.etEmail.text
        binding.bntSignup.setOnClickListener {

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_mainFragment)
//            viewModel.userShow.observe(viewLifecycleOwner, Observer {
//
        //
        //

        //                                Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show()
//
//            })


        }
    }

    private val userNameObserver = Observer<String?> {
        binding.tvHaveAccount.text = it
    }

}