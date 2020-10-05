package com.devmeist3r.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmeist3r.roomdemo.databinding.ActivityMainBinding
import com.devmeist3r.roomdemo.db.Subscriber
import com.devmeist3r.roomdemo.db.SubscriberDatabase
import com.devmeist3r.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var subscriberViewModel: SubscriberViewModel
  private lateinit var adapter: MyRecyclerViewAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    val dao = SubscriberDatabase.getInstance(application).subscriberDAO
    val repository = SubscriberRepository(dao)
    val factory = SubscriberViewModelFactory(repository)
    subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
    binding.myViewModel = subscriberViewModel
    binding.lifecycleOwner = this

    initRecyclerView()

    subscriberViewModel.message.observe(this, Observer {
      it.getContentIfNotHandled()?.let {
        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
      }
    })
  }

  private fun initRecyclerView() {
    binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
    adapter = MyRecyclerViewAdapter { selectedItem: Subscriber -> listItemClicked(selectedItem) }
    binding.subscriberRecyclerView.adapter = adapter
    displaySubscribersList()
  }


  private fun displaySubscribersList() {
    subscriberViewModel.subscribers.observe(this, Observer {
      Log.i("MyTag", it.toString())
      adapter.setList(it)
      adapter.notifyDataSetChanged()
    })
  }

  private fun listItemClicked(subscriber: Subscriber) {
    subscriberViewModel.initUpdateAndDelete(subscriber)
  }
}
