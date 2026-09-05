package giovane.giraldi.app_tarefas.models

data class Tarefa(
    val id: Int,
    val descricao: String,
    val concluida: Boolean = false
)