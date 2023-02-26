package com.foodandservice

import android.app.Application
import com.foodandservice.common.Constants
import com.foodandservice.di.*
import com.foodandservice.util.AndroidLoggingHandler
import com.stripe.android.PaymentConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodAndServiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidLoggingHandler.setup()

        startKoin {
            androidContext(this@FoodAndServiceApplication)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }

        PaymentConfiguration.init(
            context = this,
            publishableKey = Constants.STRIPE_PK
        )
    }
}