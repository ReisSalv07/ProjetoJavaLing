# 📝 Sistema Escolar Inteligente

Um sistema de gerenciamento escolar baseado em console desenvolvido em **Java**. O projeto adota uma arquitetura em camadas (separando modelos, repositórios em memória e regras de negócio) e implementa conceitos fundamentais de Orientação a Objetos, como Herança, Polimorfismo, Encapsulamento e controle de níveis de acesso.

---

## 🚀 Funcionalidades e Regras de Negócio

### 🔐 Sistema de Autenticação (Níveis de Acesso)

O sistema adapta suas funcionalidades dinamicamente dependendo do perfil do usuário logado:

* **ADMIN (Diretor/Secretaria):** Controle total do sistema. Pode cadastrar alunos, professores e turmas, além de visualizar relatórios de desempenho de qualquer turma.
* **PROFESSOR:** Responsável pelo controle pedagógico. Pode lançar notas e faltas de alunos matriculados em suas turmas e gerar relatórios de desempenho.
* **ALUNO:** Acesso focado no próprio desempenho. Pode consultar seu histórico acadêmico e emitir o boletim escolar.

### 📊 Relatórios Inteligentes

* **Emissão de Boletim:** Exibe as notas, faltas e o status final do aluno por disciplina.
* **Status de Aprovação:** O sistema calcula automaticamente se o aluno está **APROVADO** (Nota $\ge$ 7.0 e Faltas $\le$ 10) ou **REPROVADO**.
* **Relatório por Turma:** Lista todos os alunos vinculados a uma turma específica com suas respectivas médias e situação.


## 🛠️ Credenciais Pré-Cadastradas (Para Testes)

O sistema conta com uma carga inicial de dados (`BancoDados.java`) para que você possa testar os diferentes fluxos imediatamente sem precisar cadastrar tudo do zero:

| Perfil | Usuário | Senha | O que testar? |

| **Administrador** | `admin` | `admin123` | Cadastrar novos alunos/professores e turmas. |

| **Professor** | `prof1` | `123` | Lançar nota/falta na Turma `MAT-101` para o aluno `MAT202601`. |

| **Aluno** | `aluno1` | `123` | Visualizar o boletim e histórico de notas. |

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

* **Java JDK 11** ou superior instalado.
* Git (opcional, para clonar).

### Passo a Passo pelo Terminal

1. **Clone o repositório ou baixe os arquivos:**
```bash
[[git clone https://github.com/seu-usuario/sistema-escolar-inteligente.git
cd sistema-escolar-inteligente/src](https://github.com/ReisSalv07/ProjetoJavaLing.git)](https://github.com/ReisSalv07/ProjetoJavaLing.git)

```

2. **Compile todos os arquivos Java simultaneamente:**
   ```bash
   javac com/sistemaescolar/model/*.java com/sistemaescolar/repository/*.java com/sistemaescolar/service/*.java com/sistemaescolar/view/*.java com/sistemaescolar/Main.java

3. **Execute a aplicação:**
```bash

```



java com.sistemaescolar.Main

```

---

## 🛠️ Tecnologias Utilizadas

*   **Linguagem:** Java 17
*   **Paradigma:** Orientação a Objetos (POO)
*   **Estrutura de Dados:** `List` (ArrayList) e `Map` (HashMap) para vinculação dinâmica de notas/faltas.

---

> **Nota de Implementação:** Este projeto utiliza persistência em memória volátil (`static Lists`). Caso a aplicação seja encerrada, os novos dados cadastrados serão perdidos. Em uma versão futura, esta camada pode ser facilmente substituída por um banco de dados relacional utilizando JDBC ou Spring Data JPA.

```
