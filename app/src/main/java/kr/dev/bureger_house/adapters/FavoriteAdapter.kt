package kr.dev.bureger_house.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kr.dev.bureger_house.databinding.FragmentBurgerMenuBinding
import kr.dev.bureger_house.databinding.ItemBurgersMenuBinding
import kr.dev.bureger_house.databinding.ItemKursBinding
import kr.dev.bureger_house.models.BurgerData

class FavoriteAdapter (var drinkList: List<BurgerData> , var callBack: CallBack): RecyclerView.Adapter<FavoriteAdapter.Vh>() {

    inner class Vh(val binding: ItemKursBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val binding = ItemKursBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Vh(binding)
    }

    override fun getItemCount(): Int {
        return  drinkList.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = drinkList[position]
        with(holder.binding) {
            ivIvFlag1.setImageResource(item.image)
            tvCount.text = item.count.toString()
            tvBurgerTitle.text = item.name
            tvKcal.text = item.title
            tvPrice.text = item.price+" won"

            btnPlus.setOnClickListener {
                callBack.itemAddCount(item.name)
            }

            btnMinus.setOnClickListener {
                callBack.itemMinusCount(item.name)
            }

            btnDelete.setOnClickListener {
                callBack.itemDelete(item.name)
            }
        }
    }


    interface  CallBack{
        fun itemDelete(name:String)

        fun itemAddCount(name: String)
        fun itemMinusCount(name: String)


    }



}


