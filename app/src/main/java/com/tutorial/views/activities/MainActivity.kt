package com.tutorial.views.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutorial.databinding.ActivityMainBinding
import com.tutorial.models.MainModel
import com.tutorial.views.activities.presenter.MainActivityPresenter
import com.tutorial.views.activities.presenter.MainActivityView
import com.tutorial.views.adapters.MainAdapter
import com.tutorial.views.adapters.MainAdapterView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainActivityPresenter
    private lateinit var adapter: MainAdapter
    companion object {
        private var list: MutableList<MainModel> = ArrayList()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding()
    }

    private fun binding() {
        presenter = MainActivityPresenter(object : MainActivityView {
            override fun onShowLoading() {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }

            override fun onHideLoading() {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }

            override fun onResponse(response: MutableList<MainModel>) {
                list.clear()
                list.addAll(response)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(error: Throwable) {
                Toast.makeText(applicationContext, "${error.printStackTrace()}", Toast.LENGTH_SHORT).show()
            }
        })
        presenter.resultsMainActivity()

        adapter = MainAdapter(list, object : MainAdapterView {
            override fun onClick(position: Int) {
                val result = list[position]
                Toast.makeText(applicationContext, result.title, Toast.LENGTH_SHORT).show()
            }
            override fun onLongClick(position: Int) {
                val result = list[position]
                Toast.makeText(applicationContext, result.title, Toast.LENGTH_SHORT).show()
            }
        })

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachMainActivity()
    }
}
