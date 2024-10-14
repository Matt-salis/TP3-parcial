package com.example.parcial_grupo_8_ya.screen.checkout
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CheckoutViewModel : ViewModel() {
    // Estado para manejar la visibilidad del popup
    private val _isCheckoutVisible = MutableStateFlow(false)
    val isCheckoutVisible: StateFlow<Boolean> = _isCheckoutVisible

    // Funciones para mostrar y ocultar el popup
    fun showCheckout() {
        _isCheckoutVisible.value = true
    }

    fun hideCheckout() {
        _isCheckoutVisible.value = false
    }
}
