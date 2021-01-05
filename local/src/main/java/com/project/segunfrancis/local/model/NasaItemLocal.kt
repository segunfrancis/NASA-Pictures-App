package com.project.segunfrancis.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nasa_table")
data class NasaItemLocal(
    val copyright: String?,
    @PrimaryKey val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)