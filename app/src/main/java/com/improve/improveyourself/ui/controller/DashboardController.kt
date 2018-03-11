package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.modules.TabContainerComponent
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.notification.NotificationAlarmManager
import com.improve.improveyourself.ui.view.DashboardView
import com.improve.improveyourself.ui.view.DashboardViewImpl
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class DashboardController(var component: TabContainerComponent? = null) : Controller() {

    private lateinit var dashboardView: DashboardView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter
    @Inject lateinit var notificationManager: NotificationAlarmManager
    var checkInObservable: Relay<Pair<Int, Int>>? = null
    var setGoalsObservable: Relay<Pair<Int, Int>>? = null
    var disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_dashboard, container, false)
        component!!.inject(this)
        dashboardView = DashboardViewImpl(view, this)
        setupObservables()

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        mainRouter.hideActionBar()
        setCompletedCount()
    }

    override fun onDestroyView(view: View) {
        disposables.clear()
        super.onDestroyView(view)
    }

    private fun setupObservables() {
        checkInObservable = PublishRelay.create<Pair<Int, Int>>()
        disposables.add(checkInObservable!!.observeOn(AndroidSchedulers.mainThread())
                .subscribe({pair -> notificationManager.setCheckInTime(pair.first, pair.second)})
        )

        setGoalsObservable = PublishRelay.create<Pair<Int, Int>>()
        disposables.add(setGoalsObservable!!.observeOn(AndroidSchedulers.mainThread())
                .subscribe({pair -> notificationManager.setSetGoalsTime(pair.first, pair.second)})
        )
    }

    private fun setCompletedCount() {
        val goalsCompleted = goalManager.getCompletedCount()
        when (goalsCompleted) {
            0L -> dashboardView.hideGoalCount()
            else -> {
                dashboardView.displayGoalCount()
                dashboardView.setGoalCount(goalsCompleted)
            }
        }
    }

    fun onSetCheckInClicked() {
        dashboardView.displayCheckInDialog()
    }

    fun setCheckInTime(hours: Int, minutes: Int) {
        setGoalsObservable!!.accept(Pair(hours, minutes))
    }

    fun onSetSetGoalsClicked() {
        dashboardView.displaySetGoalsDialog()
    }

    fun setSetGoalsTime(hours: Int, minutes: Int) {
        setGoalsObservable!!.accept(Pair(hours, minutes))
    }
}
