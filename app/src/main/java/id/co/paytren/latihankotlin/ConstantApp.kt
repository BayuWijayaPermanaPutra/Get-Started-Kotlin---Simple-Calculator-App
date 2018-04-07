package id.co.paytren.latihankotlin

/**
 * Created by BayuWPP on 3/13/18.
 */
class ConstantApp {

    object Data {
        @JvmStatic
        val TYPE_RANDOM_NUM = 0;
        @JvmStatic
        val TYPE_RANDOM_OPR = 1;

        @JvmStatic
        val TYPE_HITUNG_TAMBAH = 0;
        @JvmStatic
        val TYPE_HITUNG_KURANG = 1;
        @JvmStatic
        val TYPE_HITUNG_KALI = 2;
        @JvmStatic
        val TYPE_HITUNG_BAGI = 3;
    }

    object Api {
        @JvmStatic
        val BASE_URL = "https://api.paytren.co.id/"
    }
}