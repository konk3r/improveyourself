package com.improve.improveyourself.data

import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * Created by konk3r on 3/10/18.
 */
class PreferenceManager(val preferences: SharedPreferences, val gson: Gson) {

    companion object {
        val CHECK_IN_NOTIFICATIONS_ENABLED_KEY = "key_check_in_notifications_enabled"
        val SET_GOALS_NOTIFICATIONS_ENABLED_KEY = "key_set_goals_notifications_enabled"
        val CHECK_IN_NOTIFICATION_TIME_KEY = "key_check_in_notification_time"
        val SET_GOALS_NOTIFICATION_TIME_KEY = "key_set_goals_notification_time"
    }

    fun enableCheckInNotifications(timePair: TimePair) {
        preferences.edit()
                .putBoolean(CHECK_IN_NOTIFICATIONS_ENABLED_KEY, true)
                .putString(CHECK_IN_NOTIFICATION_TIME_KEY, Gson().toJson(timePair))
                .apply()
    }

    fun checkInNotificationsAreEnabled(): Boolean {
        return preferences.getBoolean(CHECK_IN_NOTIFICATIONS_ENABLED_KEY, false)
    }

    fun getCheckInNotificationsTime(): TimePair {
        val timeJson = preferences.getString(CHECK_IN_NOTIFICATION_TIME_KEY, null)
        return if (timeJson == null)
            TimePair(12, 0)
        else
            gson.fromJson(timeJson, TimePair::class.java)
    }

    fun enableSetGoalsNotifications(timePair: TimePair) {
        preferences.edit()
                .putBoolean(SET_GOALS_NOTIFICATIONS_ENABLED_KEY, true)
                .putString(SET_GOALS_NOTIFICATION_TIME_KEY, Gson().toJson(timePair))
                .apply()
    }

    fun setGoalsNotificationsAreEnabled(): Boolean {
        return preferences.getBoolean(SET_GOALS_NOTIFICATIONS_ENABLED_KEY, false)
    }

    fun getSetGoalsNotificationsTime(): TimePair {
        val timeJson = preferences.getString(SET_GOALS_NOTIFICATION_TIME_KEY, null)
        return if (timeJson == null)
            TimePair(12, 0)
        else
            gson.fromJson(timeJson, TimePair::class.java)
    }

    fun disableCheckInNotifications() {
        preferences.edit()
                .putBoolean(CHECK_IN_NOTIFICATIONS_ENABLED_KEY, false)
                .apply()
    }

    fun disableSetGoalsNotifications() {
        preferences.edit()
                .putBoolean(SET_GOALS_NOTIFICATIONS_ENABLED_KEY, false)
                .apply()
    }

}