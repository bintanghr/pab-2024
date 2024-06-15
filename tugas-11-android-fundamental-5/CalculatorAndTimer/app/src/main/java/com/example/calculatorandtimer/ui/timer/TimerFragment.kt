package com.example.calculatorandtimer.ui.timer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.calculatorandtimer.R
import com.example.calculatorandtimer.databinding.FragmentTimerBinding


class TimerFragment : Fragment() {
    private lateinit var timerViewModel: MainViewModel
    private var _binding: FragmentTimerBinding? = null
    private val fragmentTimerBinding get() = _binding!!
    private var isButtonOn: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)

        return fragmentTimerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        timerViewModel.elapsedTime.observe(viewLifecycleOwner) { elapsedTime ->
            val elapsedTimeText = this.resources.getString(R.string.seconds, elapsedTime)
            fragmentTimerBinding.timerTextView.text = elapsedTimeText
        }

        fragmentTimerBinding.startButton.setOnClickListener {
            if(isButtonOn != null) {
                if (isButtonOn == true) {
                    isButtonOn = false
                    fragmentTimerBinding.startButton.setText(R.string.resume_timer)
                    timerViewModel.pauseTimer()
                } else {
                    isButtonOn = true
                    fragmentTimerBinding.startButton.setText(R.string.pause_timer)
                    timerViewModel.resumeTimer()
                }
            } else {
                isButtonOn = true
                fragmentTimerBinding.startButton.setText(R.string.pause_timer)
                timerViewModel.startTimer()
            }
        }

        fragmentTimerBinding.resetButton.setOnClickListener {
            fragmentTimerBinding.startButton.setText(R.string.start_timer)
            isButtonOn = null
            timerViewModel.stopTimer()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}