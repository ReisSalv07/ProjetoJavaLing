package sistemaescolar.service;

import sistemaescolar.model.*;
import sistemaescolar.repository.BancoDados;

public class SecretariaService {

    // CRUD Aluno / Professor
    public void cadastrarUsuario(Usuario usuario) {
        BancoDados.usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // CRUD Turma
    public void cadastrarTurma(Turma turma) {
        BancoDados.turmas.add(turma);
        System.out.println("Turma cadastrada com sucesso!");
    }

    public Turma buscarTurma(String codigo) {
        for (Turma t : BancoDados.turmas) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) return t;
        }
        return null;
    }

    // Lançamentos
    public void lancarNotaEFalta(String codigoTurma, String matricula, double nota, int faltas) {
        Turma turma = buscarTurma(codigoTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return;
        }

        for (Aluno a : turma.getAlunosMatriculados()) {
            if (a.getMatricula().equalsIgnoreCase(matricula)) {
                a.lancarNota(turma.getNomeDisciplina(), nota);
                a.lancarFalta(turma.getNomeDisciplina(), faltas);
                System.out.println("Dados lançados para o aluno " + a.getNome());
                return;
            }
        }
        System.out.println("Aluno não está nesta turma.");
    }

    // Relatório: Boletim
    public void emitirBoletim(Aluno aluno) {
        System.out.println("\n=== BOLETIM: " + aluno.getNome().toUpperCase() + " ===");
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("----------------------------------------");

        for (String disciplina : aluno.getNotas().keySet()) {
            double nota = aluno.getNotas().getOrDefault(disciplina, 0.0);
            int faltas = aluno.getFaltas().getOrDefault(disciplina, 0);
            String status = (nota >= 7.0 && faltas <= 10) ? "APROVADO" : "REPROVADO";

            System.out.printf("Disciplina: %s | Nota: %.1f | Faltas: %d | Status: %s\n",
                    disciplina, nota, faltas, status);
        }
    }

    // Relatório: Alunos Aprovados/Reprovados por Turma
    public void relatorioTurma(String codigoTurma) {
        Turma turma = buscarTurma(codigoTurma);
        if (turma == null) return;

        System.out.println("\n--- Relatório da Turma: " + turma.getNomeDisciplina() + " ---");
        for (Aluno a : turma.getAlunosMatriculados()) {
            double nota = a.getNotas().getOrDefault(turma.getNomeDisciplina(), 0.0);
            int faltas = a.getFaltas().getOrDefault(turma.getNomeDisciplina(), 0);
            String status = (nota >= 7.0 && faltas <= 10) ? "APROVADO" : "REPROVADO";

            System.out.printf("Aluno: %s | Nota: %.1f | Faltas: %d [%s]\n", a.getNome(), nota, faltas, status);
        }
    }
}
