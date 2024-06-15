package com.example.calculatorandtimer.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.calculatorandtimer.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private lateinit var calculatorViewModel: CalculatorViewModel
    private var _binding: FragmentCalculatorBinding? = null
    private val fragmentCalculatorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        return fragmentCalculatorBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        calculatorViewModel.editText.observe(viewLifecycleOwner) {
            fragmentCalculatorBinding.resultTextView.text = it
        }


        fragmentCalculatorBinding.numberZero.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberOne.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberTwo.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberThree.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberFour.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberFive.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberSix.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberSeven.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberEight.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.numberNine.setOnClickListener {
            calculatorViewModel.onDigit(it)
        }
        fragmentCalculatorBinding.plus.setOnClickListener {
            calculatorViewModel.onOperator(it)
        }
        fragmentCalculatorBinding.minus.setOnClickListener {
            calculatorViewModel.onOperator(it)
        }
        fragmentCalculatorBinding.multiple.setOnClickListener {
            calculatorViewModel.onOperator(it)
        }
        fragmentCalculatorBinding.devide.setOnClickListener {
            calculatorViewModel.onOperator(it)
        }
        fragmentCalculatorBinding.equal.setOnClickListener {
            calculatorViewModel.onEqual()
        }
        fragmentCalculatorBinding.clear.setOnClickListener {
            calculatorViewModel.onClear()
        }

    }


}