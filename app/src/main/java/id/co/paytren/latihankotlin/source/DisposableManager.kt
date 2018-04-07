package id.co.paytren.latihankotlin.source

import id.co.paytren.latihankotlin.source.DisposableManager.Companion.compositeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by BayuWPP on 4/7/18.
 */
class DisposableManager {
    companion object {
        var compositeDisposable : CompositeDisposable? = null
    }

    object Operation {
        @JvmStatic
        fun add(disposable: Disposable) {
            getCompositeDisposable().add(disposable)
        }

        @JvmStatic
        fun dispose() {
            getCompositeDisposable().dispose()
        }

        @JvmStatic
        fun clear() {
            getCompositeDisposable().clear()
        }

        @JvmStatic
        fun getCompositeDisposable(): CompositeDisposable {
            if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
                compositeDisposable = CompositeDisposable()
            }
            return compositeDisposable as CompositeDisposable
        }
    }






    fun DisposableManager() {}
}