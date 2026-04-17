package com.androidcoroutineflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidcoroutineflow.crypto_app.CryptoActivity
import com.androidcoroutineflow.lesson2.UsersActivity
import com.androidcoroutineflow.databinding.ActivityMainBinding
import com.androidcoroutineflow.team_score.TeamScoreActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
        binding.buttonCryptoActivity.setOnClickListener {
            startActivity(CryptoActivity.newIntent(this))
        }
        binding.teamScoreActivity.setOnClickListener {
            startActivity(TeamScoreActivity.newIntent(this))
        }
    }
}