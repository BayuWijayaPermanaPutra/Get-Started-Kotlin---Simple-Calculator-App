package id.co.paytren.latihankotlin.source

import android.content.Context
import id.co.paytren.latihankotlin.R
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by BayuWPP on 4/2/18.
 */
class SelfSigningClientBuilder {
    companion object {
        fun createClient(context: Context, baseUrl: String): OkHttpClient? {
            var client: OkHttpClient? = null
            var cf: CertificateFactory? = null
            var cert: InputStream?
            var ca: Certificate
            var sslContext: SSLContext?
            var exception:String?=""
            try {
                cf = CertificateFactory.getInstance("X.509")

                cert = context.resources.openRawResource(R.raw.__mytreni_com)
                if(baseUrl.equals("paytren.co.id"))
                    cert = context.resources.openRawResource(R.raw._paytren_co_id)

                ca = cf.generateCertificate(cert)
                cert.close()

                var keyStoreType : String = KeyStore.getDefaultType()
                var keyStore : KeyStore = KeyStore.getInstance(keyStoreType)
                keyStore.load(null,null)
                keyStore.setCertificateEntry("ca", ca)

                var tmfAlgorithm : String = TrustManagerFactory.getDefaultAlgorithm()
                var tmf : TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
                tmf.init(keyStore)

                sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, tmf.trustManagers,null)

                client = OkHttpClient.Builder()
                        .sslSocketFactory(sslContext.socketFactory)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build()

            } catch (e: KeyStoreException) {
                exception += e.message
            } catch (e: CertificateException) {
                exception += e.message
            } catch (e: NoSuchAlgorithmException) {
                exception += e.message
            } catch (e: IOException){
                exception += e.message
            } catch (e: KeyManagementException) {
                exception += e.message
            }

            return client
        }
    }
}