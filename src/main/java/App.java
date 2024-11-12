import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        TarefaManager manager = new TarefaManager();
        NotificacaoManager notificacaoManager = new NotificacaoManager();

        // Adicionar uma nova tarefa
        manager.adicionarTarefa(1, "Estudar para prova", "Revisar os capítulos 4 e 5", LocalDate.now());

        // Editar uma tarefa existente
        manager.editarTarefa(1, "Estudar para a prova", "Revisar os capítulos 4 a 6", LocalDate.now().plusDays(3));

        // Listar todas as tarefas
        for (Tarefa tarefa : manager.listarTarefas()) {
            System.out.println(tarefa.getTitulo() + " - Prazo: " + tarefa.getPrazo());
        }

        // Verificar prazos para notificações
        notificacaoManager.verificarPrazos(manager.listarTarefas());

        // Marcar uma tarefa como concluída
        manager.marcarConcluida(1);

        // Excluir uma tarefa
        manager.excluirTarefa(1);
    }
}
