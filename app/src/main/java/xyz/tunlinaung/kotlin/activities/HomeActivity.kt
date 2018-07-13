package xyz.tunlinaung.kotlin.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_home.*
import xyz.tunlinaung.kotlin.adapters.HealthNewsAdapter
import xyz.tunlinaung.kotlin.mvp.presenters.HealthInfoNewsPresenter
import xyz.tunlinaung.kotlin.mvp.views.HealthInfoNewsView
import xyz.tunlinaung.zeewaka_kotlin.R
import android.content.Intent
import android.net.Uri


class HomeActivity : BaseActivity(), HealthInfoNewsView {

    private var mHealthInfoAdapter: HealthNewsAdapter? = null
    private var mPresenter: HealthInfoNewsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mPresenter = ViewModelProviders.of(this).get(HealthInfoNewsPresenter::class.java!!)
        mPresenter!!.initPresenter(this)

        rvNews.mEmptyView = vpEmptyNews
        mHealthInfoAdapter = HealthNewsAdapter(applicationContext, this)
        rvNews.layoutManager = LinearLayoutManager(applicationContext)
        rvNews.adapter = mHealthInfoAdapter

        swipeRefreshLayout.isRefreshing = true

        mPresenter!!.mHealthInfoListLD!!.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            mHealthInfoAdapter!!.appendNewData(it!!)
        })

        swipeRefreshLayout.setOnRefreshListener {
            val healthAdapterVal = mHealthInfoAdapter
            healthAdapterVal!!.clearData()
            mPresenter!!.loadHealthInfo()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showWebView(url: String) {
        //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.WEBSITE_ADDRESS, url)
        startActivity(intent)
    }

    override fun displayErrorMsg(errorMsg: String) {
        Snackbar.make(rvNews, errorMsg, Snackbar.LENGTH_INDEFINITE).show()
    }
}
