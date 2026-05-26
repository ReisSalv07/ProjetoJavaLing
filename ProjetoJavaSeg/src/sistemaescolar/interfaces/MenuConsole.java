package sistemaescolar.interfaces;

import sistemaescolar.model.*;
import sistemaescolar.service.*;
import java.util.Scanner;

public class MenuConsole {
    private AutenticacaoService authService = new AutenticacaoService();
    private SecretariaService secretaria = new SecretariaService();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            if (AutenticacaoService.usuarioLogado == null) {
                exibirMenuLogin();
            } else {
                exibirMenuPrincipal();
            }
        }
    }

    private void exibirMenuLogin() {
        System.out.println("\n=== LOGIN - SISTEMA ESCOLAR ===");
        System.out.print("Usuário: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (authService.login(login, senha)) {
            System.out.println("Login efetuado com sucesso! Bem-vindo, " + AutenticacaoService.usuarioLogado.getNome());
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }

    private void exibirMenuPrincipal() {
        Usuario logado = AutenticacaoService.usuarioLogado;
        System.out.println("\n--- MENU PRINCIPAL (" + logado.getPerfil() + ") ---");

        if (logado.getPerfil() == Perfil.ADMIN) {
            System.out.println("1. Cadastrar Aluno/Professor");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Emitir Relatório Geral de Turma");
        } else if (logado.getPerfil() == Perfil.PROFESSOR) {
            System.out.println("1. Lançar Notas e Faltas");
            System.out.println("2. Ver Relatório da Turma");
        } else if (logado.getPerfil() == Perfil.ALUNO) {
            System.out.println("1. Ver Meu Boletim / Histórico");
        }

        System.out.println("0. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        processarComando(opcao);
    }

    private void processarComando(int opcao) {
        if (opcao == 0) {
            authService.logout();
            System.out.println("Sessão encerrada.");
            return;
        }

        Perfil perfil = AutenticacaoService.usuarioLogado.getPerfil();

        // Fluxo do Administrador
        if (perfil == Perfil.ADMIN) {
            if (opcao == 1) {
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Login: "); String log = scanner.nextLine();
                System.out.print("Senha: "); String senha = scanner.nextLine();
                System.out.print("Tipo (1-Aluno, 2-Professor): "); int tipo = Integer.parseInt(scanner.nextLine());

                if (tipo == 1) {
                    System.out.print("Matrícula: "); String mat = scanner.nextLine();
                    secretaria.cadastrarUsuario(new Aluno(log, senha, nome, mat));
                } else {
                    System.out.print("Especialidade: "); String esp = scanner.nextLine();
                    secretaria.cadastrarUsuario(new Professor(log, senha, nome, esp));
                }
            } else if (opcao == 2) {
                System.out.print("Código da Turma: "); String cod = scanner.nextLine();
                System.out.print("Nome da Disciplina: "); String disc = scanner.nextLine();
                secretaria.cadastrarTurma(new Turma(cod, disc, null));
            } else if (opcao == 3) {
                System.out.print("Código da Turma: "); String cod = scanner.nextLine();
                secretaria.relatorioTurma(cod);
            }
        }

        // Fluxo do Professor
        else if (perfil == Perfil.PROFESSOR) {
            if (opcao == 1) {
                System.out.print("Código da Turma: "); String cod = scanner.nextLine();
                System.out.print("Matrícula do Aluno: "); String mat = scanner.nextLine();
                System.out.print("Nota: "); double nota = Double.parseDouble(scanner.nextLine());
                System.out.print("Faltas: "); int faltas = Integer.parseInt(scanner.nextLine());
                secretaria.lancarNotaEFalta(cod, mat, nota, faltas);
            } else if (opcao == 2) {
                System.out.print("Código da Turma: "); String cod = scanner.nextLine();
                secretaria.relatorioTurma(cod);
            }
        }

        // Fluxo do Aluno
        else if (perfil == Perfil.ALUNO) {
            if (opcao == 1) {
                secretaria.emitirBoletim((Aluno) AutenticacaoService.usuarioLogado);
            }
        }
    }
}
