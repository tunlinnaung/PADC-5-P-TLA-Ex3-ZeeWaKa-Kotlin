package xyz.tunlinaung.kotlin.data.model

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.tunlinaung.kotlin.ZeeWaKaApp
import xyz.tunlinaung.kotlin.common.AppConstant
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO



class ZeeWaKaModel(context: Context) : BaseModel(context) {

    companion object {
        private var INSTANCE: ZeeWaKaModel? = null
        fun getInstance(): ZeeWaKaModel {
            if (INSTANCE == null) {
                throw RuntimeException("ZeeWaKaModel is being invoked before initializing.")
            }

            val i = INSTANCE
            return i!!
        }
        fun initModel(context: Context) {
            INSTANCE = ZeeWaKaModel(context)
        }
    }

    fun startLoadingHealthcareInfo(healthcareListLD: MutableLiveData<List<HealthCareInfoVO>>,
                                   errorMessageLD: MutableLiveData<String>) {

        mTheApi!!.getHealthcareInfo(AppConstant.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null && it!!.healthcareInfo != null && it!!.healthcareInfo.size > 0) {
                                healthcareListLD.value = it!!.healthcareInfo
                                persistHealthcareInfoList(it!!.healthcareInfo)
                            } else {
                                errorMessageLD.value = "Empty Health News"
                            }
                        }, {
                            errorMessageLD.value = it.message
                        }
                )

    }

    private fun persistHealthcareInfoList(healthcareList: List<HealthCareInfoVO>) {
        val insertedHealthcareInfos = mTheDb!!.healthCareDao().insertHealthcareInfo(healthcareList)
        Log.d(ZeeWaKaApp.LOG_TAG, "insertedHealthcareInfos : $insertedHealthcareInfos")

    }

}