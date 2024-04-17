package com.example.controlgastos

abstract class ListNode(open val name: String, val type: ListNodeType) {

    abstract fun getTotal(): Double
}