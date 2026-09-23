package giovane.giraldi.app_tarefas.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import giovane.giraldi.app_tarefas.models.Tarefa
import giovane.giraldi.app_tarefas.rotes.Cadastro
import giovane.giraldi.app_tarefas.rotes.Detalhes
import giovane.giraldi.app_tarefas.rotes.Lista
import giovane.giraldi.app_tarefas.screens.TelaCadastro
import giovane.giraldi.app_tarefas.screens.TelaDetalhes
import giovane.giraldi.app_tarefas.screens.TelaLista

@Composable
fun AppTarefas() {

    var tarefas by remember {
        mutableStateOf(
            listOf(
                Tarefa(1, "Estudar Kotlin"),
                Tarefa(2, "Praticar Compose")
            )
        )
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Lista
    ) {

        composable<Lista> {

            TelaLista(
                tarefas = tarefas,

                onNovaTarefa = {
                    navController.navigate(Cadastro)
                },

                onTarefaClick = { tarefa ->
                    navController.navigate(
                        Detalhes(tarefa.id)
                    )
                },

                onConcluir = { id ->

                    tarefas = tarefas.map { tarefa ->

                        if (tarefa.id == id) {
                            tarefa.copy(
                                concluida = !tarefa.concluida
                            )
                        } else {
                            tarefa
                        }
                    }
                },

                onExcluir = { id ->

                    tarefas = tarefas.filterNot {
                        it.id == id
                    }
                }
            )
        }

        composable<Cadastro> {

            TelaCadastro(

                onSalvar = { descricao ->

                    val novoId =
                        (tarefas.maxOfOrNull { it.id } ?: 0) + 1

                    tarefas = tarefas + Tarefa(
                        id = novoId,
                        descricao = descricao
                    )

                    navController.popBackStack()
                },

                onVoltar = {
                    navController.popBackStack()
                }
            )
        }

        composable<Detalhes> { backStackEntry ->

            val rota =
                backStackEntry.toRoute<Detalhes>()

            val tarefa =
                tarefas.firstOrNull {
                    it.id == rota.id
                }

            TelaDetalhes(
                tarefa = tarefa,

                onVoltar = {
                    navController.popBackStack()
                }
            )
        }
    }
}