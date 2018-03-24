package com.improve.improveyourself.ui.controller

import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.improve.improveyourself.R
import com.improve.improveyourself.R.id.main_toolbar
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.modules.TabContainerModule
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.navigation.ToolbarManager
import com.improve.improveyourself.ui.view.TabContainerView
import com.improve.improveyourself.ui.view.TabContainerViewImpl
import com.improve.improveyourself.util.roundDateToDay
import com.improve.improveyourself.util.subtractDay
import java.util.*

/**
 * Created by konk3r on 2/7/18.
 */

class TabContainerController(val startScreen: String? = null) : Controller(), MainRouter, ToolbarManager {

    val component by lazy { (activity as MainActivity).component.plus(TabContainerModule(this, this)) }
    private lateinit var tabContainerView: TabContainerView
    private lateinit var supportActionBar: ActionBar
    private lateinit var bottomNavRouter: Router
    private var currentTabId: Int = UNSET

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_main, container, false) as ViewGroup
        component.inject(this)
        tabContainerView = TabContainerViewImpl(view, this)
        bottomNavRouter = getChildRouter(view.findViewById(R.id.main_view_container))
        setupBottomNav(view)

        val toolbar: Toolbar = view.findViewById(main_toolbar)

        (activity as MainActivity).setSupportActionBar(toolbar)
        supportActionBar = (activity as MainActivity).supportActionBar!!

        if (!bottomNavRouter.hasRootController()) {
            setRootController(view)
        }

        return view
    }

    private fun setRootController(view: View) {
        when (startScreen) {
            SCREEN_CHECK_IN -> launchYesterdaysGoals()
            SCREEN_SET_GOALS -> launchSetGoals()
            else -> {
                val bottomNav = (view.findViewById(R.id.main_bottom_navigation) as BottomNavigationView)
                val controller = DashboardController(component)
                bottomNavRouter.setRoot(RouterTransaction.with(controller));
                bottomNavRouter.popToRoot()
                bottomNav.getMenu().getItem((DASHBOARD_POSITION)).isChecked = true
                currentTabId = R.id.action_dashboard
            }
        }
    }

    fun launchSetGoals() {
        val bottomNav = (view!!.findViewById(R.id.main_bottom_navigation) as BottomNavigationView)
        val today = Date().roundDateToDay()
        val controller = GoalListController(today, component)

        bottomNavRouter.setRoot(RouterTransaction.with(controller));
        bottomNavRouter.popToRoot()
        bottomNavRouter.pushController(RouterTransaction.with(CreateGoalController(today, component)))
        bottomNav.getMenu().getItem((GOAL_LIST_POSITION)).isChecked = true
        currentTabId = R.id.action_goals
    }

    fun launchYesterdaysGoals() {
        val bottomNav = (view!!.findViewById(R.id.main_bottom_navigation) as BottomNavigationView)
        val yesterday = Date().subtractDay().roundDateToDay()
        val controller = GoalListController(yesterday, component)

        bottomNavRouter.setRoot(RouterTransaction.with(controller));
        bottomNav.getMenu().getItem((GOAL_LIST_POSITION)).isChecked = true
        currentTabId = R.id.action_goals
    }

    private fun setupBottomNav(view: ViewGroup) {
        val bottomNavBar = view.findViewById(R.id.main_bottom_navigation) as BottomNavigationView
        bottomNavBar.setOnNavigationItemSelectedListener({ item ->
            if (item.itemId != currentTabId) {
                openNewTab(item.itemId)
                currentTabId = item.itemId
                true
            } else {
                currentTabId = item.itemId
                false
            }
        })
    }

    private fun openNewTab(itemId: Int) {
        when (itemId) {
            R.id.action_history -> launchHistory()
            R.id.action_dashboard -> launchDashboard()
            R.id.action_goals -> launchTodaysGoals()
        }
    }

    override fun onDestroyView(view: View) {
        val bottomNavBar = view.findViewById(R.id.main_bottom_navigation) as BottomNavigationView
        bottomNavBar.setOnNavigationItemSelectedListener(null)
        super.onDestroyView(view)
    }

    private fun launchHistory() {
        val backstack = arrayListOf(RouterTransaction.with(GoalHistoryController(component)))
        bottomNavRouter.setBackstack(backstack, null)
    }

    private fun launchDashboard() {
        val backstack = arrayListOf(RouterTransaction.with(DashboardController(component)))
        bottomNavRouter.setBackstack(backstack, null)
    }

    fun launchTodaysGoals() {
        val today = Date().roundDateToDay()
        val backstack = arrayListOf(RouterTransaction.with(GoalListController(today, component)))
        bottomNavRouter.setBackstack(backstack, null)
    }

    override fun launchNewGoal(date: Date) {
        bottomNavRouter.pushController(RouterTransaction.with(CreateGoalController(date, component)))
    }
    override fun launchEditGoal(goal: Goal) {
        bottomNavRouter.pushController(RouterTransaction.with(CreateGoalController(goal, component)))
    }

    override fun goBack() {
        if (!bottomNavRouter.popCurrentController()) {
            router.popCurrentController()
        }
    }

    override fun hideActionBar() {
        supportActionBar.hide()
    }

    override fun displayActionBar() {
        supportActionBar.show()
    }

    override fun setSpinnerAdapter(adapter: ArrayAdapter<String>) {
        tabContainerView.setToolbarSpinnerAdapter(adapter)
    }

    override fun setSpinnerPosition(position: Int) {
        tabContainerView.setToolbarSpinnerPosition(position)
    }

    override fun setSpinnerSelectedListener(listener: AdapterView.OnItemSelectedListener) {
        tabContainerView.setSpinnerSelectedListener(listener)
    }

    override fun hideSpinner() {
        tabContainerView.hideSpinner()
    }

    override fun displaySpinner() {
        tabContainerView.displaySpinner()
    }

    override fun displayTitle() {
        supportActionBar.setDisplayShowTitleEnabled(true)
    }

    override fun hideTitle() {
        supportActionBar.setDisplayShowTitleEnabled(false)
    }

    override fun setTitle(text: String) {
        supportActionBar.setTitle(text)
    }

    companion object {
        val SCREEN_CHECK_IN = "screen_check_in"
        val SCREEN_SET_GOALS = "screen_set_goals"


        val GOAL_HISTORY_POSITION = 0
        val DASHBOARD_POSITION = 1
        val GOAL_LIST_POSITION = 2

        val UNSET = -1
    }

}
