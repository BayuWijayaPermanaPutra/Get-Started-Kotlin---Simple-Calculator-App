package id.co.paytren.latihankotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast

//ini untuk dapat memanggil varible widget secara langsung di kotlin
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var typeHitung: Int = 0

    override fun onClick(p0: View?) {
        when (p0) {
            main_btn_hitung -> {
                try {
                    main_tv_result.setText(Math.round(hitungResult(typeHitung)).toString())
                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                    main_tv_result.setText("Undefined")
                }
            }
            main_btn_kirimhasil -> {
                if (main_tv_result.text.equals("Undefined") || main_tv_result.text.isEmpty()) {
                    Toast.makeText(this, "Result is empty", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("result", main_tv_result.text)
                    startActivity(intent)
                }
            }
            main_btn_random -> {
                main_et_num1.setText(getRandomNumber(ConstantApp.Data.TYPE_RANDOM_NUM).toString())
                main_et_num2.setText(getRandomNumber(ConstantApp.Data.TYPE_RANDOM_NUM).toString())
                var opr = getRandomNumber(ConstantApp.Data.TYPE_RANDOM_OPR)
                when (opr) {
                    ConstantApp.Data.TYPE_HITUNG_TAMBAH -> setPlusOpr()

                    ConstantApp.Data.TYPE_HITUNG_KURANG -> setMinOpr()

                    ConstantApp.Data.TYPE_HITUNG_KALI -> setKaliOpr()

                    ConstantApp.Data.TYPE_HITUNG_BAGI -> setBagiOpr()
                }
                try {
                    main_tv_result.setText(Math.round(hitungResult(opr)).toString())
                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                    main_tv_result.setText("Undefined")
                }
            }

        }
    }

    fun getRandomNumber(type: Int): Int {
        var rand: Random = Random()
        when (type) {
            ConstantApp.Data.TYPE_RANDOM_NUM -> return rand.nextInt(51)
            ConstantApp.Data.TYPE_RANDOM_OPR -> return rand.nextInt(4)
        }
        return 0
    }

    @Throws
    fun hitungResult(typeHitung: Int): Double {
        when (typeHitung) {
            ConstantApp.Data.TYPE_HITUNG_TAMBAH -> {
                return (Integer.parseInt(main_et_num1.text.toString()) + Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
            ConstantApp.Data.TYPE_HITUNG_KURANG -> {
                return (Integer.parseInt(main_et_num1.text.toString()) - Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
            ConstantApp.Data.TYPE_HITUNG_KALI -> {
                return (Integer.parseInt(main_et_num1.text.toString()) * Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
            ConstantApp.Data.TYPE_HITUNG_BAGI -> {
                return (Integer.parseInt(main_et_num1.text.toString()) / Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
        }
        return 0.0;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_rg_operation.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.main_rb_plus -> setPlusOpr()
                R.id.main_rb_min -> setMinOpr()
                R.id.main_rb_kali -> setKaliOpr()
                R.id.main_rb_bagi -> setBagiOpr()
            }
        })

        main_btn_hitung.setOnClickListener(this)
        main_btn_random.setOnClickListener(this)
        main_btn_kirimhasil.setOnClickListener(this)
    }

    private fun setBagiOpr() {
        main_rb_bagi.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " / " + main_et_num2.text.toString())
        typeHitung = ConstantApp.Data.TYPE_HITUNG_BAGI
    }

    private fun setKaliOpr() {
        main_rb_kali.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " x " + main_et_num2.text.toString())
        typeHitung = ConstantApp.Data.TYPE_HITUNG_KALI
    }

    fun setPlusOpr() {
        main_rb_plus.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " + " + main_et_num2.text.toString())
        typeHitung = ConstantApp.Data.TYPE_HITUNG_TAMBAH
    }

    fun setMinOpr() {
        main_rb_min.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " - " + main_et_num2.text.toString())
        typeHitung = ConstantApp.Data.TYPE_HITUNG_KURANG
    }
}