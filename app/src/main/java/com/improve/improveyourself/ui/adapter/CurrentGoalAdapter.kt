package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.improve.improveyourself.R
import com.improve.improveyourself.data.model.Goal

/**
 * Created by konk3r on 3/8/18.
 */
class CurrentGoalAdapter : RecyclerView.Adapter<GoalViewHolder>() {

    private var list: MutableList<Goal> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_goal, parent, false)
        return GoalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setList(list: MutableList<Goal>) {
        this.list = list
        notifyDataSetChanged()
    }

}