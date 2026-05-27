package sistemaescolar.interfaces;

import sistemaescolar.controller.EscolaController;
import sistemaescolar.model.Perfil;
import sistemaescolar.model.Usuario;
import java.util.Scanner;

public class MenuConsole {
    private EscolaController controller = new EscolaController();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            if (controller.obterUsuarioLogado() == null) {
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

        if (controller.efetuarLogin(login, senha)) {
            System.out.println("Acesso liberado! Bem-vindo, " + controller.obterUsuarioLogado().getNome());
        } else {
            System.out.println("Falha na autenticação. Verifique os dados digitados.");
        }
    }

    private void exibirMenuPrincipal() {
        Usuario logado = controller.obterUsuarioLogado();
        System.out.println("\n--- MENU PRINCIPAL (" + logado.getPerfil() + ") ---");

        // Renderização dinâmica baseada no Perfil de Acesso obtido do controlador
        if (logado.getPerfil() == Perfil.ADMIN) {
            System.out.println("1. Cadastrar Aluno ou Professor");
            System.out.println("2. Cadastrar Nova Turma");
            System.out.println("3. Emitir Relatório Geral de Turma");
        } else if (logado.getPerfil() == Perfil.PROFESSOR) {
            System.out.println("1. Lançar Notas e Faltas");
            System.out.println("2. Ver Relatório da Turma");
        } else if (logado.getPerfil() == Perfil.ALUNO) {
            System.out.println("1. Ver Meu Boletim / Histórico");
        }

        System.out.println("0. Sair (Logout)");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            processarComando(opcao);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números correspondentes às opções do menu.");
        }
    }

    private void processarComando(int opcao) {
        if (opcao == 0) {
            controller.efetuarLogout();
            return;
        }

        Perfil perfil = controller.obterUsuarioLogado().getPerfil();

        // --- FLUXO DO ADMINISTRADOR --- //
        if (perfil == Perfil.ADMIN) {
            switch (opcao) {
                case 1:
                    System.out.print("Nome do Usuário: "); String nome = scanner.nextLine();
                    System.out.print("Login de Acesso: "); String log = scanner.nextLine();
                    System.out.print("Senha de Acesso: "); String senha = scanner.nextLine();
                    System.out.print("Tipo de Conta (1 - Aluno, 2 - Professor): ");
                    int tipo = Integer.parseInt(scanner.nextLine());

                    if (tipo == 1) {
                        System.out.print("Código de Matrícula: "); String mat = scanner.nextLine();
                        controller.cadastrarAluno(log, senha, nome, mat);
                    } else {
                        System.out.print("Especialidade do Professor: "); String esp = scanner.nextLine();
                        controller.cadastrarProfessor(log, senha, nome, esp);
                    }
                    break;
                case 2:
                    System.out.print("Código Identificador da Turma: "); String cod = scanner.nextLine();
                    System.out.print("Nome da Disciplina: "); String disc = scanner.nextLine();
                    controller.cadastrarTurma(cod, disc);
                    break;
                case 3:
                    System.out.print("Digite o código da turma para o relatório: "); String codRelatorio = scanner.nextLine();
                    controller.gerarRelatorioDaTurma(codRelatorio);
                    break;
                default:
                    System.out.println("Opção inválida para o perfil Administrador.");
            }
        }

        // --- FLUXO DO PROFESSOR --- //
        else if (perfil == Perfil.PROFESSOR) {
            switch (opcao) {
                case 1:
                    System.out.print("Código da Turma: "); String codTurma = scanner.nextLine();
                    System.out.print("Matrícula do Aluno: "); String matAluno = scanner.nextLine();
                    System.out.print("Nota Final (0.0 a 10.0): "); double nota = Double.parseDouble(scanner.nextLine());
                    System.out.print("Quantidade de Faltas: "); int faltas = Integer.parseInt(scanner.nextLine());
                    controller.lancarDesempenho(codTurma, matAluno, nota, faltas);
                    break;
                case 2:
                    System.out.print("Digite o código da sua turma: "); String codMinhaTurma = scanner.nextLine();
                    controller.gerarRelatorioDaTurma(codMinhaTurma);
                    break;
                default:
                    System.out.println("Opção inválida para o perfil Professor.");
            }
        }

        // --- FLUXO DO ALUNO --- //
        else if (perfil == Perfil.ALUNO) {
            switch (opcao) {
                case 1:
                    controller.gerarBoletimDoAluno();
                    break;
                default:
                    System.out.println("Opção inválida para o perfil Aluno.");
            }
        }
    }
}
