package com.hashcode.chocshop.models

/**
 * Created by oluwalekefakorede on 25/11/2017.
 */
data class User(var userName:String, var picUrl:String, var email:String, var numberOfOrders:Int)
data class Order(var type:String, var number: Int)