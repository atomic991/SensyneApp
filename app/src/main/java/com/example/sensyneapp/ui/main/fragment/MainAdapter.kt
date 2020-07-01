package com.example.sensyneapp.ui.main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.databinding.ItemHospitalBinding
import com.example.sensyneapp.ui.base.BaseViewHolder

class MainAdapter(private val hospitals: List<Hospital>, private val listener: (Hospital) -> Unit): RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemHospitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalViewHolder(binding)
    }

    override fun getItemCount(): Int = hospitals.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class HospitalViewHolder(private val binding: ItemHospitalBinding): BaseViewHolder(binding.root) {

        private lateinit var viewModel: HospitalItemViewModel

        override fun onBind(position: Int) {
            val item = hospitals[position]
            viewModel = HospitalItemViewModel(item) {
                listener.invoke(it)
            }
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

    }

}