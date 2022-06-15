package com.example.roomapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.Student
import com.example.roomapp.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.buttonAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = editTextNameAdd.text.toString()
        val lastName = editTextLastNameAdd.text.toString()
        val age = editTextAgeAdd.text
        //val email = editTextEmailAdd.text.toString()

        if (inputCheck(name, lastName, age)) {
            val student = Student(
                0,
                name,
                lastName,
                Integer.parseInt(age.toString())
                //email
            )
            //adicionando dados no database
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(requireContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}