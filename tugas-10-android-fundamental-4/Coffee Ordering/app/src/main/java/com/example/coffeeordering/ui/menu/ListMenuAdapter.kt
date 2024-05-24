package com.example.coffeeordering.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeordering.R

class ListMenuAdapter(private val listMenu: ArrayList<Menu>) :
    RecyclerView.Adapter<ListMenuAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_coffee, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMenu.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, desc, img) = listMenu[position]
        val priceText = "Rp $price"
        holder.imgPhoto.setImageResource(img)
        holder.tvPrice.text = priceText
        holder.tvName.text = name

//        holder.itemView.setOnClickListener{
//            val activity = context as AppCompatActivity
//            val fragmentManager = activity.supportFragmentManager
//            val detailMenuFragment = DetailMenuFragment()
//            val bundle = Bundle()
//
//            bundle.putString(DetailMenuFragment.EXTRA_NAME, name)
//            bundle.putString(DetailMenuFragment.EXTRA_PRICE, price)
//            bundle.putString(DetailMenuFragment.EXTRA_DESC, desc)
//            bundle.putInt(DetailMenuFragment.EXTRA_IMG, img)
//            detailMenuFragment.arguments = bundle
//
//            fragmentManager
//                .beginTransaction()
//                .add(R.id.container, detailMenuFragment, DetailMenuFragment::class.java.simpleName)
//                .addToBackStack(null)
//                .commit()
//        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString(DetailMenuFragment.EXTRA_NAME, name)
                putString(DetailMenuFragment.EXTRA_PRICE, price)
                putString(DetailMenuFragment.EXTRA_DESC, desc)
                putInt(DetailMenuFragment.EXTRA_IMG, img)
            }
            holder.itemView.findNavController().navigate(R.id.action_menuFragment_to_detailMenuFragment, bundle)
        }
    }
}