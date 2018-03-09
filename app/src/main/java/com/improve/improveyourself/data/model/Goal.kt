package com.improve.improveyourself.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by konk3r on 3/8/18.
 */
@Entity
data class Goal(@Id var id: Long = 0,
                var type: String? = null,
                var title: String? = null,
                var date: String? = null,
                var steps: String? = null)
