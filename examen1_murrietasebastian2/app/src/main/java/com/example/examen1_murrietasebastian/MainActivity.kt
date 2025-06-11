package com.example.examen1_murrietasebastian

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1_murrietasebastian.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asignar función al botón
        binding.button.setOnClickListener {
            calcularProbabilidad()
        }
    }
    private fun calcularProbabilidad() {
        val nombre = binding.tuNombre.text.toString().trim().lowercase()
        val crush = binding.nombreCrush.text.toString().trim().lowercase()

        val soloLetras = Regex("^[a-zA\\s]+$")

        if (nombre.isEmpty() || crush.isEmpty()) {
            binding.porcentaje.text = ""
            binding.mensajeTxt.text = "Por favor, completa ambos nombres."
        } else if (!nombre.matches(soloLetras) || !crush.matches(soloLetras)) {
            binding.porcentaje.text = ""
            binding.mensajeTxt.text = "Los nombres no pueden contener números ni símbolos."
        } else {
            val nombresCombinados = (nombre + crush).replace(" ", "")
            val totalLetras = nombresCombinados.length

            val vocales = "aeiou"
            val cantidadVocales = nombresCombinados.count { it in vocales }

            val porcentaje = (cantidadVocales * 100) / totalLetras

            // Cambiar color según porcentaje
            val color = when {
                porcentaje > 90 -> Color.parseColor("#FF0000") // rojo
                porcentaje > 75 -> Color.parseColor("#FF69B4") // rosa
                porcentaje > 60 -> Color.parseColor("#FFA500") // naranja
                else -> Color.parseColor("#0000FF")            // azul
            }

            binding.porcentaje.setTextColor(color)
            binding.porcentaje.text = "$porcentaje%"

            binding.mensajeTxt.text = when {
                porcentaje > 90 -> "¡Son el uno para el otro! 💖"
                porcentaje > 75 -> "Podrían intentarlo. 😉"
                porcentaje > 60 -> "¡Buena compatibilidad! 😊"
                else -> "Mejor sigue pensándolo..."
            }
        }
    }




}
