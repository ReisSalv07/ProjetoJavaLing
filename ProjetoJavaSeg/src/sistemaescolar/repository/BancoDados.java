package sistemaescolar.repository;

import sistemaescolar.model.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    // Listas globais do sistema
    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Turma> turmas = new ArrayList<>();

    // Carga inicial de dados para conseguir testar o sistema
    static {
        // Criando um Administrador padrão
        usuarios.add(new Usuario("admin", "admin123", "Diretor Geral", Perfil.ADMIN) {});

        // Criando um Professor padrão
        Professor prof = new Professor("prof1", "123", "Dr. Augusto", "Matemática");
        usuarios.add(prof);

        // Criando um Aluno padrão
        Aluno aluno = new Aluno("aluno1", "123", "Mariana Silva", "MAT202601");
        usuarios.add(aluno);

        // Criando uma Turma padrão
        Turma turma = new Turma("MAT-101", "Matemática", prof);
        turma.adicionarAluno(aluno);
        turmas.add(turma);
    }
}
