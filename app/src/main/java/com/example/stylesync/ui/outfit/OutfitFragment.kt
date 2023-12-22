package com.example.stylesync.ui.outfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stylesync.data.remote.response.data
import com.example.stylesync.databinding.FragmentOutfitBinding
import com.example.stylesync.list.ImportantLists
import com.example.stylesync.ui.closet.ClosetAdapter
import com.google.android.material.tabs.TabLayout

class OutfitFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentOutfitBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val outfitViewModel =
            ViewModelProvider(this).get(OutfitViewModel::class.java)

        _binding = FragmentOutfitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tabLayout = binding.tabLayout
        setupTabs()

        recyclerView = binding.recyclerView
        setupRecyclerView()
        return root
    }

    private fun setupTabs() {
        for (occupation in ImportantLists.occupationList) {
            tabLayout.addTab(tabLayout.newTab().setText(occupation.occupation))
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
            data(1,1,1,"Blue","Casual","",1, 1, 1, "Casual Outfit"),
            data(1,1,1,"Blue","Casual","",2, 1, 1, "Casual Outfit"),
            data(1,1,1,"Blue","Casual","",3, 1, 1, "Casual Outfit"),
            data(1,1,1,"Bulu","Casual","",4, 1, 1, "Casual Outfit"),
            data(1,1,1,"Blue","Casual","",3, 1, 1, "Casual Outfit"),
            data(1,1,1,"Bulu","Casual","",4, 1, 1, "Casual Outfit"),
            data(1,1,1,"Blue","Casual","",3, 1, 1, "Casual Outfit"),
            data(1,1,1,"Bulu","Casual","",4, 1, 1, "Casual Outfit")
        )

        val adapter = ClosetAdapter(closetItems)
        recyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.layoutManager = layoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}