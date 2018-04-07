package id.co.paytren.latihankotlin.login

import com.google.gson.Gson
import id.co.paytren.latihankotlin.source.ApiServices
import id.co.paytren.latihankotlin.source.DisposableManager
import id.co.paytren.latihankotlin.source.dao.LoginParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by BayuWPP on 4/7/18.
 */
class LoginPresenter : LoginContract.Presenter{

    lateinit var view : LoginContract.View

    override fun doLogin(iduser: String, pass: String) {
        view.showProgress()
        var loginParams: LoginParams = LoginParams()

        loginParams.user = iduser
        loginParams.password = pass

        var disposable: Disposable = ApiServices.factory.create(view.getContext()).doLogin(Gson().toJson(loginParams))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                        { user ->
                            view.hideProgress()
                            view.showSuccessLogin(user)
                        },
                        { error ->
                            //                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            view.hideProgress()
                            error.message?.let { view.showErrorLogin(it) }
                        }
                )
        DisposableManager.Operation.add(disposable)
    }

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onDetach() {
        DisposableManager.Operation.dispose()
    }
}