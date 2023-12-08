package com.example.arbikeworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class ARViewActivity : AppCompatActivity() {

    private lateinit var modelName: String
    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    lateinit var modelNode: ArModelNode

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arview)

        // Retrieve the clicked button information from the intent
        val whichProduct = intent.getStringExtra("productName")
        //openAR(modelName)
        if (whichProduct != null) {
            modelName = whichProduct
            // Inside your activity or fragment
            Toast.makeText(this, modelName, Toast.LENGTH_SHORT).show()
            openAR(modelName)
        }
        else{
            openAR("Bike01")
        }

        val fab: FloatingActionButton = findViewById(R.id.productInfoBtn)
        fab.setOnClickListener {

            val webPageUrl: String = when (whichProduct) {
                "Bike01" -> "https://www.yamaha-motor-india.com/yamaha-r15v4.html"
                "Bike02" -> "https://www.yamaha-motor-india.com/yamaha-fzs-fi-v4.html"
                "Bike03" -> "https://www.yamaha-motor-india.com/yamaha-mt-15-v2.html"
                "Scooter01" -> "https://www.yamaha-motor-india.com/yamaha-fascino125fi-new.html"
                "Scooter02" -> "https://www.yamaha-motor-india.com/yamaha-ray-zrstreetrally125fihybrid.html"
                "Scooter03" -> "https://www.yamaha-motor-india.com/yamaha-ray-zr125fihybrid.html"
                else -> "https://www.yamaha-motor-india.com/"
            }

            openWebView(webPageUrl)
        }
    }

    private fun openWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    private fun openAR(product: String){
        val modelName: String = when (product) {
            "Bike01" -> "models/sports_bike04.glb"
            "Bike02" -> "models/cyberpunk_bike03.glb"
            "Bike03" -> "models/2020_suzuki_sv650_motorcycle02.glb"
            "Scooter01" -> "models/vespa06.glb"
            "Scooter02" -> "models/vespa_cartoonish_lineart05.glb"
            "Scooter03" -> "models/2020_suzuki_sv650_motorcycle01.glb"
            else -> "models/cyberpunk_bike03.glb"
        }

        sceneView = findViewById(R.id.sceneView)
        placeButton = findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = modelName
            ){
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                placeButton.isGone
            }
        }
        sceneView.addChild(modelNode)
    }

    private fun placeModel(){
        modelNode.anchor()
        sceneView.planeRenderer.isVisible = false
    }
}