package xyz.tunlinaung.kotlin.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import xyz.tunlinaung.kotlin.mvp.views.BaseView

// TODO("how to extends BaseView from T") - should use in/out?
open class BasePresenter<T : BaseView> : ViewModel() {

    val mView: T? = null
    var mErrorLD: MutableLiveData<String> = MutableLiveData()

    open fun initPresenter(view: T? = mView) {
        mErrorLD = MutableLiveData()
    }

}