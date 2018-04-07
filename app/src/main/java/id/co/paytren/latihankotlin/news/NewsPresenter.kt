package id.co.paytren.latihankotlin.news

import android.widget.Toast
import id.co.paytren.latihankotlin.source.ApiServices
import id.co.paytren.latihankotlin.source.DisposableManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by BayuWPP on 3/31/18.
 */
class NewsPresenter : NewsContract.Presenter {
    lateinit var view: NewsContract.View

    override fun getNews() {
        view.showProgress()
        var disposable: Disposable = ApiServices.factory.create(view.getContext()).getNews("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                        { news ->
                                view.hideProgress()
                                view.showNews(news)
                        },
                        { error ->
                            //                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                                view.hideProgress()
                                error.message?.let { view.showErrorMessage(it) }
                        }
                )
        DisposableManager.Operation.add(disposable)
    }

    override fun onAttach(view: NewsContract.View) {
        this.view = view
    }

    override fun onDetach() {
        DisposableManager.Operation.dispose()
    }

}