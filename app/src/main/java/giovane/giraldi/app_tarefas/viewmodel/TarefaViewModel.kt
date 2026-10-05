package giovane.giraldi.app_tarefas.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import giovane.giraldi.app_tarefas.models.Tarefa


class TarefaViewModel : ViewModel() {
    var tarefas by mutableStateOf(
        listOf(
            Tarefa(1, "Estudar Kotlin"),
            Tarefa(2, "Praticar Compose")
        )
    )

        private set

    fun adicionarTarefa(descricao: String) {
        val novoId = (tarefas.maxOfOrNull { it.id } ?: 0) + 1
        val novaTarefa = Tarefa(
            id = novoId,
            descricao = descricao

        )

        tarefas = tarefas + novaTarefa

    }

    fun removerTarefa(id: Int) {

        tarefas = tarefas.filterNot { it.id == id }

    }


    fun alternarConcluida(concluido: Boolean, id: Int) {
        tarefas = tarefas.map { tarefa ->
            if (tarefa.id == id) {
            tarefa.copy(concluida = concluido)
            } else {
                tarefa
            }
        }
    }

    fun buscarTarefa(id: Int): Tarefa? {

        return tarefas.find { it.id == id }

    }

}