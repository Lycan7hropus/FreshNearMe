package com.example.models

sealed class FoodCategory() {
    sealed class Dairy() : FoodCategory() {
        sealed class Milk() : Dairy(){
             object CowMilk : Milk()
             object GoatMilk : Milk()
        }
        object Cheese : Dairy()
        object Yogurt : Dairy()
        // Możesz dodać więcej podkategorii
    }

    sealed class FruitsAndVegetables() : FoodCategory() {
        object Apple : FruitsAndVegetables()
        object Banana : FruitsAndVegetables()
        // Możesz dodać więcej podkategorii
    }


}
