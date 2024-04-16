package com.example.controlgastos

enum class ListNodeType {
    CATEGORY, ITEM;

    override fun toString(): String {
        return when (this) {
            CATEGORY -> "CATEGORY"
            ITEM -> "ITEM"
        }
    }

}