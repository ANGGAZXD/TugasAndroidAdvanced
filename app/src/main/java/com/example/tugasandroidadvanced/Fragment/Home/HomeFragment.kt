package com.example.tugasandroidadvanced.Fragment.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasandroidadvanced.R


class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<PekerjaanData>()
    private lateinit var adapter: PekerjaanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPekerjaan)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = PekerjaanAdapter(mList)
        addDataToList()
        recyclerView.adapter = adapter

        return view
    }

    private fun addDataToList() {
        mList.add(PekerjaanData("1. Guru"))
        mList.add(PekerjaanData("2. Polisi"))
        mList.add(PekerjaanData("3. Pelaut"))
        mList.add(PekerjaanData("4. Tentara"))
        mList.add(PekerjaanData("5. Politikus"))

    }

}