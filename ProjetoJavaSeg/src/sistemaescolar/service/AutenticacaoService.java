package sistemaescolar.service;

import sistemaescolar.model.Usuario;
import sistemaescolar.repository.BancoDados;

public class AutenticacaoService {
    public static Usuario usuarioLogado = null;

    public boolean login(String login, String senha) {
        for (Usuario u : BancoDados.usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                usuarioLogado = u;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        usuarioLogado = null;
    }
}
