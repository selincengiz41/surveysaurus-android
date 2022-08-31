package com.android.surveysaurus.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.surveysaurus.R

import com.android.surveysaurus.databinding.SurveyLayoutBinding
import com.android.surveysaurus.fragment.MySurveyFragmentDirections
import com.android.surveysaurus.model.ListedSurvey

import com.android.surveysaurus.model.Survey

class SurveyAdapter(
    private val surveyList: ArrayList<ListedSurvey>,
    private val listener: Listener
) : RecyclerView.Adapter<SurveyAdapter.SurveyHolder>() {

    interface Listener {
        fun onItemClick(mySurveyModel: ListedSurvey)
    }

    private val imageList: ArrayList<Int> =
        arrayListOf(R.drawable.survey, R.drawable.survey1, R.drawable.survey2)

    class SurveyHolder(val binding: SurveyLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyHolder {
        val binding =
            SurveyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SurveyHolder(binding)

    }

    override fun onBindViewHolder(holder: SurveyHolder, position: Int) {
        if (!surveyList.get(position).question.isNullOrEmpty()) {
            holder.binding.questionRecycle.text = surveyList.get(position).question
            holder.binding.imageView13.setImageResource(imageList.get(position % 3))
            var optionList: ArrayList<TextView> = ArrayList()
            for (item in 0 until surveyList.get(position).choices.size) {
                val option1: TextView = TextView(holder.binding.root.context)
                option1.id = generateViewId()
                option1.text = surveyList.get(position).choices.get(item)
                option1.setTextColor(Color.parseColor("#000000"))
                option1.textSize=10f
                val typeface: Typeface? =
                    ResourcesCompat.getFont(
                        holder.binding.root.context!!,
                        com.android.surveysaurus.R.font.laila_medium
                    )
                option1.setTypeface(typeface)
                option1.compoundDrawablePadding=20
                option1.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.elipse26,
                    0,
                    0,
                    0
                )
                holder.binding.root.addView(option1)
                optionList.add(option1)
                val params = option1.layoutParams as ConstraintLayout.LayoutParams
                if (item == 0)
                    params.topToBottom = holder.binding.questionRecycle.id
                else
                    params.topToBottom = optionList.get(item - 1).id
                params.startToStart = holder.binding.surveyLayout.id
                params.marginStart = 30
                params.topMargin = 20
                option1.requestLayout()
            }
        } else {
            holder.binding.imageView13.setImageResource(R.drawable.add_survey)
            holder.binding.imageView13.setOnClickListener {
                val action =
                    MySurveyFragmentDirections.actionMySurveyFragmentToCreateSurveyFragment()
                Navigation.findNavController(holder.binding.root).navigate(action)
            }
        }



        holder.itemView.setOnClickListener {

            listener.onItemClick(surveyList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return surveyList.size
    }


}