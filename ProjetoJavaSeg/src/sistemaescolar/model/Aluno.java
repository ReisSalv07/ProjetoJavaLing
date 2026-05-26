package sistemaescolar.model;

import java.util.HashMap;
import java.util.Map;

public class Aluno extends Usuario {
    private String matricula;
    private Map<String, Double> notas = new HashMap<>(); // Disciplina -> Nota
    private Map<String, Integer> faltas = new HashMap<>(); // Disciplina -> Quantidade de Faltas

    public Aluno(String login, String senha, String nome, String matricula) {
        super(login, senha, nome, Perfil.ALUNO);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public Map<String, Double> getNotas() { return notas; }
    public Map<String, Integer> getFaltas() { return faltas; }

    public void lancarNota(String disciplina, double nota) { this.notas.put(disciplina, nota); }
    public void lancarFalta(String disciplina, int qtdFaltas) { this.faltas.put(disciplina, qtdFaltas); }
}