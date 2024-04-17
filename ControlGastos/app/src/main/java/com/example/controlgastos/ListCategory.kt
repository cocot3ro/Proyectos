package com.example.controlgastos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListCategory(override val name: String, val nodes: MutableList<ListNode>) :
    ListNode(name, ListNodeType.CATEGORY) {

    constructor() : this("", mutableListOf<ListNode>())

    constructor(name: String) : this(name, mutableListOf<ListNode>())

    @PrimaryKey
    var id: Int = 0

    override fun getTotal(): Double {
        return nodes.sumOf { it.getTotal() }
    }

    fun addChildren(node: ListNode) {
        nodes.add(node)
    }

}