package id.co.paytren.latihankotlin.news

import android.widget.Toast
import id.co.paytren.latihankotlin.source.ApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by BayuWPP on 3/31/18.
 */
class NewsPresenter : NewsContract.Presenter{
    lateinit var view: NewsContract.View

    override fun getNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        view.showProgress()
        var disposable : Disposable = ApiServices.factory.create().getNews("").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                { news ->
                    view.showNews(news)
                },
                { error ->
//                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                    error.message?.let { view.showErrorMessage(it) }
                }
        )
    }

    override fun onAttach(view: NewsContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        this.view = view
    }

    override fun onDetach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

}