package xyz.tunlinaung.kotlin.components

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

class SmartRecyclerView : RecyclerView {

    open var mEmptyView: View? = null

    private val dataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(dataObserver)

        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(dataObserver)
        checkIfEmpty()
    }

    fun setEmptyView(emptyView: View) {
        mEmptyView = emptyView
    }

    /**
     * check if adapter connected to SRV is empty. If so, show emptyView.
     */
    private fun checkIfEmpty() {
        /*
        if(mEmptyView != null && adapter.itemCount == 0) {
            mEmptyView!!.visibility = View.VISIBLE
        } else {
            mEmptyView!!.visibility = View.GONE
        }

        mEmptyView?.let {
            it.visibility = if (adapter.itemCount == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        mEmptyView?.visibility.let {
            if (adapter.itemCount == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        */

        mEmptyView?.visibility = if (adapter.itemCount == 0) {
            View.VISIBLE
        } else {
            View.GONE
        }


        /*
        val isEmpty = adapter.itemCount == 0
        //TODO to clean duplicate code
        mEmptyView?.let {
            it.visibility = if (isEmpty) View.VISIBLE else View.INVISIBLE
            visibility = if (isEmpty) View.INVISIBLE else View.VISIBLE
        }
        */
    }
}