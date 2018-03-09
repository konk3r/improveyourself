package com.improve.improveyourself.data

import com.improve.improveyourself.data.model.Goal
import io.reactivex.Observable
import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
class GoalManager {
    fun loadTomorrowsGoals(): io.reactivex.Observable<MutableList<Goal>> {
        val goals: ArrayList<Goal> = ArrayList()
        return Observable.just(goals)
    }

    fun storeGoal(goal: Goal) {
    }
}