package com.example.test908.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test908.R
import com.example.test908.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //if(savedInstanceState==null){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, fragRewiewes.newInstance())
            .commit()

         //}
                                                                                             //Switching between screens
//        binding.toolbar.critics.setOnClickListener {
//            binding.toolbar.critics.setTextColor(Color.parseColor("#b5e2fa"))
//            binding.toolbar.reviewes.setTextColor(Color.WHITE)
//            binding.toolbar.reviewes.setBackgroundResource(R.drawable.rounded_left_1)
//            binding.toolbar.critics.setBackgroundResource(R.drawable.rounded_right_1)
//            binding.toolbar.toolbarr.setBackgroundResource(R.color.orr1)
//            val windows = window
//            check = false
//            windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            windows.statusBarColor = this.resources.getColor(R.color.orr1,resources.newTheme())
//            supportFragmentManager.beginTransaction()
//                .replace<criticFragment>(R.id.frame)
//                .addToBackStack("First tab")
//                .commit() }
//        binding.toolbar.reviewes.setOnClickListener {
//            binding.toolbar.critics.setTextColor(Color.WHITE)
//            binding.toolbar.reviewes.setTextColor(Color.parseColor("#F7A072"))
//            binding.toolbar.toolbarr.setBackgroundResource(R.color.orr)
//            binding.toolbar.reviewes.setBackgroundResource(R.drawable.rounded_left)
//            binding.toolbar.critics.setBackgroundResource(R.drawable.rounded_right)
//            check1 = false
//            val windows = window
//            windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            windows.statusBarColor = this.resources.getColor(R.color.orr,resources.newTheme())
//            supportFragmentManager.beginTransaction()
//                .replace<reviews>(R.id.frame)
//                .addToBackStack("Second tab")
//                .commit()
//        }
    }
}