package kr.dev.bureger_house.models

import kr.dev.bureger_house.R
import kotlin.random.Random

class BurgerData(var image: Int, var name: String, var title: String, var price: String, var count:Int) {

    companion object {

        private fun generateRandomPrice(): String {
            val randomPrice = Random.nextInt(3000, 8001) // 3000 ~ 8000
            return "$randomPrice"
        }

        private fun generateRandomKcal(max: Int): String {
            val kcal = Random.nextInt(0, max + 1)
            return "$kcal kcal"
        }

        var burgerList = ArrayList<BurgerData>().apply {
            addAll(listOf(
                BurgerData(R.drawable.burger11, "Patty Buns Burgers", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.blackburger15, "Hamburger Black", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.kfcburger, "KFC Cheeseburger", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.burger12, "Steamed Cheeseburger", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.kebab, "Kebab Lavash Babarafi MERR", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.hotdog, "Hot-dog Chicago", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.hotdog, "Hot-dog Chicago", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.burger16, "Hamburger Whopper Set", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.burger17, "Harleys Philly Steaks Cheesesteak", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.burger13, "Theta Burger", generateRandomKcal(300), generateRandomPrice(), 1),
                BurgerData(R.drawable.burger14, "Slaw Burger", generateRandomKcal(300), generateRandomPrice(), 1)
            ))
        }

        var drinkList = ArrayList<BurgerData>().apply {
            addAll(listOf(
                BurgerData(R.drawable.colazero, "Coca-Cola", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.pepci, "Pepsi", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.sprite, "Sprite", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.fanta1, "Fanta Orange", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.lemonade, "Mountain Dew", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.redbul, "Red Bull", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.monster, "Monster Energy", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.stawberry, "Iced Stawberry Latte", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.chocolate, "Chocolate Milkshake", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.juice, "Fresh Orange Juice", generateRandomKcal(150), generateRandomPrice(), 1),
                BurgerData(R.drawable.beef, "Berry Smoothie", generateRandomKcal(150), generateRandomPrice(), 1)
            ))
        }

    }
}
