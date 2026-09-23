package giovane.giraldi.app_tarefas.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import giovane.giraldi.app_tarefas.models.Tarefa

@Composable
fun TelaDetalhes(
    tarefa: Tarefa?,
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Detalhes da tarefa",
            style = MaterialTheme.typography.headlineSmall
        )

        if (tarefa == null) {

            Text("Tarefa não encontrada")

        } else {

            Text("Descrição: ${tarefa.descricao}")

            Text(
                "Status: " +
                        if (tarefa.concluida) {
                            "Concluída"
                        } else {
                            "Pendente"
                        }
            )
        }

        Button(
            onClick = onVoltar
        ) {
            Text("Voltar")
        }
    }
}