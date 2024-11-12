import java.time.LocalDate;
import java.util.List;

public class NotificacaoManager {
    public void verificarPrazos(List<Tarefa> tarefas) {
        LocalDate hoje = LocalDate.now();
        for (Tarefa tarefa : tarefas) {
            if (!tarefa.isConcluida() && tarefa.getPrazo().isEqual(hoje)) {
                System.out.println("Notificação: A tarefa \"" + tarefa.getTitulo() + "\" vence hoje!");
            }
        }
    }
}
