package com.example.bluetooth_def

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bluetooth_def.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, DeviceListFragment())
            .commit()
    }
}