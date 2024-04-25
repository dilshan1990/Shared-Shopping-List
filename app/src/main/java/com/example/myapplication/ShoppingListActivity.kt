package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var itemNameEditText: EditText
    private lateinit var itemQtyEditText: EditText
    private lateinit var addItemButton: ImageButton
    private lateinit var itemsRecyclerView: RecyclerView
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        itemNameEditText = findViewById(R.id.itemNameEditText)
        itemQtyEditText = findViewById(R.id.itemQtyEditText)
        addItemButton = findViewById(R.id.addItemButton)
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView)

        adapter = ShoppingListAdapter(mutableListOf()) { position ->
            adapter.removeItem(position) // Calls removeItem correctly
        }
        itemsRecyclerView.adapter = adapter
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.nav_logout) {
                logout()
                true
            } else false
        }

        addItemButton.setOnClickListener {
            val itemName = itemNameEditText.text.toString().trim()
            val itemQty = itemQtyEditText.text.toString().trim()
            if (itemName.isNotEmpty() && itemQty.isNotEmpty()) {
                val newItem = ShoppingItem(itemName, itemQty)
                adapter.addItem(newItem) // Calls addItem correctly
                itemNameEditText.text.clear()
                itemQtyEditText.text.clear()
            }
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
