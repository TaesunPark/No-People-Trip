package com.test.mosun.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
//import android.support.v4.app.ActivityCompat
//import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.priyankvasa.android.cameraviewex.Modes
import com.radslow.tflitedemo.Classifier
import com.test.mosun.R
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    private lateinit var classifier: Classifier

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        classifier = Classifier(assets)

        if (!canUseCamera()) {
            requestCameraPermissions()
        } else {
            setupCamera()
        }
    }

    private fun requestCameraPermissions() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_CODE
        )
    }


    @SuppressLint("MissingPermission", "LongLogTag")
    private fun setupCamera() {
        camera.addPictureTakenListener {
            val recognitions = classifier.recognize(it.data)
            Log.d("cameraActivyt-recognition :",recognitions[0].toString());
            val txt = recognitions.joinToString(separator = "\n")
            Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
//            onDestroy();
            finish();
//            AsyncTask.execute {
//                val recognitions = classifier.recognize(it.data)
//                Log.d("cameraActivyt-recognition :",recognitions[0].toString());
//                val txt = recognitions.joinToString(separator = "\n")
//                Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
//                onDestroy();
//                finish();
////                runOnUiThread {
//////                    Log.d("cameraActivyt-recognition :",txt);
////                    Toast.makeText(this, txt, Toast.LENGTH_LONG).show()
////
////                }
//            }
        }

        capturePhoto.setOnClickListener {
            camera.capture()
        }

        button.setOnClickListener {
            camera.facing = when (camera.facing) {
                Modes.Facing.FACING_BACK -> Modes.Facing.FACING_FRONT
                else -> Modes.Facing.FACING_BACK
            }
        }

    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (REQUEST_CAMERA_CODE == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupCamera()
            } else {
                Toast.makeText(this, "App needs camera in order to work.", Toast.LENGTH_LONG).show()
                requestCameraPermissions()
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if (canUseCamera()) {
            camera.start()
        }
    }

    override fun onPause() {
        if (canUseCamera()) {
            camera.stop()
        }
        super.onPause()
    }

    override fun onDestroy() {
        if (canUseCamera()) {
            camera.destroy()
        }

        super.onDestroy()
    }

    private fun canUseCamera() =
            ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val REQUEST_CAMERA_CODE = 1
    }
}
