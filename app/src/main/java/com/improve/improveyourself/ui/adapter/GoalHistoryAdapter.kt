package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.ListItem
import com.improve.improveyourself.ui.controller.GoalHistoryDialogController
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.GoalHistoryDialogViewImpl

/**
 * Created by konk3r on 3/8/18.
 */
class GoalHistoryAdapter(val goalManager: GoalManager, val inflater: LayoutInflater,
                         mainRouter: MainRouter): RecyclerView.Adapter<GoalListItemViewHolder>() {

    private var list: MutableList<ListItem<Goal>> = ArrayList()
    private var dialogView: View = inflater.inflate(R.layout.dialog_goal_history_update, null, false)
    private var goalHistoryDialogController: GoalHistoryDialogController

    init {
        val goalHistoryDialogView = GoalHistoryDialogViewImpl(dialogView)
        goalHistoryDialogController = GoalHistoryDialogController(goalHistoryDialogView, goalManager, mainRouter)
        goalHistoryDialogView.setController(goalHistoryDialogController)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalListItemViewHolder {
        when (viewType) {
            TYPE_ITEM -> {
                val view: View = inflater.inflate(R.layout.list_goal, parent, false)
                return GoalHistoryViewHolder(view, goalHistoryDialogController)
            }

            TYPE_HEADER -> {
                val view: View = inflater.inflate(R.layout.list_goal_header, parent, false)
                return GoalHeaderViewHolder(view)
            }

            else -> throw IllegalArgumentException("GoalHistoryAdapter must return a valid view type")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GoalListItemViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    fun setList(list: MutableList<ListItem<Goal>>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun onGoalUpdated(listItem: ListItem<Goal>) {
        val position = list.indexOf(listItem)
        notifyItemChanged(position)
    }

    override fun getItemViewType(position: Int): Int {
        val item = list.get(position)
        when (item.type) {
            ListItem.Type.HEADER -> return TYPE_HEADER
            ListItem.Type.LINE_ITEM -> return TYPE_ITEM
        }
    }

    companion object {
        val TYPE_HEADER = 0
        val TYPE_ITEM = 1
    }
}