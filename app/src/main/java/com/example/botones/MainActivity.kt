package com.example.botones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.botones.ui.theme.BotonesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BotonesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Botones() {
    
    var textoBoton by rememberSaveable { mutableStateOf("Presionar") }
    var mensage by rememberSaveable { mutableStateOf("") }
    var presionado by rememberSaveable { mutableStateOf(false) }
    var texto by rememberSaveable { mutableStateOf(false) }
    var opcionSelec by rememberSaveable { mutableStateOf("") }
    var seleccionado by rememberSaveable { mutableStateOf(false) }
    var imagenes by rememberSaveable { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()


    val images = listOf(R.drawable.foto2, R.drawable.foto3, R.drawable.foto4)

    Column(
        modifier = Modifier

            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Ícono Estrella",
            modifier = Modifier.size(64.dp)
        )

        Button(onClick = {
            presionado = true
            textoBoton = "Botón presionado"
            if(presionado) {
                coroutineScope.launch {
                    delay(5000)
                    textoBoton = "Presionar"
                    presionado = false
                }
            }
        }) {
            Text(textoBoton)
        }

        if (presionado) {
            CircularProgressIndicator()
        }

        Checkbox(
            checked = seleccionado,
            onCheckedChange = {
                seleccionado = it
                texto = it
            }
        )

        if (texto) {
            Text("Checkbox activado")
        }

        var isSwitchChecked by rememberSaveable { mutableStateOf(false) }
        Switch(
            checked = isSwitchChecked,
            onCheckedChange = { isSwitchChecked = it }
        )

        if (isSwitchChecked) {
            Column {
                RadioButon(
                    selectedOption = opcionSelec,
                    label = "Opción 1",
                    onClick = { opcionSelec = "Opción 1"; mensage = "Seleccionaste Opción 1" }
                )
                RadioButon(
                    selectedOption = opcionSelec,
                    label = "Opción 2",
                    onClick = { opcionSelec = "Opción 2"; mensage = "Seleccionaste Opción 2" }
                )
                RadioButon(
                    selectedOption = opcionSelec,
                    label = "Opción 3",
                    onClick = { opcionSelec = "Opción 3"; mensage = "Seleccionaste Opción 3" }
                )
            }
            if (mensage.isNotEmpty()) {
                Text(mensage)
            }
        }

        Image(
            painter = painterResource(id = images[imagenes]),
            contentDescription = "Imagen",
            modifier = Modifier.size(100.dp)
        )

        Button(onClick = {
            imagenes = (imagenes + 1) % images.size
        }) {
            Text("Cambiar imagen")
        }
    }
}

@Composable
fun RadioButon(selectedOption: String, label: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (selectedOption == label),
            onClick = onClick
        )
        Text(label)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Botones()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BotonesTheme {
        Greeting("Android")
    }
}