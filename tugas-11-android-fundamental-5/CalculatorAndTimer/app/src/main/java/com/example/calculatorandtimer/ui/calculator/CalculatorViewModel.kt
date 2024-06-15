package com.example.calculatorandtimer.ui.calculator

import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var editText = MutableLiveData<String>()
    private var result: Long = 0
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false
    private var operator: String = ""

    fun onDigit(view: View) {
        if (editText.value.toString() == "" && operator == "") {
            editText.postValue((view as Button).text.toString())
            result = view.text.toString().toLong()
        } else if (lastNumeric && operator == ""){
            editText.postValue(editText.value.toString() + (view as Button).text.toString())
            result = (editText.value.toString() + view.text.toString()).toLong()
        } else if (lastNumeric){
            editText.postValue(editText.value.toString() + (view as Button).text.toString())
        } else {
            editText.postValue((view as Button).text.toString())
        }
        lastNumeric = true
    }

    fun onClear() {
        editText.postValue("")
        result = 0
        lastNumeric = false
        stateError = false
        lastDot = false
        operator = ""
    }

    fun onEqual() {
        if (lastNumeric) {
            when (operator) {
                "+" -> {
                    result += editText.value.toString().toLong()
                }
                "-" -> {
                    result -= editText.value.toString().toLong()
                }
                "/" -> {
                    result /= editText.value.toString().toLong()
                }
                "*" -> {
                    result *= editText.value.toString().toLong()
                }
            }
//            editText.postValue(result.toString())
            operator = ""
            editText.postValue(result.toString())
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric) {
            editText.postValue(editText.value.toString() + (view as Button).text.toString())
            operator = view.text.toString()
            lastNumeric = false
            lastDot = false
        }
    }

//    fun onDecimalPoint(view: View) {
//        if (lastNumeric && !stateError && !lastDot) {
//            editText.append(".")
//            lastNumeric = false
//            lastDot = true
//        }
//    }
}