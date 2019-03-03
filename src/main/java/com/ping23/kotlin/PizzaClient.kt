package com.ping23.kotlin


class PizzaClient {

    companion object {

        fun main() {
            val myPizza: Pizza = Pizza(6, listOf(Toppings.Pepperoni, Toppings.Cheese, Toppings.Mushroom))

            println(myPizza.toString())

        } // main()

    } // companion object

}
