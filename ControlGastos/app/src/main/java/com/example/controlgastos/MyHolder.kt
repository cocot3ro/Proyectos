package com.example.controlgastos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.controlgastos.databinding.ItemListNodeBinding
import com.unnamed.b.atv.model.TreeNode

class MyHolder(context: Context) : TreeNode.BaseNodeViewHolder<ListNode>(context) {
    override fun createNodeView(node: TreeNode?, value: ListNode): View {
        val binding = ItemListNodeBinding.inflate(LayoutInflater.from(context))

        binding.textViewName.text = value.name
        binding.textViewAmount.text = value.getTotal().toString()

        return binding.root
    }
}