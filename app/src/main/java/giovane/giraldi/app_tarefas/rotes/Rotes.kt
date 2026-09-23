package giovane.giraldi.app_tarefas.rotes

import kotlinx.serialization.Serializable

@Serializable
data object Lista

@Serializable
data object Cadastro

@Serializable
data class Detalhes(val id: Int)
