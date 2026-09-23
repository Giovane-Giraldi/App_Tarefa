package giovane.giraldi.app_tarefas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TelaCadastro(
    onSalvar: (String) -> Unit,
    onVoltar: () -> Unit
) {

    var descricao by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Nova tarefa",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = descricao,
            onValueChange = {
                descricao = it
            },
            label = {
                Text("Descrição")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (descricao.isNotBlank()) {
                    onSalvar(descricao)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }

        OutlinedButton(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}