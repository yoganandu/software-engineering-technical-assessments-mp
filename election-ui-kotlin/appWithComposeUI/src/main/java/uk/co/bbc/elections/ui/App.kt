package uk.co.bbc.elections.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.co.bbc.elections.data.ServiceContainer
import uk.co.bbc.elections.ui.home.Home
import uk.co.bbc.elections.ui.home.HomeAndroidViewModel
import uk.co.bbc.elections.ui.home.HomeViewModel
import uk.co.bbc.elections.ui.theme.ElectionsAssessmentTheme

@Composable
fun App(serviceContainer: ServiceContainer, homeAndroidViewModel: HomeAndroidViewModel) {
    ElectionsAssessmentTheme {
        val networkState by homeAndroidViewModel.networkState.collectAsState()
        val homeViewModel:HomeViewModel = viewModel(factory = HomeViewModel.Factory(serviceContainer))
        LaunchedEffect(key1 = networkState){
            if(networkState){
                homeViewModel.refresh()
            }
        }
        Home(homeViewModel, homeAndroidViewModel)
    }
}
