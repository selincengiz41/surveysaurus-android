package com.android.surveysaurus.adapter

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.surveysaurus.R

import com.android.surveysaurus.databinding.SurveyLayoutBinding

import com.android.surveysaurus.fragment.ViewPagerFragmentDirections
import com.android.surveysaurus.model.SurveyModel
import java.sql.Blob

class SurveyAdapter( private val listener:Listener):RecyclerView.Adapter<SurveyAdapter.SurveyHolder>() {

    interface Listener{
        fun onItemClick(surveyModel: SurveyModel)
    }
    private val imageList : ArrayList<Int> = arrayListOf(R.drawable.survey,R.drawable.survey1,R.drawable.survey2)
    private val  surveyList : ArrayList<SurveyModel> =ArrayList()
    class SurveyHolder(val binding:SurveyLayoutBinding) : RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyHolder {
        val binding = SurveyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return SurveyHolder(binding)

    }

    override fun onBindViewHolder(holder: SurveyHolder, position: Int) {

        holder.binding.questionRecycle.text=surveyList.get(position).question
        holder.binding.imageView13.setImageResource(imageList.get(position%3))
        var optionList:ArrayList<TextView> = ArrayList()
        for(item in 0 until surveyList.get(position).options.size){
            val option1 :TextView= TextView(holder.binding.root.context)
            option1.id= generateViewId()
            option1.text=surveyList.get(position).options.get(item)
            option1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.elipse26,0,0,0)
            holder.binding.root.addView(option1)
            optionList.add(option1)
            val params = option1.layoutParams as ConstraintLayout.LayoutParams
            if (item==0)
            params.topToBottom= holder.binding.questionRecycle.id
            else
                params.topToBottom= optionList.get(item-1).id
            params.startToStart=holder.binding.surveyLayout.id
            params.marginStart=30
            params.topMargin=5
            option1.requestLayout()
        }

        holder.binding.questionRecycle.setOnClickListener {

            listener.onItemClick(surveyList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return surveyList.size
    }

    fun recycleAdd(item :SurveyModel){
        surveyList.add(item)
    }

}