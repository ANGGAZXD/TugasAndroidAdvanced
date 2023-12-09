package com.example.tugasandroidadvanced.Fragment.Game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tugasandroidadvanced.R
import com.example.tugasandroidadvanced.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var myViewModel: MyViewModel
    private lateinit var binding: FragmentGameBinding
    private lateinit var textCounter: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCounter = view.findViewById(R.id.textViewCounter)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.textViewCounter.text = myViewModel.getInitialCounter().toString()
        binding.buttonIncrement.setOnClickListener {
            binding.textViewCounter.text = myViewModel.getUpdateCounter().toString()
        }
    }
}
