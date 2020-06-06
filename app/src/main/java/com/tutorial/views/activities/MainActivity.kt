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
import com.tutorial.views.adapters.MainAdapter
import com.tutorial.views.adapters.MainAdapterView
import java.util.*

class MainActivity : AppCompatActivity(), MainActivityView, MainAdapterView {

    private lateinit var binding: ActivityMainBinding
    private val presenter: MainActivityPresenter = MainActivityPresenter(this)
    private val adapter: MainAdapter = MainAdapter(list, this)
    companion object {
        private var list: MutableList<MainModel> = ArrayList()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.resultsMainActivity()
        binding()
    }

    private fun binding() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        binding.recyclerView.adapter = adapter
    }

    override fun onShowLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    override fun onHideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    override fun onResponse(response: List<MainModel>) {
        list.clear()
        list.addAll(response)
        adapter.notifyDataSetChanged()
    }

    override fun onFailure(error: Throwable) {
        Toast.makeText(applicationContext, "${error.printStackTrace()}", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(position: Int) {
        val result = list[position]
        Toast.makeText(applicationContext, result.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachMainActivity()
    }
}
