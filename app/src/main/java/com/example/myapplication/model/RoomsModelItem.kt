package com.example.myapplication.model

data class RoomsModelItem(
    val created_at: String,
    val id: String,
    val is_occupied: Boolean,
    val max_occupancy: Int,
    val name: String
)