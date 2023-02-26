package com.foodandservice.common

import java.util.*

object Constants {
    const val API_BASE_URL = "https://api-auth-dev.foodyservice.com/"
    const val API_KEY_HEADER = "x-api-key"
    const val API_KEY_VALUE = "secret_auth"
    const val STRIPE_PK =
        "pk_live_51LfrDLAmb0uhaPWFZmAwzW3M37cpwGyJo20vSb4B6sZ09xE9aMn3E8zPrZt27qRjj6fLxgAogjt7X2dKzR3UDsyw00GDZSPkOx"
    val FYS_COPYRIGHT_LABEL = "Food&Service © " + Calendar.getInstance().get(Calendar.YEAR)
}