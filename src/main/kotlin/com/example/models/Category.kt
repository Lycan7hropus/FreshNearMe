package com.example.models

sealed class Category {
    sealed class Food : Category() {
        sealed class Dairy : Food() {
            sealed class Milk : Dairy() {
                object CowMilk : Milk()
                object GoatMilk : Milk()
            }
            object Cheese : Dairy()
            object Yogurt : Dairy()
            // Możesz dodać więcej podkategorii
        }

        sealed class FruitsAndVegetables : Food() {
            object Apple : FruitsAndVegetables()
            object Banana : FruitsAndVegetables()
            // Możesz dodać więcej podkategorii
        }
    }

    sealed class Crafts : Category() {
        sealed class Woodwork : Crafts() {
            object Carving : Woodwork()
            object Furniture : Woodwork()
        }

        sealed class Textile : Crafts() {
            object Knitting : Textile()
            object Embroidery : Textile()
        }

        sealed class Pottery : Crafts() {
            object Sculpting : Pottery()
            object Glazing : Pottery()
        }

        // Add more craft categories as needed
    }
}
