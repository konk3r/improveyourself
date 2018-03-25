package com.improve.improveyourself.data.model

import com.improve.improveyourself.R
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
@Entity
data class Goal(var type: String = "",
                var title: String = "",
                var date: Date = Date(),
                var steps: String = "",
                var isCompleted: Boolean = false,
                @Id var id: Long = 0) {

    fun getIconResource(): Int {
        when (type) {
            Goal.PRODUCTIVE -> return R.drawable.ic_fitness_center_black_24dp
            Goal.SELF_CARE -> return R.drawable.ic_hot_tub_black_24dp
            "Relaxation" -> return R.drawable.ic_hot_tub_black_24dp
            else -> throw IllegalArgumentException("Invalid goal type, a goal must either be productive or self care")
        }
    }

    override fun toString(): String {
        return "Goal(title='$title')"
    }

    companion object Type {
        val PRODUCTIVE = "Productive"
        val SELF_CARE = "Self Care"
    }
}
