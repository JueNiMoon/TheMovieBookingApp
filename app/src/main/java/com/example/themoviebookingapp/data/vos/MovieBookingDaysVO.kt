package com.example.themoviebookingapp.data.vos

import java.time.LocalDate
import java.time.LocalDateTime

data class MovieBookingDaysVO (
    var isSelected : Boolean = false,
    var bookingDays : LocalDate = LocalDate.now()
)