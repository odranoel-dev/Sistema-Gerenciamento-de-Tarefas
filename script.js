// Função para listar as tarefas no front-end
function listarTarefas() {
    fetch('http://localhost:8080/api/tarefas') // Substitua pela URL do seu back-end
        .then(response => response.json())
        .then(tarefas => {
            const tbody = document.querySelector('table tbody');
            tbody.innerHTML = ''; // Limpa a tabela antes de atualizar

            tarefas.forEach(tarefa => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${tarefa.id}</td>
                    <td>${tarefa.titulo}</td>
                    <td>${tarefa.descricao}</td>
                    <td>${tarefa.prazo}</td>
                    <td>${tarefa.concluida ? 'Sim' : 'Não'}</td>
                `;
                tbody.appendChild(tr); // Adiciona cada tarefa à tabela
            });
        })
        .catch(error => {
            console.error('Erro ao carregar tarefas:', error);
        });
}

// Carregar as tarefas ao iniciar a página
window.onload = listarTarefas;

// Função para adicionar uma nova tarefa
document.getElementById('formTarefa').addEventListener('submit', function (e) {
    e.preventDefault(); // Impede o envio do formulário

    const novaTarefa = {
        titulo: document.getElementById('titulo').value,
        descricao: document.getElementById('descricao').value,
        prazo: document.getElementById('prazo').value
    };

    fetch('http://localhost:8080/api/tarefas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novaTarefa)
    })
        .then(response => response.json())
        .then(tarefa => {
            listarTarefas(); // Atualiza a lista de tarefas após adicionar
            // Limpa o formulário
            document.getElementById('formTarefa').reset();
        })
        .catch(error => {
            console.error('Erro ao adicionar tarefa:', error);
        });
});
