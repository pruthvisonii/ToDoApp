/**

This is the main activity file for the To-Do App
Created by Pruthvi Soni and Sakshi Sheth
 */
package com.example.todoapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Declare private variables for the UI components
private lateinit var items: ArrayList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var lvItems: ListView
    private lateinit var etNewItem: EditText
    private lateinit var btnAddItem: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        lvItems = findViewById(R.id.lvItems)
        etNewItem = findViewById(R.id.etNewItem)
        btnAddItem = findViewById(R.id.btnAddItem)

        // Initialize the list and adapter
        items = ArrayList()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_checked, items)
        lvItems.adapter = itemsAdapter

        // Setup listener for the "Add Item" button
        btnAddItem.setOnClickListener {
            val itemText = etNewItem.text.toString()
            if (itemText.isNotEmpty()) {
                itemsAdapter.add(itemText)
                etNewItem.text.clear()
            }
        }

        // Setup listener for the list view items
        setupListViewListener()
    }

    // Function to setup the list view listener
    private fun setupListViewListener() {
        lvItems.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val checkedTextView = view.findViewById<CheckedTextView>(android.R.id.text1)
            if (checkedTextView.isChecked) {
                checkedTextView.isChecked = false
                itemsAdapter.remove(selectedItem)
            } else {
                checkedTextView.isChecked = true
            }
        }
    }
}
