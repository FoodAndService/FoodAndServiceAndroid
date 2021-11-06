package com.foodandservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FoodAndServiceActivity : AppCompatActivity() {
    //var smsBroadcastReceiver: SmsBroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodandservice)
        //SmsRetriever.getClient(this).startSmsUserConsent(null)
    }
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == RESULT_OK && data != null)
            Log.i("MIERDA", data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE).toString())
    }

    private fun registerBroadcastReceiver() {
        smsBroadcastReceiver = SmsBroadcastReceiver()
        smsBroadcastReceiver!!.smsListener = object : SmsBroadcastReceiver.SmsListener {
            override fun onSuccess(intent: Intent?) {
                startActivityForResult(intent, 2)
            }

            override fun onFailure() {}
        }

        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        registerBroadcastReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(smsBroadcastReceiver)
    }
    */
}