package com.example.roomapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.adapter.ListAdpter
import com.example.roomapp.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //infla o layout para esse fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdpter()
        val recyclerView = view.recyclerviewListStudents
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer { student ->
            adapter.setData(student)
        })

        view.floatingActionButtonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete_item){
            deleteAllStudents()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllStudents() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mStudentViewModel.deleteAllStudents()
            Toast.makeText(requireContext(), "Tudo removido com sucesso", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Excluir tudo?")
        builder.setMessage("Tem certeza de que deseja excluir tudo?")
        builder.create().show()
    }
}