package modulo_05.ejercicio_10


import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modulo_05.ejercicio_10.components.MainButton
import modulo_05.ejercicio_10.components.MySegmentedButton
import modulo_05.ejercicio_10.components.MyText
import modulo_05.ejercicio_10.components.MyTextField
import modulo_05.ejercicio_10.viewmodel.MainViewModel


@Composable
fun MainScreen(paddingValues: PaddingValues, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize(),// Padding del Scaffold

        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val edad by viewModel.edad.observeAsState(initial = "")
        val peso by viewModel.peso.observeAsState(initial = "")
        val altura by viewModel.altura.observeAsState(initial = "")
        val imc by viewModel.imc.observeAsState(initial = 0.0)


        MyText(text = stringResource(id = R.string.tituloApp))
        MySegmentedButton()
        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            MyTextField(
                text = edad,
                onValueChange = {
                    viewModel.onMainScreenChanged(
                        edad = it,
                        peso.toString(),
                        altura.toString(),

                        )
                },
                label = stringResource(id = R.string.edad)
            )
            MyTextField(
                text = peso.toString(),
                onValueChange = {
                    viewModel.onMainScreenChanged(
                        edad,
                        peso = it,
                        altura = altura.toString()
                    )
                },
                label = stringResource(id = R.string.peso)
            )
            MyTextField(
                text = altura.toString(),
                onValueChange = {
                    viewModel.onMainScreenChanged(
                        edad,
                        peso = peso.toString(),
                        altura = it
                    )
                },
                label = stringResource(id = R.string.altura)
            )

            Text(text = stringResource(id = R.string.calcular))
            if (imc != 0.0) {
                Text(text = imc.toString(), fontSize = 50.sp, fontWeight = FontWeight.SemiBold)
            }

            MainButton(text = "Limpiar pantalla", color = MaterialTheme.colorScheme.error) {
                viewModel.limpiarPantalla()
            }
        }
    }
}

