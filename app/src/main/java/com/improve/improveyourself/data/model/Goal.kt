package com.improve.improveyourself.data.model

import com.improve.improveyourself.util.formatToDay
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
                var steps: String? = "",
                var date: String? = null
                ) {

    constructor(type: String? = null,
                title: String? = null,
                steps: String? = "") : this(0, type, title, steps, Date().formatToDay())
}
