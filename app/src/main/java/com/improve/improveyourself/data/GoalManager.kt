package com.improve.improveyourself.data

import com.improve.improveyourself.data.model.Goal
import io.objectbox.Box
import io.reactivex.Observable

/**
 * Created by konk3r on 3/8/18.
 */
class GoalManager(val goalBox: Box<Goal>) {
    fun loadTomorrowsGoals(): io.reactivex.Observable<MutableList<Goal>> {
        return Observable.just(goalBox.all)
    }

    fun storeGoal(goal: Goal) {
        goalBox.put(goal)
    }
}