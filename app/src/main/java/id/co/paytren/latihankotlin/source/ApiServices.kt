package id.co.paytren.latihankotlin.source

import android.util.Log
import com.google.gson.GsonBuilder
import id.co.paytren.latihankotlin.ConstantApp
import id.co.paytren.latihankotlin.source.dao.News
import id.co.paytren.latihankotlin.source.dao.Source
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit
import io.reactivex.Observable



/**
 * Created by BayuWPP on 3/14/18.
 */

interface ApiServices {

    @POST("source")
    fun getNewsSource(@Body params: String) : Observable<List<Source>>

    @POST("news")
    fun getNews(@Body params: String) : Observable<List<News>>


    object factory {
        private var retrofit: Retrofit? = null

        @Synchronized
        fun create(): ApiServices {
            if (retrofit == null) {
                val builder = OkHttpClient().newBuilder()
                builder.readTimeout(20, TimeUnit.SECONDS)
                builder.connectTimeout(10, TimeUnit.SECONDS)
                builder.writeTimeout(20, TimeUnit.SECONDS)

                val client = builder.build()

                val gson = GsonBuilder()
                        .setLenient()
                        .create()

                Log.e("ApiService", "statusRETROFIT:NULL")
                retrofit = Retrofit.Builder()
                        .baseUrl(ConstantApp.Api.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }

            return retrofit!!.create(ApiServices::class.java)
        }
    }
}
