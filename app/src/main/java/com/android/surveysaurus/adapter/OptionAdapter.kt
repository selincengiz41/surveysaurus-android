package com.android.surveysaurus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.surveysaurus.databinding.AddOptionLayBinding

class OptionAdapter():RecyclerView.Adapter<OptionAdapter.OptionHolder>() {
    private val  optionList : ArrayList<Int> =ArrayList()
    class OptionHolder(val binding: AddOptionLayBinding) :RecyclerView.ViewHolder(binding.root){


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionHolder {
val binding =AddOptionLayBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return OptionHolder(binding)

    }

    override fun onBindViewHolder(holder: OptionHolder, position: Int) {

    }

    override fun getItemCount(): Int {
return optionList.size
    }


    fun recycleAdd(item :Int){
        optionList.add(item)
    }


}