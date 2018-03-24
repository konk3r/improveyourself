package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.controller.GoalHistoryDialogController
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.GoalHistoryDialogViewImpl

/**
 * Created by konk3r on 3/8/18.
 */
class GoalHistoryAdapter(val goalManager: GoalManager, val inflater: LayoutInflater,
                         val mainRouter: MainRouter): RecyclerView.Adapter<GoalHistoryViewHolder>() {

    private var list: MutableList<Goal> = ArrayList()
    private var dialogView: View = inflater.inflate(R.layout.dialog_goal_history_update, null, false)
    private var goalHistoryDialogController: GoalHistoryDialogController

    init {
        val goalHistoryDialogView = GoalHistoryDialogViewImpl(dialogView)
        goalHistoryDialogController = GoalHistoryDialogController(goalHistoryDialogView, goalManager, mainRouter)
        goalHistoryDialogView.setController(goalHistoryDialogController)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalHistoryViewHolder {
        val view: View = inflater.inflate(R.layout.list_goal, parent, false)
        return GoalHistoryViewHolder(view, goalHistoryDialogController)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GoalHistoryViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setList(list: MutableList<Goal>) {
        this.list = list
        notifyDataSetChanged()
    }

}