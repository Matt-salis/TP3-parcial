package com.example.parcial_grupo_8_ya.viewModels
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CheckoutViewModel : ViewModel() {
    private val _isCheckoutVisible = MutableStateFlow(false)
    val isCheckoutVisible: StateFlow<Boolean> = _isCheckoutVisible

    fun showCheckout() {
        _isCheckoutVisible.value = true
    }

    fun hideCheckout() {
        _isCheckoutVisible.value = false
    }
}
