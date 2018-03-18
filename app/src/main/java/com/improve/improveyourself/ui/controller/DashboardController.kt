package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.TimePair
import com.improve.improveyourself.data.model.NotificationStatus
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
    lateinit var checkInClickedObservable: Relay<TimePair>
    lateinit var setGoalsClickedObservable: Relay<TimePair>
    lateinit var checkInStatusObservable: Relay<NotificationStatus>
    lateinit var setGoalsStatusObservable: Relay<NotificationStatus>

    var disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_dashboard, container, false)
        inflater.toString()
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
        checkInClickedObservable = PublishRelay.create<TimePair>()
        setGoalsClickedObservable = PublishRelay.create<TimePair>()
        checkInStatusObservable = notificationManager.getCheckInObservable()
        setGoalsStatusObservable = notificationManager.getSetGoalsObservable()

        disposables.addAll(
                checkInClickedObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ time -> notificationManager.setCheckInTime(time.hour, time.minutes) }),

                setGoalsClickedObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ time -> notificationManager.setSetGoalsTime(time.hour, time.minutes) }),

                checkInStatusObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ status -> onCheckInChange(status)}),

                setGoalsStatusObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ status -> onSetGoalsChange(status)})
        )

    }

    private fun onSetGoalsChange(status: NotificationStatus) {
        if (status.isEnabled) {
            displaySetGoalsTime(status.timePair!!)
        } else  {
            fadeOutSetGoalsTime()
        }
    }

    private fun onCheckInChange(status: NotificationStatus) {
        if (status.isEnabled) {
            displayCheckInTime(status.timePair!!)
        } else  {
            fadeOutCheckInTime()
        }
    }

    private fun setCompletedCount() {
        val goalsCompleted = goalManager.getCompletedCount()
        when (goalsCompleted) {
            0L -> {
                dashboardView.hideGoalCountIcon()
                dashboardView.hideGoalCount()
            }
            else -> {
                dashboardView.displayGoalCountIcon()
                dashboardView.displayGoalCount()
                dashboardView.setGoalCount(goalsCompleted)
            }
        }
    }

    fun onSetCheckInClicked() {
        dashboardView.displayCheckInDialog(notificationManager.getCheckInTime())
    }

    fun onCheckInTimeSet(hours: Int, minutes: Int) {
        notificationManager.setCheckInTime(hours, minutes)
    }

    private fun displaySetGoalsTime(time: TimePair) {
        dashboardView.displaySetGoalsTime(time)
        dashboardView.displaySetGoalsCancelButton()
        dashboardView.setSetGoalsTitleTextSet()
    }

    private fun fadeOutSetGoalsTime() {
        dashboardView.fadeOutSetGoalsCancelButton()
        dashboardView.fadeOutSetGoalsTime({ _ -> dashboardView.setSetGoalsTitleTextUnset() })
    }

    private fun displayCheckInTime(time: TimePair) {
        dashboardView.displayCheckInTime(time)
        dashboardView.displayCheckInCancelButton()
        dashboardView.setCheckInTitleTextSet()
    }

    private fun fadeOutCheckInTime() {
        dashboardView.fadeOutCheckInCancelButton()
        dashboardView.fadeOutCheckInTime({ _ -> dashboardView.setCheckInTitleTextUnset() })
    }

    fun onSetSetGoalsClicked() {
        dashboardView.displaySetGoalsDialog(notificationManager.getSetGoalsTime())
    }

    fun onSetGoalsTimeSet(hours: Int, minutes: Int) {
        notificationManager.setSetGoalsTime(hours, minutes)
    }

    fun onCancelCheckInTimeClicked() {
        notificationManager.disableCheckInNotifications()
    }

    fun onCancelSetGoalsTimeClicked() {
        notificationManager.disableSetGoalsNotifications()
    }
}
