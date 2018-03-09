package com.improve.improveyourself.data

import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.Goal_
import com.improve.improveyourself.util.formatToDay
import com.improve.improveyourself.util.getTomorrowsDate
import io.objectbox.Box
import io.reactivex.Observable
import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
class GoalManager(val goalBox: Box<Goal>) {
    fun loadTomorrowsGoals(): io.reactivex.Observable<MutableList<Goal>> {
        val tomorrow = Date().getTomorrowsDate().formatToDay()
        val goals = goalBox.query()
                .equal(Goal_.date, tomorrow)
                .build()
                .find()
        return Observable.just(goals)
    }

    fun storeGoal(goal: Goal) {
        goalBox.put(goal)
    }
}