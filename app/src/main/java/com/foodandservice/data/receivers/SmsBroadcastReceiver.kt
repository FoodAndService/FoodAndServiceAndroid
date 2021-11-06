package com.foodandservice.data.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsBroadcastReceiver : BroadcastReceiver() {
    var smsListener: SmsListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("MIERDA", "onReceive()")
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
            val extras = intent.extras
            val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (smsRetrieverStatus.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val messageIntent =
                        extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    smsListener?.onSuccess(messageIntent)
                }
                CommonStatusCodes.TIMEOUT -> {
                    smsListener?.onFailure()
                }
            }
        }
    }

    interface SmsListener {
        fun onSuccess(intent: Intent?)
        fun onFailure()
    }
}