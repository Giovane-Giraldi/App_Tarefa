package giovane.giraldi.app_tarefas.components


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
import giovane.giraldi.app_tarefas.viewmodel.TarefaViewModel

@Composable

fun AppTarefas(
    tarefaViewModel: TarefaViewModel = viewModel()
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Lista
    ) {

        composable<Lista> {

            TelaLista(

                tarefas = tarefaViewModel.tarefas,

                onNovaTarefa = {

                    navController.navigate(Cadastro)

                },

                onTarefaClick = {tarefa -> navController.navigate(Detalhes(tarefa.id))

                },

                onConcluir = { id, concluida -> tarefaViewModel.alternarConcluida(concluida, id) },
                onExcluir = {id->tarefaViewModel.removerTarefa(id)}
            )

        }
        composable<Cadastro> {

            TelaCadastro(

                onSalvar = { descricao ->

                    tarefaViewModel.adicionarTarefa(descricao)

                    navController.popBackStack()

                },
                onVoltar = {

                    navController.popBackStack()
                }
            )
        }
        composable<Detalhes> { backStackEntry ->
            val rota = backStackEntry.toRoute<Detalhes>()
            val tarefa = tarefaViewModel.buscarTarefa(rota.id)
            TelaDetalhes(

                tarefa = tarefa,

                onVoltar = {

                    navController.popBackStack()

                }

            )

        }
    }
}