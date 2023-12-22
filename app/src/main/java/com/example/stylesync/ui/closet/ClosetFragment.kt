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
import com.example.stylesync.databinding.FragmentClosetBinding
import com.example.stylesync.list.ImportantLists

class ClosetFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentClosetBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val closetViewModel = ViewModelProvider(this).get(ClosetViewModel::class.java)

        _binding = FragmentClosetBinding.inflate(inflater, container, false)
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
        for (category in ImportantLists.categoriesList) {
            tabLayout.addTab(tabLayout.newTab().setText(category.category))
        }

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
            data(1,1,1,"Blue","Casual","",1, 1, 1, "Jacket"),
            data(1,1,1,"Blue","Casual","",2, 1, 1, "Jacket"),
            data(1,1,1,"Blue","Casual","",3, 1, 1, "Jacket"),
            data(1,1,1,"Bulu","Casual","",4, 1, 1, "Jacket"),
            data(1,1,1,"Blue","Casual","",3, 1, 1, "Jacket"),
            data(1,1,1,"Bulu","Casual","",4, 1, 1, "Jacket"),
            data(1,1,1,"Blue","Casual","",3, 1, 1, "Jacket"),
            data(1,1,1,"Bulu","Casual","",4, 1, 1, "Jacket")
        )

        val adapter = ClosetAdapter(closetItems)
        recyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
    }

    private fun setupView() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(context, AddClosetActivity::class.java))
        }
    }
}