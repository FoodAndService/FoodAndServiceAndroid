package com.foodandservice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.foodandservice.util.extensions.CoreExtensions.showToast

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
                Log.i("QRScannerActivity", "Scan result: ${it.text}")
                finish()
            }
        }

        qrScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                showToast(getString(R.string.btn_qr_camera_error))
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