package id.co.paytren.latihankotlin.news

import id.co.paytren.latihankotlin.BasePresenter
import id.co.paytren.latihankotlin.BaseView
import id.co.paytren.latihankotlin.source.dao.News

/**
 * Created by BayuWPP on 3/31/18.
 */
class NewsContract{
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun showNews(list: List<News>)
        fun showErrorMessage(message: String)
    }

    interface Presenter : BasePresenter<View>{
        fun getNews()
    }
}