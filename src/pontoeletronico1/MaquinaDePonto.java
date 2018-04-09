
package pontoeletronico1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class MaquinaDePonto {
    Scanner sc = new Scanner(System.in);
    GregorianCalendar gc = new GregorianCalendar();
    Administrador adm = new Administrador();
    
    double horadechegada;
    double horadesaida;
    Funcionario aux;
    
    
 public void menuPrincipal() {
    int op = 0;
    do {
        System.out.println("1. Bater Ponto");
        System.out.println("2. Acessar Menu");
        System.out.println("3. Sair");
        System.out.print(">>> ");
        op = sc.nextInt();
        switch (op) {
            case 1:
                System.out.println("Bater Ponto");
                baterPonto();
                break;
            case 2:
                System.out.println("Acessar menu");
                acessarMenu();
                break;
            case 3:
                System.out.println("Encerrar programa");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

    } while (op != 3);
}

public void baterPonto() {
    System.out.println("Digite sua matrícula: ");
    int m = sc.nextInt();
    System.out.println("Digite sua senha: ");
    String s = sc.next();

    adm.lista.forEach((f) -> {
        if ((f.getMatricula() == m) && (f.getSenha().equals(s))) {
            if (f.isEstaLogado()) {
                System.out.println("Saída - Batida de ponto confirmada");
                f.setEstaLogado(false);
                horadesaida = (gc.get(Calendar.HOUR_OF_DAY) + ((double) gc
                        .get(Calendar.MINUTE) / 60));
                
              

            } else {
                
                System.out.println("Chegada - Batida de ponto confirmada");
                f.setEstaLogado(true);

                horadechegada = gc.get(Calendar.HOUR_OF_DAY)
                        + ((double) gc.get(Calendar.MINUTE) / 60);

            }
        } else {
            System.out.println("Senha ou matricula incorreta");

        }
    });
}

public void acessarMenu() {
    int op = 0;
    do {
        System.out.println("1. Menu do Administrador");
        System.out.println("2. Menu do Funcionário");
        System.out.println("3. Voltar");
        op = sc.nextInt();

        String s;
        int m;
        switch (op) {
            case 1:
                System.out.println("Digite sua matricula: ");
                m = sc.nextInt();
                System.out.println("Digite sua senha: ");
                s = sc.next();
                if ((adm.getMatricula() == m) && (adm.getSenha().equals(s))) {
                    System.out.println("Logado como adm");
                    menuDoAdm();
                } else {
                    System.out.println("Matricula ou senha incorreta");
                }
                break;
            case 2:
                System.out.println("Digite sua matricula: ");
                m = sc.nextInt();
                System.out.println("Digite sua senha: ");
                s = sc.next();
                for (Funcionario f : adm.lista) {
                    if (!f.getSenha().equals(s) || f.getMatricula() != m) {
                        System.out.println("Matricula ou senha incorreta");
                    } else {
                        aux = f;
                        System.out.println("Logado como Funcionario");
                        menuDoFunc();
                        break;
                    }
                }
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

    } while (op != 3);

}

public void menuDoAdm() {
    int op = 0;
    do {
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2.Voltar");
        System.out.println("3.Sair");
        op = sc.nextInt();
        switch (op) {
            case 1:
                System.out.println("Cadastrar Funcionário");
                Funcionario f = new Funcionario();
                System.out.println("Digite a matricula do novo funcionario: ");
                f.setMatricula(sc.nextInt());
                System.out.println("Digite a senha do novo funcionario: ");
                f.setSenha(sc.next());
                System.out.println("Digite o nome do novo funcionario: ");
                f.setNome(sc.next());
                System.out.println("Digite o CPF do novo funcionario: ");
                f.setCpf(sc.next());
                adm.cadastrarFunc(f);
                break;

            case 3:
                System.out.println("Encerrar programa");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    } while (op != 2);
    menuPrincipal();
}

public void menuDoFunc() {
    int op = 0;
    do {
        System.out.println("1. Ver Horas Trabalhadas");

        op = sc.nextInt();
        switch (op) {
            case 1:
                System.out.println("Você já trabalhou " + aux.getHorasTrab()
                        + " horas neste mês");
                break;
            case 2:
                break;
        }
    } while (op != 2);
}

public class Funcionario {

    int matricula;
    String senha;
    String nome;
    String cpf;
    boolean estaLogado;

    public Funcionario() {
    }

    public Funcionario(int matricula, String senha, String nome, String cpf, boolean estaLogado) {
        this.matricula = matricula;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.estaLogado = estaLogado;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isEstaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(boolean estaLogado) {
        this.estaLogado = estaLogado;
    }

    public double getHorasTrab() {
        return horadechegada-horadesaida;
    }

}

public class Administrador {

    int matricula;
    String senha;
    List<Funcionario> lista = new ArrayList();

    public Administrador() {
        matricula = 1;
        senha = "admin";
    }

    public Administrador(int matricula, String senha) {
        this.matricula = matricula;
        this.senha = senha;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void cadastrarFunc(Funcionario funcionario) {
        lista.add(funcionario);
    }

    public List<Funcionario> lista(){
        return lista;
    }
    
}
}