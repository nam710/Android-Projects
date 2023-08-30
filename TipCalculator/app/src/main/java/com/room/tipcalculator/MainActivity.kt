package com.room.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.room.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculateTip.setOnClickListener(View.OnClickListener { calculateTip() })
    }

    private fun calculateTip() {
        val stringInEdtTextField = binding.edtCost.text.toString()
        val cost = stringInEdtTextField.toDoubleOrNull()
        if(cost==null){
            binding.txTipAmt.text=""
            return
        }
        val percent = when(binding.rgTipOptions.checkedRadioButtonId){
            R.id.rb20per -> 0.2
            R.id.rb15per -> 0.15
            else -> 0.18
        }
        var tipAmt = percent * cost
        val roundUp = binding.swRoundUp.isChecked
        if(roundUp){
            tipAmt = kotlin.math.ceil(tipAmt)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmt)
        binding.txTipAmt.text = getString(R.string.tip_amount,formattedTip)

    }
}