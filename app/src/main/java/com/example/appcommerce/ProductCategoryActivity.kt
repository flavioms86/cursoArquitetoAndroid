package com.example.appcommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcommerce.adapter.ProductCategoryAdapter
import com.example.appcommerce.model.ProductCategory

class ProductCategoryActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var textTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_category)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.product_category_title)

        recyclerView = findViewById(R.id.rv_product_category)

        val arrayCategory = arrayListOf<ProductCategory>(
                ProductCategory("1", "Camisetas"),
                ProductCategory("2", "Calças"),
                ProductCategory("3", "Meias"),
                ProductCategory("4", "Sapatos")
        )

        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recyclerView.adapter = adapterCategory
        recyclerView.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}