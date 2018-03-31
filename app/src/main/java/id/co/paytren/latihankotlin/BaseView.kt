package id.co.paytren.latihankotlin

import android.content.Context

/**
 * Created by BayuWPP on 3/13/18.
 */

interface BaseView {
    fun onAttach()
    fun getContext(): Context
}
