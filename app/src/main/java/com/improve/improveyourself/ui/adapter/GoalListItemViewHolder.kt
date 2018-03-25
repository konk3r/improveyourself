package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.ListItem
import kotlinx.android.extensions.LayoutContainer


/**
 * Created by konk3r on 3/8/18.
 */
abstract class GoalListItemViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bind(listItem: ListItem<Goal>)

}