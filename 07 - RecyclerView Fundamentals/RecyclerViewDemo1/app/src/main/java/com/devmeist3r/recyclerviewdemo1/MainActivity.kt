package com.devmeist3r.recyclerviewdemo1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private var fruitsList = listOf(
    Fruit("Mango", supplier = "Tom"),
    Fruit("Apple", "Lucas"),
    Fruit("Banana", "Gabriel"),
    Fruit("Guava", "Joao"),
    Fruit("Lemon", "Bida"),
    Fruit("Pear", "Guilherme"),
    Fruit("Orange", "Esteves")
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    my_recycler_view.setBackgroundColor(Color.YELLOW)
    my_recycler_view.layoutManager = LinearLayoutManager(this)

    my_recycler_view.adapter = MyRecyclerViewAdapter(
      fruitsList
    ) { selectedFruitItem: Fruit -> listItemClicked(selectedFruitItem) }
  }

  private fun listItemClicked(fruit: Fruit) {
    Toast.makeText(
      this@MainActivity,
      "Supplier name is ${fruit.supplier}",
      Toast.LENGTH_LONG
    ).show()
  }

}
