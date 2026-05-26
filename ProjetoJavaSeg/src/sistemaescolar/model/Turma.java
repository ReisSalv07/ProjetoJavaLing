package sistemaescolar.model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private String nomeDisciplina;
    private Professor professor;
    private List<Aluno> alunosMatriculados = new ArrayList<>();

    public Turma(String codigo, String nomeDisciplina, Professor professor) {
        this.codigo = codigo;
        this.nomeDisciplina = nomeDisciplina;
        this.professor = professor;
    }

    // Getters e Setters
    public String getCodigo() { return codigo; }
    public String getNomeDisciplina() { return nomeDisciplina; }
    public Professor getProfessor() { return professor; }
    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }

    public void adicionarAluno(Aluno aluno) { this.alunosMatriculados.add(aluno); }
}
