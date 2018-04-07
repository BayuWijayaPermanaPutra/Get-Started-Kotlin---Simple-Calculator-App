package id.co.paytren.latihankotlin.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import id.co.paytren.latihankotlin.R
import id.co.paytren.latihankotlin.source.dao.User

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var presenter: LoginContract.Presenter


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showSuccessLogin(user: User) {

    }

    override fun showErrorLogin(message: String) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun onAttach() {
        presenter = LoginPresenter()
        presenter.onAttach(this)
    }

    override fun getContext(): Context {
        return this@LoginActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
