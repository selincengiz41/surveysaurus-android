package com.android.surveysaurus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.surveysaurus.databinding.AddOptionLayBinding
import com.android.surveysaurus.model.ListedSurvey

class OptionAdapter(private val optionList: ArrayList<Int>, private val listener: Listener) :
    RecyclerView.Adapter<OptionAdapter.OptionHolder>() {
    interface Listener {
        fun onItemClick(optionList: ArrayList<Int>)
    }

    class OptionHolder(val binding: AddOptionLayBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionHolder {
        val binding =
            AddOptionLayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionHolder(binding)

    }

    override fun onBindViewHolder(holder: OptionHolder, position: Int) {
        holder.binding.delete.setOnClickListener {
            optionList.removeAt(position)

            notifyItemRemoved(position)
            notifyItemRangeChanged(position, optionList.size)
            listener.onItemClick(optionList)

        }
    }

    override fun getItemCount(): Int {
        return optionList.size
    }


}