package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.PreferenceManager
import com.improve.improveyourself.data.TimePair
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
    @Inject lateinit var prefsManager: PreferenceManager
    var checkInObservable: Relay<TimePair>? = null
    var setGoalsObservable: Relay<TimePair>? = null
    var disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_dashboard, container, false)
        component!!.inject(this)
        dashboardView = DashboardViewImpl(view, this)
        setupObservables()
        updateCheckInTime()
        updateSetGoalsTime()

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
        checkInObservable = PublishRelay.create<TimePair>()
        setGoalsObservable = PublishRelay.create<TimePair>()

        disposables.add(checkInObservable!!.observeOn(AndroidSchedulers.mainThread())
                .subscribe({time -> notificationManager.setCheckInTime(time.hour, time.minutes)})
        )

        disposables.add(setGoalsObservable!!.observeOn(AndroidSchedulers.mainThread())
                .subscribe({time -> notificationManager.setSetGoalsTime(time.hour, time.minutes)})
        )
    }

    private fun setCompletedCount() {
        val goalsCompleted = goalManager.getCompletedCount()
        when (goalsCompleted) {
            0L -> {
                dashboardView.hideGoalCountIcon()
                dashboardView.hideGoalCount()
            }
            else -> {
                dashboardView.displayGoalCounticon()
                dashboardView.displayGoalCount()
                dashboardView.setGoalCount(goalsCompleted)
            }
        }
    }

    fun onSetCheckInClicked() {
        val time = prefsManager.getCheckInNotificationsTime()
        dashboardView.displayCheckInDialog(time)
    }

    private fun updateSetGoalsTime() {
        if (prefsManager.setGoalsNotificationsAreEnabled()) {
            displaySetGoalsTime()
        } else {
            hideSetGoalsTime()
        }
    }

    private fun displaySetGoalsTime() {
        dashboardView.displaySetGoalsTime(prefsManager.getSetGoalsNotificationsTime())
        dashboardView.displaySetGoalsCancelButton()
        dashboardView.setSetGoalsTitleTextSet()
    }

    private fun hideSetGoalsTime() {
        dashboardView.hideSetGoalsCancelButton()
        dashboardView.hideSetGoalsTime()
        dashboardView.setSetGoalsTitleTextUnset()
    }

    fun onCheckInTimeSet(hours: Int, minutes: Int) {
        checkInObservable!!.accept(TimePair(hours, minutes))
        displayCheckInTime()
    }

    private fun updateCheckInTime() {
        if (prefsManager.checkInNotificationsAreEnabled()) {
            displayCheckInTime()
        } else {
            hideCheckInTime()
        }
    }

    private fun displayCheckInTime() {
        dashboardView.displayCheckInTime(prefsManager.getCheckInNotificationsTime())
        dashboardView.displayCheckInCancelButton()
        dashboardView.setCheckInTitleTextSet()
    }

    private fun hideCheckInTime() {
        dashboardView.hideCheckInCancelButton()
        dashboardView.hideCheckInTime()
        dashboardView.setCheckInTitleTextUnset()
    }

    fun onSetSetGoalsClicked() {
        val time = prefsManager.getSetGoalsNotificationsTime()
        dashboardView.displaySetGoalsDialog(time)
    }

    fun onSetGoalsTimeSet(hours: Int, minutes: Int) {
        setGoalsObservable!!.accept(TimePair(hours, minutes))
        displaySetGoalsTime()
    }

    fun onCancelCheckInTimeClicked() {
        prefsManager.disableCheckInNotifications()
        hideCheckInTime()
    }

    fun onCancelSetGoalsTimeClicked() {
        prefsManager.disableSetGoalsNotifications()
        hideSetGoalsTime()
    }
}
