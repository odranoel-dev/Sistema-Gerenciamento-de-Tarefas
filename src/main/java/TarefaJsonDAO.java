import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.google.gson.reflect.TypeToken;



public class TarefaJsonDAO {
    private static final String ARQUIVO_JSON = "tarefas.json";
    private Gson gson;

    public TarefaJsonDAO() {
        // Registrar o LocalDateAdapter para serialização e desserialização de LocalDate
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }

    // Carregar as tarefas do arquivo JSON
    public List<Tarefa> carregarTarefas() {
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            Type tarefaListType = new TypeToken<ArrayList<Tarefa>>(){}.getType();
            List<Tarefa> tarefas = gson.fromJson(reader, tarefaListType);

            // Verificar se o arquivo está vazio e inicializar uma lista vazia
            if (tarefas == null) {
                tarefas = new ArrayList<>();
            }
            return tarefas;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    // Salvar as tarefas no arquivo JSON
    public void salvarTarefas(List<Tarefa> tarefas) {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(tarefas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
