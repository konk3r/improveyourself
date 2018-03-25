package com.improve.improveyourself.data

import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.Goal_
import com.improve.improveyourself.util.roundDateToDay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.objectbox.Box
import io.reactivex.Observable
import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
class GoalManager(val goalBox: Box<Goal>) {

    val goalUpdateRelay = PublishRelay.create<Goal>()!!

    fun loadGoalsFor(date: Date): io.reactivex.Observable<MutableList<Goal>> {
        val goals = goalBox.query()
                .equal(Goal_.date, date.roundDateToDay())
                .build()
                .find()
        return Observable.just(goals)
    }

    fun storeGoal(goal: Goal) {
        goal.date = goal.date.roundDateToDay()
        goalBox.put(goal)
        goalUpdateRelay.accept(goal)
    }

    fun getCompletedCount(): Long {
        return goalBox.query()
                .equal(Goal_.isCompleted, true)
                .build()
                .count()
    }

    fun loadPreviousGoals(): Observable<MutableList<Goal>> {
        val today = Date().roundDateToDay()
        val goals = goalBox.query()
                .less(Goal_.date, today)
                .build()
                .find()

        return Observable.just(goals)
    }

    fun removeGoal(goal: Goal) {
        goalBox.remove(goal)
    }

    fun getUpdateObservable(): Relay<Goal> {
        return goalUpdateRelay
    }

}