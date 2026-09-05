package giovane.giraldi.app_tarefas.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import giovane.giraldi.app_tarefas.models.Tarefa
import giovane.giraldi.app_tarefas.components.ItemTarefa

@Composable
fun TelaTarefas() {

    var tarefas by remember {
        mutableStateOf(
            listOf(
                Tarefa(1, "Estudar Kotlin"),
                Tarefa(2, "Praticar Compose"),
                Tarefa(3, "Revisar a aula")
            )
        )
    }

    var novaTarefa by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = novaTarefa,
                onValueChange = { novaTarefa = it },
                label = { Text("Nova tarefa") },
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    if (novaTarefa.isNotBlank()) {
                        val tarefa = Tarefa(
                            id = (tarefas.maxOfOrNull { it.id } ?: 0) + 1,
                            descricao = novaTarefa.trim()
                        )

                        tarefas = tarefas + tarefa
                        novaTarefa = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Adicionar")
            }
        }


        Spacer(modifier = Modifier.height(12.dp))


        LazyColumn {
            items(
                tarefas,
                key = { it.id }
            )
            { tarefa ->
                ItemTarefa(
                    tarefa = tarefa,
                    onConcluidaChange = { concluida ->
                        tarefas = tarefas.map { item ->
                            if (item.id == tarefa.id) {
                                item.copy(concluida = concluida)
                            } else {
                                item
                            }
                        }
                        }, onExcluir = { tarefas = tarefas.filterNot { it.id == tarefa.id }
                    }
                )
            }
        }
    }
}


                    @Preview(
    showSystemUi = true
)
@Composable
fun PreviewTelaTarefas(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(paddingValues = innerPadding))
        TelaTarefas()
    }

}