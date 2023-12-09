package com.example.tugasandroidadvanced.Fragment.WorkManager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.work.*
import com.example.tugasandroidadvanced.databinding.FragmentWorkBinding
import java.util.concurrent.TimeUnit

class WorkFragment : Fragment() {
    private lateinit var binding: FragmentWorkBinding
    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
    private lateinit var context: Context
    private lateinit var constraints: Constraints

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkBinding.inflate(inflater, container, false)
        context = requireContext() // Gunakan requireContext() untuk mendapatkan Context secara aman
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi oneTimeWorkRequest di sini atau di tempat lain yang sesuai
        val data: Data = Data.Builder().putString("key", "Yes").build()
        constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWork::class.java)
            .addTag("onetimework" + System.currentTimeMillis())
            .setConstraints(constraints)
            .setInputData(data)
            .setInitialDelay(1, TimeUnit.SECONDS)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.SECONDS
            )
            .build()

        binding.btnStartWork.setOnClickListener {
            Toast.makeText(context, "Start One Time Work!", Toast.LENGTH_SHORT).show()

            // Gunakan oneTimeWorkRequest yang sudah diinisialisasi
            WorkManager.getInstance(requireContext()).enqueue(oneTimeWorkRequest)
        }

        binding.btnStopWork.setOnClickListener {
            if (::oneTimeWorkRequest.isInitialized) {
                val operation: Operation =
                    WorkManager.getInstance(requireContext()).cancelWorkById(oneTimeWorkRequest.id)
                MyWork.isStopped = true
                Toast.makeText(
                    context,
                    "Stopped One Time Work!" + operation.state,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "oneTimeWorkRequest is not initialized",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
