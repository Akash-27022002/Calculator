package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
//import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*Number Buttons*/

        tvOne.setOnClickListener {
            evaluateExpression("1", clear = true)
        }
        tvTwo.setOnClickListener {
            evaluateExpression("2", clear = true)
        }
        tvThree.setOnClickListener {
            evaluateExpression("3", clear = true)
        }
        tvFour.setOnClickListener {
            evaluateExpression("4", clear = true)
        }
        tvFive.setOnClickListener {
            evaluateExpression("5", clear = true)
        }
        tvSix.setOnClickListener {
            evaluateExpression("6", clear = true)
        }
        tvSeven.setOnClickListener {
            evaluateExpression("7", clear = true)
        }
        tvEight.setOnClickListener {
            evaluateExpression("8", clear = true)
        }
        tvNine.setOnClickListener {
            evaluateExpression("9", clear = true)
        }
        tvZero.setOnClickListener {
            evaluateExpression("0", clear = true)
        }
        /*Operators*/
        tvAdd.setOnClickListener {
            evaluateExpression("+", clear = true)
        }
        tvMinus.setOnClickListener {
            evaluateExpression("-", clear = true)
        }
        tvMul.setOnClickListener {
            evaluateExpression("*", clear = true)
        }
        tvDivide.setOnClickListener {
            evaluateExpression("/", clear = true)
        }
        tvDot.setOnClickListener {
            evaluateExpression(".", clear = true)
        }
        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }
        tvEquals.setOnClickListener {
            if(!tvResult.text.toString().isEmpty() || tvResult.text.toString() != "Error"){
                tvExpression.text = tvResult.text
            }
            tvResult.text = ""
        }
        tvBack.setOnClickListener {
            val text = tvExpression.text.toString()
            if(text.isNotEmpty()) {
                tvExpression.text = text.drop(1)
            }
            tvResult.text = ""
        }
    }

    /*Function to calculate the expressions using expression builder library*/

    private fun evaluateExpression(string: String, clear: Boolean) = if(clear) {
        tvResult.text = ""
        tvExpression.append(string)
        tvResult.text = result()
    } else {
        if(tvResult.text.toString() == "Error"){
            tvExpression.append("")
        }
        tvExpression.append(tvResult.text)
        tvExpression.append(string)
        tvResult.text = result()

    }

    private fun result():String{
        var text = tvExpression.text.toString()
        val n = text.length
        var bool = true

        if((text[n-1] == '+' || text[n-1] == '*'||text[n-1] == '-'||text[n-1] == '/')&&n>1){
            if(text[n-1] == '+' || text[n-1] == '-'){
                text += "0";
            }else{
                text += "1"
            }
        }

        if(text[0] == '+' || text[0] == '*'||text[0] == '-'||text[0] == '/'){
            return "Error"
        }
        else if(tvExpression.text.toString().isEmpty()){
            return ""
        }
            val expression = ExpressionBuilder(text).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                return longResult.toString()
            } else {
                return result.toString()
            }
    }
}

