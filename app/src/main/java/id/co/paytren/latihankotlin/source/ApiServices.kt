package id.co.paytren.latihankotlin.source

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import id.co.paytren.latihankotlin.ConstantApp
import id.co.paytren.latihankotlin.source.dao.News
import id.co.paytren.latihankotlin.source.dao.Source
import id.co.paytren.latihankotlin.source.dao.User
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable

/**
 * Created by BayuWPP on 3/14/18.
 */

interface ApiServices {

    @POST("source")
    fun getNewsSource(@Body params: String) : Observable<List<Source>>

    @POST("news")
    fun getNews(@Body params: String) : Observable<List<News>>

    @POST("opt/")
    fun doLogin(@Body params:String) : Observable<User>


    object factory {
        private var retrofit: Retrofit? = null

        @Synchronized
        fun create(context: Context): ApiServices {
            if (retrofit == null) {

                val gson = GsonBuilder()
                        .setLenient()
                        .create()

                Log.e("ApiService", "statusRETROFIT:NULL")
                retrofit = Retrofit.Builder()
                        .baseUrl(ConstantApp.Api.BASE_URL)
                        .client(SelfSigningClientBuilder.createClient(context,ConstantApp.Api.BASE_URL))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }

            return retrofit!!.create(ApiServices::class.java)
        }
    }
}
