package com.example.coffeeordering.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeordering.R
import com.example.coffeeordering.ui.coffee.ListMenuAdapter
import com.example.coffeeordering.ui.coffee.Menu

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val list = ArrayList<Menu>()
    private lateinit var rvHistory: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView : View = inflater.inflate(R.layout.fragment_history, container, false)
        rvHistory = rootView.findViewById(R.id.rv_history)
        rvHistory.setHasFixedSize(true)

        list.addAll(getHistoryList())
        showRecyclerList()
        // Inflate the layout for this fragment
        return rootView
    }

    private fun getHistoryList() : ArrayList<Menu> {
        val foodName = resources.getStringArray(R.array.food_name)
        val foodPrice = resources.getStringArray(R.array.food_price)
        val foodDesc = resources.getStringArray(R.array.food_desc)
        val foodImg = resources.obtainTypedArray(R.array.food_img)
        val dataName = resources.getStringArray(R.array.coffee_name)
        val dataDesc = resources.getStringArray(R.array.coffee_desc)
        val dataPrice = resources.getStringArray(R.array.coffee_price)
        val dataImg = resources.obtainTypedArray(R.array.coffee_img)

        val dataHistory = resources.getStringArray(R.array.history)
        val menuList = ArrayList<Menu>()
        for (i in dataHistory.indices) {
            val values = dataHistory[i].split(",")
            val category = values[0]
            val id = values[1].toInt()
            when(category) {
                "Food" -> {
                    val menu = Menu(foodName[id], foodPrice[id], foodDesc[id], foodImg.getResourceId(id, -1))
                    menuList.add(menu)
                }
                "Coffee" -> {
                    val menu = Menu(dataName[id], dataPrice[id], dataDesc[id], dataImg.getResourceId(id, -1))
                    menuList.add(menu)
                }
            }
        }
        return menuList
    }

    private fun showRecyclerList() {
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        val listCatAdapter = ListMenuAdapter(this.requireContext(), list)
        rvHistory.adapter = listCatAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}