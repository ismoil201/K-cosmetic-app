package kr.dev.bureger_house.view

import android.R.attr.name
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kr.dev.bureger_house.adapters.BurgerAdapter
import kr.dev.bureger_house.adapters.CallBack
import kr.dev.bureger_house.databinding.FragmentBurgerMenuBinding
import kr.dev.bureger_house.models.BurgerData


class BurgerMenuFragment : Fragment() {

     val PREF_NAME: String = "MyPrefs"

     val KEY_NAME: String = "burger"


    lateinit var binding:FragmentBurgerMenuBinding
    lateinit var burgerList :ArrayList<BurgerData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBurgerMenuBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        burgerList = BurgerData.burgerList

        val burgerAdapter = BurgerAdapter(burgerList, callBack = object : CallBack {
            override fun itemClick(position: Int) {
                val sharedPreferences =
                    requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

                val first = sharedPreferences.getString(KEY_NAME, "") ?: ""
                val second = sharedPreferences.getString("ikkinchi", "") ?: ""
                val third = sharedPreferences.getString("uchinchi", "") ?: ""
                val fourth = sharedPreferences.getString("tortinchi", "") ?: ""
                val fifth = sharedPreferences.getString("beshinchi", "") ?: ""

                val selectedBurger = burgerList[position].name

                val editor = sharedPreferences.edit()

                when {
                    first.isEmpty() && selectedBurger != second && selectedBurger != third && selectedBurger != fourth && selectedBurger != fifth -> {
                        editor.putString(KEY_NAME, selectedBurger)
                        Toast.makeText(requireActivity(), "~~ 1st Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    second.isEmpty() && selectedBurger != first && selectedBurger != third && selectedBurger != fourth && selectedBurger != fifth -> {
                        editor.putString("ikkinchi", selectedBurger)
                        Toast.makeText(requireActivity(), "~~ 2nd Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    third.isEmpty() && selectedBurger != first && selectedBurger != second && selectedBurger != fourth && selectedBurger != fifth -> {
                        editor.putString("uchinchi", selectedBurger)
                        Toast.makeText(requireActivity(), "~~ 3rd Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    fourth.isEmpty() && selectedBurger != first && selectedBurger != second && selectedBurger != third && selectedBurger != fifth -> {
                        editor.putString("tortinchi", selectedBurger)
                        Toast.makeText(requireActivity(), "~~ 4th Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    fifth.isEmpty() && selectedBurger != first && selectedBurger != second && selectedBurger != third && selectedBurger != fourth -> {
                        editor.putString("beshinchi", selectedBurger)
                        Toast.makeText(requireActivity(), "~~ 5th Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(requireActivity(), "This burger is already selected or no slots available ❌", Toast.LENGTH_SHORT).show()
                    }
                }

                editor.apply()
            }
        })

        binding.rvBurger.adapter = burgerAdapter
    }







}