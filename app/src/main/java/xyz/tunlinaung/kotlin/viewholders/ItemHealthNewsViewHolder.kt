package xyz.tunlinaung.kotlin.viewholders

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item_health_news.view.*
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO
import xyz.tunlinaung.kotlin.mvp.views.HealthInfoNewsView

class ItemHealthNewsViewHolder(itemView: View, private var view: HealthInfoNewsView) : BaseViewHolder<HealthCareInfoVO>(itemView) {

    override fun setData(data: HealthCareInfoVO, position: Int) {
        mData = data

        itemView!!.tvHealthNewsTitle!!.text = data.title

        Glide.with(itemView.context)
                .load(data.image)
                .into(itemView.ivNewsHealthImage)

        /* No Info type at 1st index of json array
        if (!data.infoType.isEmpty()) {
            itemView.tvNewsCategory!!.text = data.infoType
        } else {
            itemView.tvNewsCategory!!.text = "General"
        }
        */

        if (data.author != null) {
            itemView.tvNewsAuthor!!.text = data.author.authorName
        }

        itemView.tvNewsDate!!.text = data!!.publishedDate

        itemView.ivNewsHealthImage.setOnClickListener { view.showWebView(mData!!.completeUrl) }
    }

    override fun onClick(p0: View?) {
        view.showWebView(mData!!.completeUrl)
    }

}