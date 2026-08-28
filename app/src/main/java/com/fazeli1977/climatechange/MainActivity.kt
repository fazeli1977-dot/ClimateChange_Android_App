package com.fazeli1977.climatechange

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.fazeli1977.climatechange.worker.DataIngestWorker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnView = findViewById<Button>(R.id.btnViewSource)
        val btnFetch = findViewById<Button>(R.id.btnFetchNow)
        val btnChart = findViewById<Button>(R.id.btnOpenChart)
        val btnMap = findViewById<Button>(R.id.btnOpenMap)

        btnView.setOnClickListener {
            // Example: open an IPCC or NASA page inside the app
            val intent = Intent(this, InAppWebViewActivity::class.java)
            intent.putExtra("url", "https://climate.nasa.gov/")
            intent.putExtra("title", "NASA Climate")
            startActivity(intent)
        }

        btnFetch.setOnClickListener {
            // Enqueue the DataIngestWorker to fetch sample data now
            val request = OneTimeWorkRequestBuilder<DataIngestWorker>().build()
            WorkManager.getInstance(this).enqueue(request)
            Toast.makeText(this, "Data fetch started (background)", Toast.LENGTH_SHORT).show()
        }

        btnChart.setOnClickListener {
            startActivity(Intent(this, com.fazeli1977.climatechange.ui.ChartActivity::class.java))
        }

        btnMap.setOnClickListener {
            startActivity(Intent(this, com.fazeli1977.climatechange.ui.MapActivity::class.java))
        }
    }
}
