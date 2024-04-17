package com.example.controlgastos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListCategory(val name: String, val nodes: List<ListNode>) : ListNode {

    @PrimaryKey
    var id: Int = 0

    override fun getAmount(): Double {
        return nodes.sumOf { it.getAmount() }
    }
}