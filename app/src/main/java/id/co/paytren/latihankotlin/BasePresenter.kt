package id.co.paytren.latihankotlin

/**
 * Created by BayuWPP on 3/13/18.
 */
interface BasePresenter<T : BaseView> {
    fun onAttach(view:T)
    fun onDetach()
}