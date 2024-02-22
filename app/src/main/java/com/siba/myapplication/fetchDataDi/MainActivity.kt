package com.siba.myapplication.fetchDataDi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.siba.myapplication.ApiResult
import com.siba.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            val fetchReqest = FetchReqest("common", "technewadmin")
            viewModel.fetchUserDataApiCall(fetchReqest)
        }

        lifecycleScope.launch {
            viewModel.fetchData.observe(this@MainActivity){result->
             when(result){
                 is ApiResult.Loading->{
                     binding.progressBar.visibility = View.VISIBLE
                 }
                is ApiResult.Success->{
                    result.data.let { getResult->
                        binding.progressBar.visibility = View.GONE
                        binding.tvOutput.text = getResult?.data?.version
                    }
                 }
                 is ApiResult.Error->{
                     binding.progressBar.visibility = View.GONE
                  Toast.makeText(this@MainActivity,result.message,Toast.LENGTH_SHORT).show()
                 }

             }
            }
        }
    }
}