package com.funeraria.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.funeraria.app.ui.navigation.NavGraph
import com.funeraria.app.ui.theme.BackgroundColor
import com.funeraria.app.ui.theme.FunerariaTheme
import com.funeraria.app.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunerariaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                    val authViewModel: AuthViewModel = hiltViewModel()
                    NavGraph(authViewModel = authViewModel)
                }
            }
        }
    }
}
