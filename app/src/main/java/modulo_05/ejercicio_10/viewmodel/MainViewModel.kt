package modulo_05.ejercicio_10.viewmodel

import android.icu.text.DecimalFormat
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val showAlert = MutableLiveData<Boolean>()

    private val _edad = MutableLiveData<String>()
    var edad: LiveData<String> = _edad

    private val _peso = MutableLiveData<Double>()
    val peso: LiveData<Double> = _peso

    private val _altura = MutableLiveData<Double>()
    val altura: LiveData<Double> = _altura

    private var _imc = MutableLiveData<Double>()
    var imc: LiveData<Double> = _imc

    fun onMainScreenChanged(edad: String, peso: String, altura: String) {
        _edad.value = edad
        try {
            _peso.value = peso.toDouble()
            _altura.value = altura.toDouble()

            if (!peso.isBlank() && !altura.isBlank() && altura.toDouble() !=0.0) {
                _imc.value = calcularIMC(peso.toDouble(), altura.toDouble())
            }
        } catch (e: NumberFormatException) {
            showAlert.value = true
        }
    }
    fun calcularIMC(peso: Double, altura: Double): Double {
        val imc = peso / (altura * altura)
        val df = DecimalFormat("#.##")
        val imcFormateado = df.format(imc)
        return imcFormateado.toDouble()
    }

    fun limpiarPantalla(){
        _edad.value = ""
        _peso.value = 0.0
        _altura.value = 0.0
        _imc.value = 0.0

    }
}