package com.example.coffeeorderingapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvMenu: RecyclerView
    private lateinit var btnBeverages: Button
    private lateinit var btnFood: Button
    private lateinit var btnSetLayout: ImageButton
    private val list = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvMenu = findViewById(R.id.rv_coffees)
        rvMenu.setHasFixedSize(true)

        btnBeverages = findViewById(R.id.btn_coffee)
        btnBeverages.setOnClickListener(this)
        btnFood = findViewById(R.id.btn_food)
        btnFood.setOnClickListener(this)
        btnSetLayout = findViewById(R.id.btn_set_layout)
        btnSetLayout.setOnClickListener(this)

        list.addAll(getCoffeeList())
        showRecyclerList()
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
        rvMenu.layoutManager = LinearLayoutManager(this)
        val listCatAdapter = ListMenuAdapter(this@MainActivity, list)
        rvMenu.adapter = listCatAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
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

            R.id.btn_set_layout -> {
                if (rvMenu.layoutManager is GridLayoutManager) {
                    btnSetLayout.setImageResource(R.drawable.grid_icon)
                    rvMenu.layoutManager = LinearLayoutManager(this)
                    rvMenu.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
                            override fun onChildViewAttachedToWindow(view: View) {
                            val viewHolder = rvMenu.getChildViewHolder(view)
                            if (viewHolder is RecyclerView.ViewHolder) {
                                val card: ConstraintLayout = viewHolder.itemView.findViewById(R.id.constraint_card)

                                val constraintSet = ConstraintSet()
                                constraintSet.clone(card)

                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.TOP)
                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.START)
                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.END)
                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.BOTTOM)

                                constraintSet.connect(
                                    R.id.tv_item_name,
                                    ConstraintSet.TOP,
                                    R.id.img_item_photo,
                                    ConstraintSet.TOP,
                                    8
                                )
                                constraintSet.connect(
                                    R.id.tv_item_name,
                                    ConstraintSet.START,
                                    R.id.img_item_photo,
                                    ConstraintSet.END,
                                    8
                                )
                                constraintSet.connect(
                                    R.id.tv_item_name,
                                    ConstraintSet.END,
                                    R.id.constraint_card,
                                    ConstraintSet.END
                                )

                                constraintSet.connect(
                                    R.id.tv_item_price,
                                    ConstraintSet.TOP,
                                    R.id.tv_item_name,
                                    ConstraintSet.BOTTOM
                                )
                                constraintSet.connect(
                                    R.id.tv_menu_price,
                                    ConstraintSet.START,
                                    R.id.tv_item_name,
                                    ConstraintSet.START
                                )
                                constraintSet.applyTo(card)
                            }
                        }

                        override fun onChildViewDetachedFromWindow(view: View) {
                            // Kosongkan implementasi jika diperlukan
                        }
                    })
                } else {
                    btnSetLayout.setImageResource(R.drawable.list_icon)
                    rvMenu.layoutManager = GridLayoutManager(this, 2)
                    rvMenu.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
                        override fun onChildViewAttachedToWindow(view: View) {
                            // Terapkan perubahan constraint pada setiap item yang baru terattach
                            val viewHolder = rvMenu.getChildViewHolder(view)
                            if (viewHolder is RecyclerView.ViewHolder) {
                                val card: ConstraintLayout = viewHolder.itemView.findViewById(R.id.constraint_card)
                                val constraintSet = ConstraintSet()
                                constraintSet.clone(card)
                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.TOP)
                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.START)
                                constraintSet.clear(R.id.tv_item_name, ConstraintSet.END)
                                constraintSet.clear(R.id.tv_menu_desc, ConstraintSet.BOTTOM)

                                constraintSet.connect(
                                    R.id.tv_item_name,
                                    ConstraintSet.TOP,
                                    R.id.img_item_photo,
                                    ConstraintSet.BOTTOM,
                                    8
                                )
                                constraintSet.connect(
                                    R.id.tv_item_name,
                                    ConstraintSet.START,
                                    R.id.constraint_card,
                                    ConstraintSet.START,
                                    8
                                )
                                constraintSet.connect(
                                    R.id.tv_item_price,
                                    ConstraintSet.TOP,
                                    R.id.tv_item_name,
                                    ConstraintSet.BOTTOM,
                                    8
                                )
                                constraintSet.connect(
                                    R.id.tv_item_price,
                                    ConstraintSet.START,
                                    R.id.constraint_card,
                                    ConstraintSet.START,
                                )
                                constraintSet.connect(
                                    R.id.tv_menu_price,
                                    ConstraintSet.END,
                                    R.id.constraint_card,
                                    ConstraintSet.END
                                )
                                constraintSet.applyTo(card)
                            }
                        }

                        override fun onChildViewDetachedFromWindow(view: View) {
                            // Kosongkan implementasi jika diperlukan
                        }
                    })

                }
            }
        }
    }
}