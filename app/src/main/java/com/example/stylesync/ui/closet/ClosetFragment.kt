package com.example.stylesync.ui.closet

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.stylesync.data.remote.response.data
import com.google.android.material.tabs.TabLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stylesync.databinding.FragmentHomeBinding

class ClosetFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val closetViewModel = ViewModelProvider(this).get(ClosetViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupView()

        tabLayout = binding.tabLayout
        setupTabs()

        recyclerView = binding.recyclerView
        setupRecyclerView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupTabs() {
        // Add tabs for closet categories (e.g., Top, Bottom, Accessories)
        val categories = listOf("Top", "Bottom", "Dress", "Outwear", "Activewear", "Workwear", "Accessories", "Shoes")
        for (category in categories) {
            tabLayout.addTab(tabLayout.newTab().setText(category))
        }

        // Set tab selected listener if needed
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselection
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselection
            }
        })
    }

    private fun setupRecyclerView() {
        val closetItems = mutableListOf(
            data(1,1,1,1,1,3,"Item 1"),
            data(2,1,1,2,1,3,"item 2"),
            data(3,2,2,3,2,3,"item 3"),
            data(4,1,2,4,2,3,"item 4")
        )

        // Create and set up the adapter
        val adapter = ClosetAdapter(closetItems)
        recyclerView.adapter = adapter

        // Set the layout manager (GridLayoutManager with two columns)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
    }

    private fun setupView() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(context, AddClosetActivity::class.java))
        }
    }
}