package com.improve.improveyourself.ui.adapter

import android.view.View
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.ListItem
import kotlinx.android.synthetic.main.list_goal_header.*


/**
 * Created by konk3r on 3/8/18.
 */
class GoalHeaderViewHolder(override val containerView: View) : GoalListItemViewHolder(containerView) {

    override fun bind(listItem: ListItem<Goal>) {
        goal_header_text.text = listItem.text
    }
}