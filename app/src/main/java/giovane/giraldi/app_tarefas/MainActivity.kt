package giovane.giraldi.app_tarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import giovane.giraldi.app_tarefas.components.AppTarefas
import giovane.giraldi.app_tarefas.models.Tarefa
import giovane.giraldi.app_tarefas.screens.TelaLista
import giovane.giraldi.app_tarefas.ui.theme.AppTarefaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTarefaTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppTarefas()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaListaPreview() {

    AppTarefaTheme {

        TelaLista(
            tarefas = listOf(
                Tarefa(
                    id = 1,
                    descricao = "Estudar Kotlin"
                ),
                Tarefa(
                    id = 2,
                    descricao = "Praticar Compose"
                )
            ),

            onNovaTarefa = {},

            onTarefaClick = { _ -> },

            onConcluir = { _ -> },

            onExcluir = { _ -> }
        )
    }
}
