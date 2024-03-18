package com.example.tmdbclone.extension

enum class Gender {
    NotSpecified, Female, Male, NonBinary, None
}

private fun Gender.toGenderString(): String {
    return when (this) {
        Gender.NotSpecified -> "Not Specified"
        Gender.Female -> "Female"
        Gender.Male -> "Male"
        Gender.NonBinary -> "Non Binary"
        else -> Gender.None.name
    }
}

fun Int.toGender(): String {
    return when (this) {
        0 -> Gender.NotSpecified.toGenderString()
        1 -> Gender.Female.toGenderString()
        2 -> Gender.Male.toGenderString()
        3 -> Gender.NonBinary.toGenderString()
        else -> Gender.None.toGenderString()
    }
}