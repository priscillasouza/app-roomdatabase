package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.StudentDatabase
import com.example.roomapp.model.Student
import com.example.roomapp.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Student>>
    private val repository: StudentRepository

    init {
        val studentDao = StudentDatabase.getDatabase(
            application
        ).studentDao()
        repository = StudentRepository(studentDao)
        readAllData = repository.readAllData
    }

    fun addStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }
}