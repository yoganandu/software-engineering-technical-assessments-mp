package uk.co.bbc.elections.ui.home

import android.accessibilityservice.AccessibilityService
import android.app.Application
import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import uk.co.bbc.elections.data.ServiceContainer

class HomeAndroidViewModel(application: Application) : AndroidViewModel(application) {
    val talkbackState = MutableStateFlow(false)
    private val am: AccessibilityManager
    init {
        am = application.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        talkbackState.value = am.isEnabled
    }
}