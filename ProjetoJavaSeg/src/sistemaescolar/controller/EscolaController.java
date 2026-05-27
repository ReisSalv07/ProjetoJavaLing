package sistemaescolar.controller;

import sistemaescolar.model.*;
import sistemaescolar.service.*;

public class EscolaController {
    private AutenticacaoService authService = new AutenticacaoService();
    private SecretariaService secretariaService = new SecretariaService();

    // --- CONTROLE DE ACESSO --- //
    public boolean efetuarLogin(String login, String senha) {
        if (login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            System.out.println("Erro: Usuário e senha não podem ser vazios.");
            return false;
        }
        return authService.login(login, senha);
    }

    public void efetuarLogout() {
        authService.logout();
        System.out.println("Sessão finalizada com sucesso no Controlador.");
    }

    public Usuario obterUsuarioLogado() {
        return AutenticacaoService.usuarioLogado;
    }

    // --- CADASTROS (ADMIN) --- //
    public void cadastrarAluno(String login, String senha, String nome, String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            System.out.println("Erro: Matrícula inválida.");
            return;
        }
        Aluno novoAluno = new Aluno(login, senha, nome, matricula);
        secretariaService.cadastrarUsuario(novoAluno);
    }

    public void cadastrarProfessor(String login, String senha, String nome, String especialidade) {
        Professor novoProf = new Professor(login, senha, nome, especialidade);
        secretariaService.cadastrarUsuario(novoProf);
    }

    public void cadastrarTurma(String codigo, String nomeDisciplina) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Erro: Código da turma é obrigatório.");
            return;
        }
        Turma novaTurma = new Turma(codigo, nomeDisciplina, null);
        secretariaService.cadastrarTurma(novaTurma);
    }

    // --- LANÇAMENTOS (PROFESSOR) --- //
    public void lancarDesempenho(String codigoTurma, String matricula, double nota, int faltas) {
        if (nota < 0.0 || nota > 10.0) {
            System.out.println("Erro: A nota deve ser entre 0.0 e 10.0.");
            return;
        }
        if (faltas < 0) {
            System.out.println("Erro: O número de faltas não pode ser negativo.");
            return;
        }
        secretariaService.lancarNotaEFalta(codigoTurma, matricula, nota, faltas);
    }

    // --- RELATÓRIOS --- //
    public void gerarBoletimDoAluno() {
        Usuario logado = obterUsuarioLogado();
        if (logado instanceof Aluno) {
            secretariaService.emitirBoletim((Aluno) logado);
        } else {
            System.out.println("Erro: Apenas usuários do tipo Aluno podem consultar boletins individuais.");
        }
    }

    public void gerarRelatorioDaTurma(String codigoTurma) {
        if (codigoTurma == null || codigoTurma.trim().isEmpty()) {
            System.out.println("Erro: É necessário informar o código da turma.");
            return;
        }
        secretariaService.relatorioTurma(codigoTurma);
    }
}
