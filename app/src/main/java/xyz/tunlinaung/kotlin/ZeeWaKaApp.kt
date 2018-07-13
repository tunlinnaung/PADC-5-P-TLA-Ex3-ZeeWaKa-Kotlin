package xyz.tunlinaung.kotlin

import android.app.Application
import xyz.tunlinaung.kotlin.data.model.ZeeWaKaModel

class ZeeWaKaApp : Application() {

    companion object {
        const val LOG_TAG = "ZeeWaKa"
    }

    override fun onCreate() {
        super.onCreate()
        ZeeWaKaModel.initModel(applicationContext)
    }

}