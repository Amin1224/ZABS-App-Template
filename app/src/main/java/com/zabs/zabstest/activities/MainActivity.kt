package com.zabs.zabstest.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.zabs.zabstest.R
import com.zabs.zabstest.databinding.ActivityMainBinding
import com.zabs.zabstest.dialog.Dialog
import com.zabs.zabstest.viewModel.AppViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val controller by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }
    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpUi()
        handleDestinationChange()
    }

    private fun setUpUi() {
        binding.menu.setOnClickListener {
            binding.drawer.open()
        }
        binding.backPress.setOnClickListener {
            onBackPressed()
        }
        binding.sideMenu.rateUs.setOnClickListener {
            Dialog.rateUs(this)
            binding.drawer.close()
        }
    }

    private fun handleDestinationChange() {
        controller.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.dashboard -> { // Use the actual ID obtained
                    Log.i("rfgege", "handleDestinationChange:match")

                    binding.menu.visibility = View.VISIBLE
                    binding.backPress.visibility = View.GONE
                    binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }

                else -> {
                    Log.i("rfgege", "handleDestinationChange: No match")
                    binding.menu.visibility = View.INVISIBLE
                    binding.backPress.visibility = View.VISIBLE
                    binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                }
            }
        }
    }

    override fun onBackPressed() {

        when (controller.currentDestination?.id) {
            R.id.dashboard -> {
                super.onBackPressed()
                finishAffinity()
            }

            else -> {
                controller.navigate(R.id.dashboard)
            }
        }

    }


}