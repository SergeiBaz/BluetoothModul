package com.example.bluetooth_def

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.bluetooth_def.databinding.ActivityBaseBinding
import com.example.bluetooth_def.databinding.FragmentListBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initRcView()
        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, DeviceListFragment()).commit()

    }

    /*private fun initRcView(){
        val rcView = binding.rcViewPaired
        rcView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter()
        rcView.adapter = adapter
        adapter.submitList(changeDevices())
    }*/

    private fun changeDevices() : List<ListItem>{
        val list = ArrayList<ListItem>()
        for (i in 0 until 10){
            list.add(
                ListItem(
                    "Honor $i",
                    "45:64:00:1$i"
                )
            )
        }
        return list
    }
}
