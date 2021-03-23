package com.example.appcommerce.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcommerce.ProductCategoryFragment
import com.example.appcommerce.R
import com.example.appcommerce.model.ProductCategory

class ProductCategoryAdapter(val list: List<ProductCategory>, val context: Context) : RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.iv_category_icon)
        val title: TextView = itemView.findViewById(R.id.tv_category_title)
        val cardView: CardView = itemView.findViewById(R.id.cv_product_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.item_product_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: ProductCategory = list[position]
        holder.title.text = category.title
        holder.cardView.setOnClickListener {
            (context as ProductCategoryFragment.Callback).itemSelected(category)
        }
    }

    override fun getItemCount(): Int = list.size
}