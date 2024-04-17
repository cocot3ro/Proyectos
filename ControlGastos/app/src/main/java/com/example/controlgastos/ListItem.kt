package com.example.controlgastos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItem(var name: String, var amount: Double) : ListNode {

    @PrimaryKey
    var id: Int = 0

    override fun getAmount(): Double {
        return amount
    }
}