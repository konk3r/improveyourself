package com.improve.improveyourself.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
@Entity
data class Goal(@Id var id: Long = 0,
                var type: String? = null,
                var title: String? = null,
                var date: Date? = null,
                var steps: String? = "",
                var isCompleted: Boolean = false
                ) {

    constructor(type: String?,
                title: String?,
                date: Date?,
                steps: String? = "") : this(0, type, title, date, steps)

    companion object Type {
        val PRODUCTIVE = "Productive"
        val RELAXATION = "Relaxation"
    }
}
