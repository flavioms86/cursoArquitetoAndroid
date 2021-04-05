package com.example.appcommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcommerce.adapter.OrderAdapter
import com.example.appcommerce.model.*
import java.util.*

class OrderFragment : Fragment(){
    lateinit var recyclerOrder: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_order, container, false)

        recyclerOrder = view.findViewById(R.id.rv_order)


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

        val arrayOrder = arrayListOf(
            Order(UUID.randomUUID().toString(),
                Date().time,
                Order.Status.PAID,
                Order.Method.CREDIT_CARD,
                User("", "", "", "", "", "", emptyList()),
                arrayListOf(
                OrderedProduct("1", product1, 1), OrderedProduct("1", product2, 2))),

            Order(UUID.randomUUID().toString(),
                Date().time,
                Order.Status.PENDENT,
                Order.Method.BOLETO,
                User("", "", "", "", "", "", emptyList()),
                arrayListOf(
                    OrderedProduct("1", product2, 3), OrderedProduct("1", product2, 5))))

        val adapterOrder = OrderAdapter(arrayOrder, requireContext())

        recyclerOrder.adapter = adapterOrder
        recyclerOrder.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        return view
    }
}