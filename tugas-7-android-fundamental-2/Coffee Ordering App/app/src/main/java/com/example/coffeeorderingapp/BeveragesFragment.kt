package com.example.coffeeorderingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BeveragesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeveragesFragment : Fragment() {
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
        val rootView : View = inflater.inflate(R.layout.fragment_beverages, container, false)
        rvMenu = rootView.findViewById(R.id.rv_coffees)
        rvMenu.setHasFixedSize(true)

        list.addAll(getCoffeeList())
        showRecyclerList()
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        when (index) {
            0 -> {
                list.clear()
                list.addAll(getCoffeeList())
                showRecyclerList()
            }
            1 -> {
                list.clear()
                list.addAll(getFoodList())
                showRecyclerList()
            }
        }
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
        const val ARG_SECTION_NUMBER = "section_number"
    }
}