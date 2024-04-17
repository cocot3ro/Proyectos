package com.example.controlgastos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.controlgastos.databinding.ActivityMainBinding
import com.unnamed.b.atv.model.TreeNode
import com.unnamed.b.atv.view.AndroidTreeView

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myHolder = MyHolder(this)

        //n_0: ListCategory("Total", listOf(n_0_0, n_0_1, n_0_2))
        //      n_0_0: ListCategory("Gasto supermercado", listOf(n_0_0_0, n_0_0_1, n_0_0_2))
        //          n_0_0_0: ListItem("Lunes", 50)
        //          n_0_0_1: ListItem("Martes", 25.5)
        //          n_0_0_2: ListItem("Miercoles", 65.79)
        //      n_0_1: ListCategory("SDC", listOf(n_0_1_0, n_0_1_1))
        //          n_0_1_0: ListCategory("Coche", listOf(n_0_1_0_0, n_0_1_0_1, n_0_1_0_2))
        //              n_0_1_0_0: ListItem("Gasolina1", 50)
        //              n_0_1_0_1: ListItem("Gasolina2", 40)
        //              n_0_1_0_2: ListItem("Peaje", 6)
        //          n_0_1_1: ListCategory("Piso", listOf(n_0_1_1_0, n_0_1_1_1))
        //              n_0_1_1_0: ListCategory("Alquileres", listOf(n_0_1_1_0_0, n_0_1_1_0_1, n_0_1_1_0_2))
        //                  n_0_1_1_0_0: ListItem("Enero", 215)
        //                  n_0_1_1_0_1: ListItem("Febrero", 215)
        //                  n_0_1_1_0_2: ListItem("Marzo", 215)
        //              n_0_1_1_1: ListCategory("Facturas", listOf(n_0_1_1_1_0, n_0_1_1_1_1, n_0_1_1_1_2))
        //                  n_0_1_1_1_0: ListItem("Agua", 6.5)
        //                  n_0_1_1_1_1: ListItem("Luz", 15)
        //                  n_0_1_1_1_2: ListItem("Gas", 12.75)
        //      n_0_2: ListCategory("Ocio", listOf(n_0_2_0, n_0_2_1))
        //          n_0_2_0: ListItem("Cine", 7.5)
        //          n_0_2_1: ListItem("Cafe", 1.5)

        val total: ListNode = ListCategory("Total")

        val root = TreeNode(total)


        val treeView = AndroidTreeView(this, root)
        treeView.setDefaultViewHolder(myHolder.javaClass)
    }

    fun generateDataStructure(): ListNode {
        val root = ListCategory("Total")

        // Gasto supermercado
        val gastoSupermercado = ListCategory("Gasto supermercado")
        gastoSupermercado.addChildren(ListItem("Lunes", 50))
        gastoSupermercado.addChildren(ListItem("Martes", 25.5))
        gastoSupermercado.addChildren(ListItem("Miércoles", 65.79))
        root.addChildren(gastoSupermercado)

        // SDC
        val sdc = ListCategory("SDC")
        // Coche
        val coche = ListCategory("Coche")
        coche.addChildren(ListItem("Gasolina1", 50))
        coche.addChildren(ListItem("Gasolina2", 40))
        coche.addChildren(ListItem("Peaje", 6))
        sdc.addChildren(coche)
        // Piso
        val piso = ListCategory("Piso")
        // Alquileres
        val alquileres = ListCategory("Alquileres")
        alquileres.addChildren(ListItem("Enero", 215))
        alquileres.addChildren(ListItem("Febrero", 215))
        alquileres.addChildren(ListItem("Marzo", 215))
        piso.addChildren(alquileres)
        // Facturas
        val facturas = ListCategory("Facturas")
        facturas.addChildren(ListItem("Agua", 6.5))
        facturas.addChildren(ListItem("Luz", 15))
        facturas.addChildren(ListItem("Gas", 12.75))
        piso.addChildren(facturas)
        sdc.addChildren(piso)
        root.addChildren(sdc)

        // Ocio
        val ocio = ListCategory("Ocio")
        ocio.addChildren(ListItem("Cine", 7.5))
        ocio.addChildren(ListItem("Café", 1.5))
        root.addChildren(ocio)

        return root
    }
}