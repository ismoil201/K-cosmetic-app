package kr.dev.bureger_house.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kr.dev.bureger_house.R
import kr.dev.bureger_house.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    val PREF_NAME: String = "MyPrefs"

    val KEY_NAME: String = "burger"
    val Vottar_NAME: String = "Drink"

    val Drink_NAME: String = "vottar"
    lateinit var binding : FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainBottomNavigation.setupWithNavController(
            Navigation.findNavController(requireActivity(), R.id.main_host_fragment))
    }


    override fun onDestroyView() {
        super.onDestroyView()

        val sharedPreferences2 = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor2 = sharedPreferences2.edit()
        editor2.remove("burger")
        editor2.remove("ikkinchi")
        editor2.remove("uchinchi")
        editor2.apply()
        val sharedPreferences = requireActivity().getSharedPreferences(Vottar_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("vottar")
        editor.remove("ikkinchi_drink")
        editor.remove("uchinchi_drink")
        editor.apply()
    }
}