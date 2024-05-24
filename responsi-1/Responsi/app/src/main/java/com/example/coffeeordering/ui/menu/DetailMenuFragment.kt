package com.example.coffeeordering.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.coffeeordering.R

class DetailMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgMenu : ImageView = view.findViewById(R.id.img_menu)
        val tvMenuPrice : TextView = view.findViewById(R.id.tv_menu_price)
        val tvMenuName : TextView = view.findViewById(R.id.tv_menu_name)
        val tvMenuDesc : TextView = view.findViewById(R.id.tv_menu_desc)

        arguments?.let {
            val img = it.getInt(EXTRA_IMG, 0)
            val price = it.getString(EXTRA_PRICE)
            val name = it.getString(EXTRA_NAME)
            val desc = it.getString(EXTRA_DESC)
            val priceText = "Rp $price"

            imgMenu.setImageResource(img)
            tvMenuPrice.text = priceText
            tvMenuName.text = name
            tvMenuDesc.text = desc
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_IMG = "extra_img"
    }
}