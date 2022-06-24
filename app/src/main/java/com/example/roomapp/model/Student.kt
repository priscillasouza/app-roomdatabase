package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", defaultValue = "0")
    val id: Int,
    @ColumnInfo(name = "name", defaultValue = "0")
    val name: String,
    @ColumnInfo(name = "lastName", defaultValue = "0")
    val lastName: String,
    @ColumnInfo(name = "age", defaultValue = "0")
    val age: Int,
    @ColumnInfo(name = "email", defaultValue = "0")
    val email: String
): Parcelable
