package com.example.tugasandroidadvanced.Fragment.WorkManager

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWork (
    context: Context,
    workParams: WorkerParameters
): Worker(context,workParams){
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val context: Context
    private var progress = 10

    companion object {
        var isStopped: Boolean = false
    }

    init{
        this.context = context
        setProgressAsync(Data.Builder().putInt("PROGRESS", 0).build())
        Companion.isStopped = false
    }

    override fun doWork(): Result {
        val inputData = inputData.getString("key")
        handler.post{
            Toast.makeText(
                context, "One Time Work Input Data: $inputData",
                Toast.LENGTH_SHORT
            ).show()
        }
        for (i in 1..5){
            if(Companion.isStopped){
                break
            }
            progress = i * 100/100
            handler.post{
                Log.d("TAG", i.toString())
                Toast.makeText(context, "One Time Work Data: $i",
                    Toast.LENGTH_SHORT).show()
            }
            try{
                setProgressAsync(Data.Builder().putInt("PROGRESS",
                    progress).build())
                Thread.sleep(5000)
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
        val outputData = Data.Builder().putString("Output", "Some_Output_Data").build()
        return Result.success(outputData)
    }
}