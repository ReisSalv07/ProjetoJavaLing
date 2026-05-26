package sistemaescolar.model;

public class Professor extends Usuario {
    private String especialidade;

    public Professor(String login, String senha, String nome, String Black) {
        super(login, senha, nome, Perfil.PROFESSOR);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() { return especialidade; }
}
