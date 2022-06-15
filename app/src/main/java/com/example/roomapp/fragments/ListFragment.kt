package com.example.roomapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.adapter.ListAdpter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //infla o layout para esse fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdpter()
        val recyclerView = view.recyclerviewListStudents
        recyclerviewListStudents.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
}