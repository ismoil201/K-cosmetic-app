package kr.dev.bureger_house.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.dev.bureger_house.adapters.FavoriteAdapter
import kr.dev.bureger_house.adapters.FavoriteAdapter.CallBack
import kr.dev.bureger_house.databinding.FragmentFavoriteBinding
import kr.dev.bureger_house.models.BurgerData

class CartFragment : Fragment() {

    private val PREF_NAME = "MyPrefs"
    private val KEY_NAME = "burger"

    private val Vottar_NAME = "Drink"
    private var totalPrice:Double = 0.0
    private val Drink_NAME = "vottar"

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var favoriteList: ArrayList<BurgerData>
    private lateinit var currentItemList: ArrayList<BurgerData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        favoriteList = ArrayList<BurgerData>().apply {
            addAll(BurgerData.burgerList)
            addAll(BurgerData.drinkList)
        }

        currentItemList = ArrayList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SharedPreferences
        val sharedPreferencesBurger = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sharedPreferencesDrink = requireActivity().getSharedPreferences(Vottar_NAME, Context.MODE_PRIVATE)

        val burgerNames = listOf(
            sharedPreferencesBurger.getString(KEY_NAME, "") ?: "",
            sharedPreferencesBurger.getString("ikkinchi", "") ?: "",
            sharedPreferencesBurger.getString("uchinchi", "") ?: "",
            sharedPreferencesBurger.getString("tortinchi", "") ?: "",
            sharedPreferencesBurger.getString("beshinchi", "") ?: ""
        )

        val drinkNames = listOf(
            sharedPreferencesDrink.getString(Drink_NAME, "") ?: "",
            sharedPreferencesDrink.getString("ikkinchi_drink", "") ?: "",
            sharedPreferencesDrink.getString("uchinchi_drink", "") ?: "",
            sharedPreferencesDrink.getString("tortinchi_drink", "") ?: "",
            sharedPreferencesDrink.getString("beshinchi_drink", "") ?: ""
        )


        val allNames = burgerNames + drinkNames

        // Add only matching items (no duplicates)
        for (itemName in allNames) {
            if (itemName.isNotEmpty()) { // bo‘sh stringlardan qochish
                val item = favoriteList.find { it.name == itemName }
                if (item != null && currentItemList.none { it.name == item.name }) {
                    currentItemList.add(item)
                }
            }
        }


        adapter = FavoriteAdapter(currentItemList, object : CallBack {
            override fun itemDelete(name: String) {

                // 1. currentItemList dan o‘chirish
                val index = currentItemList.indexOfFirst { it.name == name }
                if (index >= 0) {
                    currentItemList.removeAt(index)
                    adapter.notifyItemRemoved(index)
                }

                // 2. SharedPreferences dan o‘chirish
                val burgerPrefs = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                val drinkPrefs = requireActivity().getSharedPreferences(Vottar_NAME, Context.MODE_PRIVATE)

                val allBurgerKeys = listOf(KEY_NAME, "ikkinchi", "uchinchi", "tortinchi", "beshinchi")
                val allDrinkKeys = listOf(Drink_NAME, "ikkinchi_drink", "uchinchi_drink", "tortinchi_drink", "beshinchi_drink")


                // burger keys
                val editorBurger = burgerPrefs.edit()
                for (key in allBurgerKeys) {
                    val value = burgerPrefs.getString(key, "")
                    if (value.equals(name, ignoreCase = true)) {
                        editorBurger.remove(key)
                    }
                }
                editorBurger.apply()

                // drink keys
                val editorDrink = drinkPrefs.edit()
                for (key in allDrinkKeys) {
                    val value = drinkPrefs.getString(key, "")
                    if (value.equals(name, ignoreCase = true)) {
                        editorDrink.remove(key)
                    }
                }
                editorDrink.apply()

                totalPrice = currentItemList.sumOf { it.price.toDouble() * it.count.toDouble() }

                binding.tvTotalPrice.text = "Total price: " + totalPrice.toString()+" 원"

            }

            override fun itemAddCount(name: String) {
                val item = currentItemList.find { it.name == name }
                item?.let {
                    ++it.count
                   totalPrice = currentItemList.sumOf { it.price.toDouble() * it.count.toDouble() }

                    binding.tvTotalPrice.text = "Total price: " + totalPrice.toString()+" 원"
                    adapter.notifyItemChanged(currentItemList.indexOf(it))
                }
            }

            override fun itemMinusCount(name: String) {
                val item = currentItemList.find { it.name == name }
                item?.let {
                    if (it.count > 0) {
                        it.count--
                        totalPrice = currentItemList.sumOf { it.price.toDouble() * it.count.toDouble() }

                        binding.tvTotalPrice.text = "Total price: " + totalPrice.toString()+" 원"
                        adapter.notifyItemChanged(currentItemList.indexOf(it))
                    }
                }
            }
        })
         totalPrice = currentItemList.sumOf { it.price.toDouble() * it.count.toDouble() }

        binding.tvTotalPrice.text = "Total price: " + totalPrice.toString()+" 원"

        binding.rvFavorite.adapter = adapter

        binding.btnPayment.setOnClickListener {
            if (currentItemList.isEmpty()) {
                android.widget.Toast.makeText(requireContext(), "No menu selected", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val paymentMethods = arrayOf("Card", "Phone Number", "Cash")
            var selectedMethod = paymentMethods[0]

            val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            builder.setTitle("Complete Payment")

            val linearLayout = android.widget.LinearLayout(requireContext())
            linearLayout.orientation = android.widget.LinearLayout.VERTICAL
            linearLayout.setPadding(50, 40, 50, 10)

            val tvTotal = android.widget.TextView(requireContext())
            tvTotal.text = "Total amount: $totalPrice Won"
            tvTotal.textSize = 18f
            tvTotal.setPadding(0, 0, 0, 30)
            linearLayout.addView(tvTotal)

            val spinner = android.widget.Spinner(requireContext())
            val adapterSpinner = android.widget.ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paymentMethods)
            adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapterSpinner
            linearLayout.addView(spinner)

            builder.setView(linearLayout)

            builder.setPositiveButton("Pay") { dialog, _ ->
                selectedMethod = spinner.selectedItem as String
                android.widget.Toast.makeText(requireContext(), "Payment method: $selectedMethod\nTotal: $totalPrice UZS", android.widget.Toast.LENGTH_LONG).show()

                currentItemList.clear()
                adapter.notifyDataSetChanged()
                dialog.dismiss()

                // Clear SharedPreferences
                val sharedPreferencesBurger = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                sharedPreferencesBurger.edit().clear().apply()

                val sharedPreferencesDrink = requireActivity().getSharedPreferences("Drink", Context.MODE_PRIVATE)
                sharedPreferencesDrink.edit().clear().apply()

                favoriteList.clear()

                totalPrice = 0.0
                binding.tvTotalPrice.text = "Total price: " + totalPrice.toString()+" 원"

            }

            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}
