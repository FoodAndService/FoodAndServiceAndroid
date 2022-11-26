package com.foodandservice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*

class QRScannerActivity : AppCompatActivity() {
    private lateinit var qrScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        startScan()
    }

    private fun startScan() {
        val qrScannerView: CodeScannerView = findViewById(R.id.qrCodeScanner)

        qrScanner = CodeScanner(this, qrScannerView)
        qrScanner.camera = CodeScanner.CAMERA_BACK
        qrScanner.formats = CodeScanner.ALL_FORMATS
        qrScanner.autoFocusMode = AutoFocusMode.SAFE
        qrScanner.scanMode = ScanMode.SINGLE
        qrScanner.isAutoFocusEnabled = true
        qrScanner.isFlashEnabled = true

        qrScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        qrScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(
                    this,
                    "Camera initialization error: ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        qrScannerView.setOnClickListener {
            qrScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        if (::qrScanner.isInitialized) {
            qrScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::qrScanner.isInitialized) {
            qrScanner.releaseResources()
        }
        super.onPause()
    }
}