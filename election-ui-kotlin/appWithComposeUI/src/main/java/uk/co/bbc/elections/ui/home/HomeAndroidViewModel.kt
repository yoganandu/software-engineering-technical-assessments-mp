package uk.co.bbc.elections.ui.home

import android.accessibilityservice.AccessibilityService
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.view.accessibility.AccessibilityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import uk.co.bbc.elections.data.ServiceContainer

class HomeAndroidViewModel(application: Application) : AndroidViewModel(application) {
    val talkbackState = MutableStateFlow(false)
    val networkState = MutableStateFlow(false)
    private val am: AccessibilityManager
    private val connectivityManager: ConnectivityManager
    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkState.value = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkState.value = false
        }
    }

    init {
        am = application.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        talkbackState.value = am.isEnabled
        connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}