package id.co.paytren.latihankotlin.login

import id.co.paytren.latihankotlin.BasePresenter
import id.co.paytren.latihankotlin.BaseView
import id.co.paytren.latihankotlin.source.dao.User

/**
 * Created by BayuWPP on 4/7/18.
 */
class LoginContract{
    interface View : BaseView {
        fun showProgress()
        fun hideProgress()
        fun showSuccessLogin(user: User)
        fun showErrorLogin(message: String)
    }

    interface Presenter : BasePresenter<View>{
        fun doLogin(iduser: String, pass: String)
    }
}