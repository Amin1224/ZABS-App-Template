package com.zabs.zabstest.dialog

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import com.zabs.zabstest.databinding.RateUsBinding

class Dialog {

    companion object{
        fun rateUs(context: Context) {
            val customDialog = Dialog(context)
            val binding = RateUsBinding.inflate(LayoutInflater.from(context))
            customDialog.setContentView(binding.root)
            // Set the width and height to wrap content programmatically
            val window = customDialog.window
            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            binding.cancel.setOnClickListener {
                customDialog.dismiss()
            }

            // Reference to the "Done" button
            val doneButton = binding.done

            binding.rateUs.setOnRatingBarChangeListener { _, rating, _ ->
                // Enable/disable the "Done" button based on the selected rating
                doneButton.isClickable = rating >= 1

            }

            binding.done.setOnClickListener {
                val rating = binding.rateUs.rating
                customDialog.dismiss()

                if (rating >= 4) {
                    // Open your app's Play Store page
                    try {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=${context.packageName}")
                        )
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        // If Play Store is not installed, open the app in a web browser
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}")
                        )
                        context.startActivity(intent)
                    }
                } else {
                    // Display a toast message for thanks
                    Toast.makeText(context, "Thanks for  rating!", Toast.LENGTH_SHORT).show()
                }
            }

            customDialog.show()
        }

    }
}