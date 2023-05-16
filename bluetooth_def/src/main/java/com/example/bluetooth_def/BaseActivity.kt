package com.example.bluetooth_def

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluetooth_def.databinding.FragmentListBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRcView()
    }

    private fun initRcView(){
        val rcView = binding.rcViewPaired
        rcView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter()
        rcView.adapter = adapter
        adapter.submitList(createDevises())

    }

    private fun createDevises(): List<ListItem>{
        val list = ArrayList<ListItem>()

        for (i in 0 until 5){
            list.add(
                ListItem(
                    "Honor $i",
                    "33:54:65:5$i"
                )
            )
        }
        return list
    }

}