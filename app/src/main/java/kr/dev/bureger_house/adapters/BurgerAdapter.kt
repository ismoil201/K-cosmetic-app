package kr.dev.bureger_house.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kr.dev.bureger_house.databinding.FragmentBurgerMenuBinding
import kr.dev.bureger_house.databinding.ItemBurgersMenuBinding
import kr.dev.bureger_house.models.BurgerData

class BurgerAdapter (var burgerList: List<BurgerData>, var callBack: CallBack): RecyclerView.Adapter<BurgerAdapter.Vh>() {

    inner class Vh(val binding: ItemBurgersMenuBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val binding = ItemBurgersMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Vh(binding)
    }

    override fun getItemCount(): Int {
        return  burgerList.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        with(holder){
            with(burgerList[position]){

                binding.ivBurgerMenuImage.setImageResource(this.image)
                binding.tvBurgerName.text=this.name
                binding.tvBurgerTitle.text=this.title
                binding.tvBurgerPrice.text = this.price+" won"


            }
        }
        holder.binding.btnAddBurger.setOnClickListener {
            callBack.itemClick(position)
        }

    }




}

interface CallBack {

   fun  itemClick(position: Int)
}
