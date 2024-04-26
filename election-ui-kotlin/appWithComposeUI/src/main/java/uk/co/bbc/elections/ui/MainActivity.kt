package uk.co.bbc.elections.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import uk.co.bbc.elections.ElectionsApplication
import uk.co.bbc.elections.ui.home.HomeAndroidViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: HomeAndroidViewModel by viewModels()
        setContent {
            App((application as ElectionsApplication).serviceContainer,viewModel)
        }
    }
}
