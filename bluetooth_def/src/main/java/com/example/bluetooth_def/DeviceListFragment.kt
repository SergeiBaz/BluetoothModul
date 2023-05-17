package com.example.bluetooth_def

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluetooth_def.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

class DeviceListFragment : Fragment(), ItemAdapter.Listener {
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: FragmentListBinding
    private var bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var bluetoothLauncher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imBluetoothOn.setOnClickListener {
            bluetoothLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        }
        initRcViewPaired()
        registerBluetoothLaunch()
        initBluetoothAdapter()
        bluetoothState()
    }

    private fun initRcViewPaired() = with(binding) {
        rcViewPaired.layoutManager = LinearLayoutManager(requireContext())
        itemAdapter = ItemAdapter(this@DeviceListFragment)
        rcViewPaired.adapter = itemAdapter
    }

    private fun getPairedDevices() {
        try {
            val listDevice = bluetoothAdapter?.bondedDevices as Set<BluetoothDevice>
            val list = ArrayList<ListItem>()
            listDevice.forEach {
                list.add(
                    ListItem(
                        it.name,
                        it.address,
                        false
                    )
                )
            }
            binding.tvEmptyPaired.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            itemAdapter.submitList(list)
        } catch (_: SecurityException) {
        }
    }

    private fun initBluetoothAdapter() {
        val bluetoothManager =
            activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
    }

    private fun bluetoothState() {
        if (bluetoothAdapter?.isEnabled == true) {
            checkButtonColor(binding.imBluetoothOn, Color.BLACK)
            getPairedDevices()
        }
    }

    private fun registerBluetoothLaunch() {
        bluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                checkButtonColor(binding.imBluetoothOn, Color.BLACK)
                getPairedDevices()
                Snackbar.make(binding.root, "Bluetooth ON", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(binding.root, "Bluetooth OFF", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(device: ListItem) {

    }
}