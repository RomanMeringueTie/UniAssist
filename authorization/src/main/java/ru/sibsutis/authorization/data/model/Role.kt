package ru.sibsutis.authorization.data.model

import androidx.annotation.StringRes
import ru.sibsutis.authorization.R

enum class Role(@StringRes val string: Int) {
    Student(R.string.student),
    Teacher(R.string.teacher)
}