package com.foodandservice.common

import com.foodandservice.R
import java.util.*

object Constants {
    val BottomBarVisibleFragments = listOf(R.id.homeFragment, R.id.favouritesFragment, R.id.ordersFragment)
    val FYS_COPYRIGHT_LABEL = "Food&Service © " + Calendar.getInstance().get(Calendar.YEAR)
    const val API_BASE_URL = "https://api-auth-dev.foodyservice.com/"
    const val API_KEY_HEADER = "x-api-key"
    const val API_KEY_VALUE = "secret_auth"
}