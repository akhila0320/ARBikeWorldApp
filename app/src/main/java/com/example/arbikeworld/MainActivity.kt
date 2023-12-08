package com.example.arbikeworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.openWebPage)
        fab.setOnClickListener {
            val webPageUrl = "https://www.yamaha-motor-india.com/"
            openWebView(webPageUrl)
        }

        val bike01: ImageView = findViewById(R.id.bike01)
        val bike02: ImageView = findViewById(R.id.bike02)
        val bike03: ImageView = findViewById(R.id.bike03)
        val scooter01: ImageView = findViewById(R.id.scooter01)
        val scooter02: ImageView = findViewById(R.id.scooter02)
        val scooter03: ImageView = findViewById(R.id.scooter03)

        bike01.setOnClickListener {
            openARView("Bike01")
        }
        bike02.setOnClickListener {
            openARView("Bike02")
        }
        bike03.setOnClickListener {
            openARView("Bike03")
        }
        scooter01.setOnClickListener {
            openARView("Scooter01")
        }
        scooter02.setOnClickListener {
            openARView("Scooter02")
        }
        scooter03.setOnClickListener {
            openARView("Scooter03")
        }
    }

    private fun openWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    private fun openARView(clickedButton: String) {
        val intent = Intent(this, ARViewActivity::class.java)
        intent.putExtra("productName", clickedButton)
        startActivity(intent)
    }
}