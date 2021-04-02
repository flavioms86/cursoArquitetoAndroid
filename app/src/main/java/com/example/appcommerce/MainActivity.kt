package com.example.appcommerce

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcommerce.adapter.ProductAdapter
import com.example.appcommerce.adapter.ProductCategoryAdapter
import com.example.appcommerce.model.Product
import com.example.appcommerce.model.ProductCategory
import com.example.appcommerce.model.ProductColor
import com.example.appcommerce.model.ProductSize
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    ProductCategoryFragment.Callback {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var textTitle: TextView
    lateinit var textLogin: TextView
    lateinit var recyclerCategory: RecyclerView
    lateinit var recyclerProduct: RecyclerView
    lateinit var imageProfile: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = getString(R.string.app_name)

        drawerLayout = findViewById(R.id.nav_drawer_layout)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.toggle_open,
            R.string.toggle_close
        )
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        textLogin = navigationView.getHeaderView(0).findViewById(R.id.header_profile_name)
        textLogin.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        imageProfile = navigationView.getHeaderView(0).findViewById(R.id.header_profile_image)

        recyclerCategory = findViewById(R.id.rv_main_product_category)

        val arrayCategory = arrayListOf<ProductCategory>(
            ProductCategory("1", "Camisetas"),
            ProductCategory("2", "Calças", fillRvProduct()),
            ProductCategory("3", "Meias"),
            ProductCategory("4", "Sapatos")
        )

        val adapterCategory = ProductCategoryAdapter(arrayCategory, this)

        recyclerCategory.adapter = adapterCategory
        recyclerCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerProduct = findViewById(R.id.rv_main_product)

        val adapterProduct = ProductAdapter(fillRvProduct(), this)

        recyclerProduct.adapter = adapterProduct
        recyclerProduct.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun fillRvProduct(): List<Product> {
        val product1: Product = Product(
            "1",
            "Camiseta 89",
            ProductCategory("id", "Camiseta"),
            "Camiseta leve exercícios",
            19.90,
            arrayListOf(
                ProductColor("1", "Branco", "#ffffff"),
                ProductColor("2", "Preto", "#000000")
            ),
            arrayListOf(ProductSize("1", "P"), ProductSize("1", "M")),
            emptyList()
        )

        val product2: Product = Product(
            "1",
            "Calça Jeans",
            ProductCategory("id", "Calças"),
            "Calça Jeans Rasgada",
            39.90,
            arrayListOf(
                ProductColor("1", "Branco", "#ffffff"),
                ProductColor("2", "Preto", "#000000")
            ),
            arrayListOf(ProductSize("1", "M"), ProductSize("1", "GG")),
            emptyList()
        )

        return arrayListOf(product1, product2)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    //Responde pela navegação do menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_account -> {
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_category -> {

                val intent = Intent(this, ProductCategoryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_orders -> Toast.makeText(this, "Compras", Toast.LENGTH_LONG).show()
            R.id.nav_cart -> Toast.makeText(this, "Carrinho", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Sair", Toast.LENGTH_LONG).show()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun itemSelected(category: ProductCategory) {
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val profileImage = PreferenceManager.getDefaultSharedPreferences(this).getString(MediaStore.EXTRA_OUTPUT, null)

        if (profileImage != null) {
            imageProfile.setImageURI(Uri.parse(profileImage))
        } else  {
            imageProfile.setImageResource(R.drawable.profile_image)
        }
    }
}