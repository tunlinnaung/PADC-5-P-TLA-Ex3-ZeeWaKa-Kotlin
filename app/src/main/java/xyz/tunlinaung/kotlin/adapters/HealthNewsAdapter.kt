package xyz.tunlinaung.kotlin.adapters

import android.content.Context
import android.view.ViewGroup
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO
import xyz.tunlinaung.kotlin.mvp.views.HealthInfoNewsView
import xyz.tunlinaung.kotlin.viewholders.ItemHealthNewsViewHolder
import xyz.tunlinaung.zeewaka_kotlin.R

class HealthNewsAdapter(context: Context, private val view: HealthInfoNewsView) : BaseRecyclerAdapter<ItemHealthNewsViewHolder, HealthCareInfoVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHealthNewsViewHolder {
        val newsItemView = mLayoutInflator.inflate(R.layout.view_item_health_news, parent, false)
        return ItemHealthNewsViewHolder(newsItemView, view)
    }

}