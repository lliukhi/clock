package com.example.clock.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clock.R
import com.example.clock.viewmodel.ClockViewModel

class ClockFragment : Fragment() {
    private  val TAG = "ClockFragment"
    val viewModel by lazy { ViewModelProvider(this).get(ClockViewModel::class.java) }

    private var binding: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_time, container, false)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView = view.findViewById<RecyclerView>(R.id.timeRecyclerView)
        var linearLayoutManager = LinearLayoutManager(context)
        var toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            (activity as AppCompatActivity).supportActionBar?.title = ""
        }
        setHasOptionsMenu(true)
        recyclerView.layoutManager = linearLayoutManager
        var adapter = ClockAdapter(viewModel.clockList)
        recyclerView.adapter = adapter
        recyclerView.setNestedScrollingEnabled(false);
        viewModel.clockLiveData.observe(viewLifecycleOwner, Observer { clocks ->
            viewModel.clockList.clear()
            viewModel.clockList.addAll(clocks)
            adapter.notifyDataSetChanged()
        })

        viewModel.updateClock("ll")
    }
        override fun onDestroyView() {
            super.onDestroyView()
            binding = null
        }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // Handle settings action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}




