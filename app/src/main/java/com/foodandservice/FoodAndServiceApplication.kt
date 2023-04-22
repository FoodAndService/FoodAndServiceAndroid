package com.foodandservice

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.foodandservice.common.Constants
import com.foodandservice.di.databaseModule
import com.foodandservice.di.networkModule
import com.foodandservice.di.repositoryModule
import com.foodandservice.di.useCaseModule
import com.foodandservice.di.viewModelModule
import com.foodandservice.util.AndroidLoggingHandler
import com.stripe.android.PaymentConfiguration
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodAndServiceApplication : Application() {
    private val networkFlipperPlugin: NetworkFlipperPlugin by inject()

    override fun onCreate() {
        super.onCreate()

        setupLogging()
        setupDI()
        setupStripe()
        setupFlipper()
    }

    private fun setupLogging() {
        AndroidLoggingHandler.setup()
    }

    private fun setupDI() {
        startKoin {
            androidContext(this@FoodAndServiceApplication)
            modules(
                databaseModule, networkModule, repositoryModule, useCaseModule, viewModelModule
            )
        }
    }

    private fun setupStripe() {
        PaymentConfiguration.init(
            context = this, publishableKey = Constants.STRIPE_PK
        )
    }

    private fun setupFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                addPlugin(
                    InspectorFlipperPlugin(
                        this@FoodAndServiceApplication, DescriptorMapping.withDefaults()
                    )
                )
                addPlugin(DatabasesFlipperPlugin(this@FoodAndServiceApplication))
                addPlugin(SharedPreferencesFlipperPlugin(this@FoodAndServiceApplication))
                addPlugin(networkFlipperPlugin)
                start()
            }
        }
    }
}