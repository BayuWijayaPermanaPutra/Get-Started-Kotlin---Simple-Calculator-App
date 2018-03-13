package id.co.paytren.latihankotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup

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
            main_btn_random -> {
                main_et_num1.setText(getRandomNumber(0).toString())
                main_et_num2.setText(getRandomNumber(0).toString())
                var opr = getRandomNumber(1)
                when (opr) {
                    0 -> setPlusOpr()

                    1 -> setMinOpr()

                    2 -> setKaliOpr()

                    3 -> setBagiOpr()

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
            0 -> return rand.nextInt(51)
            1 -> return rand.nextInt(4)
        }
        return 0
    }

    @Throws
    fun hitungResult(typeHitung: Int): Double {
        when (typeHitung) {
            0 -> {
                return (Integer.parseInt(main_et_num1.text.toString()) + Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
            1 -> {
                return (Integer.parseInt(main_et_num1.text.toString()) - Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
            2 -> {
                return (Integer.parseInt(main_et_num1.text.toString()) * Integer.parseInt(main_et_num2.text.toString())).toDouble()
            }
            3 -> {
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
    }

    private fun setBagiOpr() {
        main_rb_bagi.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " / " + main_et_num2.text.toString())
        typeHitung = 3
    }

    private fun setKaliOpr() {
        main_rb_kali.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " x " + main_et_num2.text.toString())
        typeHitung = 2
    }

    fun setPlusOpr() {
        main_rb_plus.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " + " + main_et_num2.text.toString())
        typeHitung = 0
    }

    fun setMinOpr() {
        main_rb_min.isChecked = true
        main_tv_detail.setText(main_et_num1.text.toString() + " - " + main_et_num2.text.toString())
        typeHitung = 1
    }
}