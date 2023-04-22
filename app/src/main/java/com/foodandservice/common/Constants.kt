package com.foodandservice.common

import java.util.Calendar

object Constants {
    const val FYS_AUTH_BASE_URL = "https://api-auth-dev.foodyservice.com/"
    const val FYS_CUSTOMER_BASE_URL = "https://api-customer-dev.foodyservice.com/"
    const val FYS_STRIPE_BASE_URL = "https://api.stripe.com/"
    const val API_KEY_HEADER = "x-api-key"
    const val API_KEY_VALUE = "secret_auth"
    const val DEFAULT_LATITUDE = 40.416729
    const val DEFAULT_LONGITUDE = -3.703339
    const val STRIPE_CUSTOMER_ID = "cus_NLRsVvpCow25yB"
    const val STRIPE_PK =
        "pk_test_51LfrDLAmb0uhaPWFyX3Doz63luIoaiUixnMOUjnRoUPMQM4Ku3hLw7sUWdYauN5T6aEMVJWSRNZmxwfzQwpHgOLz00n6NnLl8T"
    const val STRIPE_SK =
        "sk_test_51LfrDLAmb0uhaPWFM4pJZAJOeBnvjFxYVNd3KdEiRG4zBTDLR4GTMxlbpl2ijh99X9zxC32Eb7UyJOfkA0aElNNh00uGSoytgX"
    val FYS_COPYRIGHT_LABEL = "Food&Service © " + Calendar.getInstance().get(Calendar.YEAR)
}