import java.time.LocalDate;
import java.util.List;

public class TarefaManager {
    private TarefaJsonDAO dao = new TarefaJsonDAO();
    private List<Tarefa> tarefas;

    public TarefaManager() {
        this.tarefas = dao.carregarTarefas();
    }

    public void adicionarTarefa(int id, String titulo, String descricao, LocalDate prazo) {
        Tarefa tarefa = new Tarefa(id, titulo, descricao, prazo);
        tarefas.add(tarefa);
        dao.salvarTarefas(tarefas);
    }

    public void editarTarefa(int id, String novoTitulo, String novaDescricao, LocalDate novoPrazo) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setTitulo(novoTitulo);
                tarefa.setDescricao(novaDescricao);
                tarefa.setPrazo(novoPrazo);
                dao.salvarTarefas(tarefas);
                break;
            }
        }
    }

    public void excluirTarefa(int id) {
        tarefas.removeIf(tarefa -> tarefa.getId() == id);
        dao.salvarTarefas(tarefas);
    }

    public void marcarConcluida(int id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                tarefa.setConcluida(true);
                dao.salvarTarefas(tarefas);
                break;
            }
        }
    }

    public List<Tarefa> listarTarefas() {
        return tarefas;
    }
}
