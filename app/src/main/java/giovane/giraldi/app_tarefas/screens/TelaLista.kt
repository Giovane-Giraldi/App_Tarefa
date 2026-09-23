package giovane.giraldi.app_tarefas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import giovane.giraldi.app_tarefas.models.Tarefa

@Composable
fun TelaLista(
    tarefas: List<Tarefa>,
    onNovaTarefa: () -> Unit,
    onTarefaClick: (Tarefa) -> Unit,
    onConcluir: (Int) -> Unit,
    onExcluir: (Int) -> Unit
) {

    val roxo = Color(0xFF7E64B8)
    val fundo = Color(0xFFFFF8FF)
    val fundoCard = Color(0xFFF0EAF2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fundo)
            .padding(16.dp)
    ) {

        Button(
            onClick = onNovaTarefa,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = roxo
            )
        ) {
            Text(
                text = "Nova Tarefa"
            )
        }

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(tarefas) { tarefa ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = fundoCard
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 12.dp,
                                vertical = 14.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = tarefa.concluida,
                            onCheckedChange = {
                                onConcluir(tarefa.id)
                            }
                        )

                        Text(
                            text = tarefa.descricao,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                                .clickable {
                                    onTarefaClick(tarefa)
                                }
                        )

                        TextButton(
                            onClick = {
                                onExcluir(tarefa.id)
                            }
                        ) {
                            Text(
                                text = "Excluir",
                                color = roxo
                            )
                        }
                    }
                }
            }
        }
    }
}