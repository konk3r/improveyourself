package com.improve.improveyourself.data

import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.Goal_
import io.objectbox.Box
import io.reactivex.Observable

/**
 * Created by konk3r on 3/8/18.
 */
class GoalManager(val goalBox: Box<Goal>) {
    fun loadGoalsFor(date: String): io.reactivex.Observable<MutableList<Goal>> {
        val goals = goalBox.query()
                .equal(Goal_.date, date)
                .build()
                .find()
        return Observable.just(goals)
    }

    fun storeGoal(goal: Goal) {
        goalBox.put(goal)
    }

    fun getCompletedCount(): Long {
        return goalBox.query()
                .equal(Goal_.isCompleted, true)
                .build()
                .count()
    }
}