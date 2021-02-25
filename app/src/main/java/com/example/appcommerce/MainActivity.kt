package com.example.appcommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productItem: LinearLayout = findViewById(R.id.ll_product_item)
        productItem.setOnClickListener{
            val intent: Intent = Intent(this, ProductDetailActivity::class.java)
            startActivity(intent)
        }
    }
}