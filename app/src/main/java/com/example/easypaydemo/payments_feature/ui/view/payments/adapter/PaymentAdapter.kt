package com.example.easypaydemo.payments_feature.ui.view.payments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easypaydemo.core.dto.Payment
import com.example.easypaydemo.databinding.ComponentItemPayBinding

/**
 * @author : Mingaleev D
 * @data : 29.11.2023
 */

class PaymentAdapter :
    ListAdapter<Payment, PaymentAdapter.MyViewholder>(diff) {

   inner class MyViewholder(val binding: ComponentItemPayBinding) :
       RecyclerView.ViewHolder(binding.root) {

      fun bind(itemModel: Payment) {
         with(binding) {
            idTv.text = itemModel.id.toString()
            titleTv.text = itemModel.title
            amountTv.text = itemModel.amount?.toString()?: "N/A"
            createdTv.text = itemModel.created?.toString() ?: "N/A"
         }
      }
   }

   private companion object {

      val diff = object : DiffUtil.ItemCallback<Payment>() {
         override fun areItemsTheSame(oldItem: Payment, newItem: Payment) =
             oldItem::class == newItem::class

         override fun areContentsTheSame(oldItem: Payment, newItem: Payment) =
             oldItem == newItem

      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder =
       MyViewholder(
           ComponentItemPayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       )

   override fun onBindViewHolder(holder: MyViewholder, position: Int) {
      val item = getItem(position) ?: return

      holder.bind(item)
   }
}


