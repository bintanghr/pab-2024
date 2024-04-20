package com.example.coffeeordering.ui.coffee

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeordering.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rvMenu: RecyclerView
    private lateinit var btnBeverages: Button
    private lateinit var btnFood: Button
    private lateinit var btnSetLayout: ImageButton
    private val list = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_coffee, container, false)
        rvMenu = rootView.findViewById(R.id.rv_coffees)
        rvMenu.setHasFixedSize(true)

        btnBeverages = rootView.findViewById(R.id.btn_coffee)
        btnBeverages.setOnClickListener(this)
        btnFood = rootView.findViewById(R.id.btn_food)
        btnFood.setOnClickListener(this)
        btnSetLayout = rootView.findViewById(R.id.btn_set_layout)
        btnSetLayout.setOnClickListener(this)

        list.addAll(getCoffeeList())
        showRecyclerList()
        // Inflate the layout for this fragment
        return rootView
    }

    private fun getCoffeeList() : ArrayList<Menu> {
        val dataName = resources.getStringArray(R.array.coffee_name)
        val dataDesc = resources.getStringArray(R.array.coffee_desc)
        val dataPrice = resources.getStringArray(R.array.coffee_price)
        val dataImg = resources.obtainTypedArray(R.array.coffee_img)
        val menuList = ArrayList<Menu>()
        for (i in dataName.indices) {
            val menu = Menu(dataName[i], dataPrice[i], dataDesc[i], dataImg.getResourceId(i, -1))
            menuList.add(menu)
        }
        return menuList
    }

    private fun getFoodList() : ArrayList<Menu> {
        val dataName = resources.getStringArray(R.array.food_name)
        val dataPrice = resources.getStringArray(R.array.food_price)
        val dataDesc = resources.getStringArray(R.array.food_desc)
        val dataImg = resources.obtainTypedArray(R.array.food_img)
        val menuList = ArrayList<Menu>()
        for (i in dataName.indices) {
            val menu = Menu(dataName[i], dataPrice[i], dataDesc[i], dataImg.getResourceId(i, -1))
            menuList.add(menu)
        }
        return menuList
    }

    private fun showRecyclerList() {
        rvMenu.layoutManager = LinearLayoutManager(requireContext())
        val listCatAdapter = ListMenuAdapter(this.requireContext(), list)
        rvMenu.adapter = listCatAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CoffeeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CoffeeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_food -> {
                btnFood.setBackgroundColor(Color.parseColor("#583732"))
                btnBeverages.setBackgroundColor(Color.parseColor("#9b8784"))
                list.clear()
                list.addAll(getFoodList())
                showRecyclerList()
            }

            R.id.btn_coffee -> {
                btnBeverages.setBackgroundColor(Color.parseColor("#583732"))
                btnFood.setBackgroundColor(Color.parseColor("#9b8784"))
                list.clear()
                list.addAll(getCoffeeList())
                showRecyclerList()
            }
        }
    }
}