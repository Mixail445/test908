package com.example.test908.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test908.databinding.ItemLoadstateBinding
import com.example.test908.utils.visible

class loadStateAdapter ( private val retry: () -> Unit): LoadStateAdapter<loadStateAdapter.loadHolder>() {

    class loadHolder(private val binding: ItemLoadstateBinding,private val retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState){
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage }
            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.buttonRetry.visible(loadState is LoadState.Error)
            binding.textViewError.visible(loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry() }
            binding.progressbar.visibility = View.VISIBLE
        }
    }
    override fun onBindViewHolder(holder: loadHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)
            = loadHolder(
        ItemLoadstateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}