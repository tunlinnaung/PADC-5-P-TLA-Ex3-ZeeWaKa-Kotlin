package xyz.tunlinaung.kotlin.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*
import xyz.tunlinaung.kotlin.adapters.HealthNewsAdapter
import xyz.tunlinaung.kotlin.components.SmartScrollListener
import xyz.tunlinaung.kotlin.mvp.presenters.HealthInfoNewsPresenter
import xyz.tunlinaung.kotlin.mvp.views.HealthInfoNewsView
import xyz.tunlinaung.zeewaka_kotlin.R


class HomeActivity : BaseActivity(), HealthInfoNewsView {

    private lateinit var mHealthInfoAdapter: HealthNewsAdapter
    private lateinit var mPresenter: HealthInfoNewsPresenter
    private lateinit var mSmartScrollListener: SmartScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mPresenter = ViewModelProviders.of(this).get(HealthInfoNewsPresenter::class.java!!)
        mPresenter.initPresenter(this)

        rvNews.mEmptyView = vpEmptyNews
        mHealthInfoAdapter = HealthNewsAdapter(applicationContext, this)
        rvNews.layoutManager = LinearLayoutManager(applicationContext)
        rvNews.adapter = mHealthInfoAdapter
        mSmartScrollListener = SmartScrollListener(object : SmartScrollListener.OnSmartScrollListener {
            override fun onListEndReach() {
                Snackbar.make(rvNews, "Loading more data.", Snackbar.LENGTH_LONG).show()
                swipeRefreshLayout.isRefreshing = true
                mPresenter.onPullRefresh()
            }
        })
        rvNews.addOnScrollListener(mSmartScrollListener)

        swipeRefreshLayout.isRefreshing = true

        mPresenter.mHealthInfoListLD.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            mHealthInfoAdapter.setNewData(it!!)
        })

        mPresenter.mErrorLD.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = false
            Snackbar.make(rvNews, it.toString(), Snackbar.LENGTH_INDEFINITE).show()
        })

        swipeRefreshLayout.setOnRefreshListener {
            //val healthAdapterVal = mHealthInfoAdapter
            //healthAdapterVal.clearData()
            mPresenter.onPullRefresh()
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
