package com.example.roomapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.Student
import com.example.roomapp.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.editTextNameUpdate.setText(args.currentStudent.name)
        view.editTextLastNameUpdate.setText(args.currentStudent.lastName)
        view.editTextAgeUpdate.setText(args.currentStudent.age.toString())
        //view.editTextEmailUpdate.setText(args.currentStudent.email)

        view.buttonUpdate.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val name = editTextNameUpdate.text.toString()
        val lastName = editTextLastNameUpdate.text.toString()
        val age = Integer.parseInt(editTextAgeUpdate.text.toString())
        //val email = editTextEmailUpdate.text.toString()

        if (inputCheck(name, lastName, editTextAgeUpdate.text)) {

            //Cria o obejto student
            val updateStudent = Student(args.currentStudent.id, name, lastName, age)
            mStudentViewModel.updateStudent(updateStudent)
            Toast.makeText(requireContext(), "Sucesso ao atualizar", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Por favor digite todos os campos", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(name: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_item) {
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mStudentViewModel.deleteStudent(args.currentStudent)
            Toast.makeText(
                requireContext(),
                "Removido com sucesso: ${args.currentStudent.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Delete ${args.currentStudent.name}?")
        builder.setMessage("Tem certeza de que deseja excluir ${args.currentStudent.name}?")
        builder.create().show()
    }
}