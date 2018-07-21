package xyz.tunlinaung.kotlin.mvp.presenters

import xyz.tunlinaung.kotlin.mvp.views.HealthInfoNewsView
import android.arch.lifecycle.MutableLiveData
import xyz.tunlinaung.kotlin.data.model.ZeeWaKaModel
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO


class HealthInfoNewsPresenter : BasePresenter<HealthInfoNewsView>() {

    //TODO how to clear null check (can't lateinit) how to lazy
    lateinit var mHealthInfoListLD: MutableLiveData<List<HealthCareInfoVO>>

    override fun initPresenter(view: HealthInfoNewsView?) {
        super.initPresenter(view)
        mHealthInfoListLD = MutableLiveData()

        loadHealthInfo()
    }

    fun loadHealthInfo() {
        ZeeWaKaModel.getInstance().startLoadingHealthcareInfo(mHealthInfoListLD, mErrorLD)
    }

    fun onPullRefresh() {
        loadHealthInfo()
    }

}