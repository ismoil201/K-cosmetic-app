package kr.dev.bureger_house.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kr.dev.bureger_house.R
import kr.dev.bureger_house.adapters.BurgerAdapter
import kr.dev.bureger_house.adapters.CallBack
import kr.dev.bureger_house.adapters.CallBackDrink
import kr.dev.bureger_house.adapters.DrinkAdapter
import kr.dev.bureger_house.databinding.FragmentBurgerMenuBinding
import kr.dev.bureger_house.databinding.FragmentDrinkMenuBinding
import kr.dev.bureger_house.models.BurgerData


class DrinkMenuFragment : Fragment() {
    val Vottar_NAME: String = "Drink"

    val Drink_NAME: String = "vottar"

    lateinit var binding: FragmentDrinkMenuBinding
    lateinit var drinkList :ArrayList<BurgerData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinkMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drinkList = BurgerData.drinkList

        val drinkAdapter = DrinkAdapter(drinkList, callBack = object : CallBackDrink {
            override fun itemClick(position: Int) {
                val sharedPreferences = requireActivity().getSharedPreferences(Vottar_NAME, Context.MODE_PRIVATE)

                val first = sharedPreferences.getString(Drink_NAME, "") ?: ""
                val second = sharedPreferences.getString("ikkinchi_drink", "") ?: ""
                val third = sharedPreferences.getString("uchinchi_drink", "") ?: ""
                val fourth = sharedPreferences.getString("tortinchi_drink", "") ?: ""
                val fifth = sharedPreferences.getString("beshinchi_drink", "") ?: ""

                val selectedDrink = drinkList[position].name
                val editor = sharedPreferences.edit()

                when {
                    first.isEmpty() && selectedDrink != second && selectedDrink != third && selectedDrink != fourth && selectedDrink != fifth -> {
                        editor.putString(Drink_NAME, selectedDrink)
                        Toast.makeText(requireActivity(), "~~ 1st Drink Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    second.isEmpty() && selectedDrink != first && selectedDrink != third && selectedDrink != fourth && selectedDrink != fifth -> {
                        editor.putString("ikkinchi_drink", selectedDrink)
                        Toast.makeText(requireActivity(), "~~ 2nd Drink Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    third.isEmpty() && selectedDrink != first && selectedDrink != second && selectedDrink != fourth && selectedDrink != fifth -> {
                        editor.putString("uchinchi_drink", selectedDrink)
                        Toast.makeText(requireActivity(), "~~ 3rd Drink Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    fourth.isEmpty() && selectedDrink != first && selectedDrink != second && selectedDrink != third && selectedDrink != fifth -> {
                        editor.putString("tortinchi_drink", selectedDrink)
                        Toast.makeText(requireActivity(), "~~ 4th Drink Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    fifth.isEmpty() && selectedDrink != first && selectedDrink != second && selectedDrink != third && selectedDrink != fourth -> {
                        editor.putString("beshinchi_drink", selectedDrink)
                        Toast.makeText(requireActivity(), "~~ 5th Drink Selected ✅ ~~", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(requireActivity(), "This drink is already selected or the slots are full ❌", Toast.LENGTH_SHORT).show()
                    }
                }

                editor.apply()
            }
        })

        binding.rvDrink.adapter = drinkAdapter
    }

}