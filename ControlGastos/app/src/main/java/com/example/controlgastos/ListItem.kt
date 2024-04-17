package com.example.controlgastos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity
data class ListItem(override val name: String, var amount: Number) : ListNode(name, ListNodeType.ITEM) {

    constructor() : this(Calendar.getInstance().time.toString(), 0)

    @PrimaryKey
    var id: Int = 0

    override fun getTotal(): Double {
        return amount.toDouble()
    }
}