package br.com.alisson.maquina;

import br.com.alisson.entidades.Funcionario;
import br.com.alisson.entidades.Operario;
import br.com.alisson.entidades.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MaquinaDePonto {

    private Scanner sc;
    private PontoEletronicoGeral pontoEletronico;
    private String login;
    private String senha;
    private Usuario user;
    private List<Funcionario> lista;

    public MaquinaDePonto() {
        user = new Usuario("admin", "admin");
        lista = new ArrayList();
        lista.add(new Operario("Alisson Cahum", "674.983.475-32", new BigDecimal("2345.50"), 1, "123", false));
        lista.add(new Operario("João Castanha", "987.234.765-52", new BigDecimal("980.00"), 2, "123", false));
        lista.add(new Operario("Lucas Pestanha", "593.798.623-43", new BigDecimal("5300.80"), 3, "123", false));
        lista.add(new Operario("Clóvis Montanha", "842.737.324-98", new BigDecimal("1230.99"), 4, "123", false));
        lista.add(new Operario("Giseli dos Anjos", "345.678.909-87", new BigDecimal("5800.90"), 5, "123", false));
    }

    /**
     * Inicia o sistema
     */
    public void iniciarSistema() {
        try {
            //cria os objetos iniciais
            sc = new Scanner(System.in);
            pontoEletronico = new PontoEletronicoGeral(this);
            //solicita login ao usuário do sistema
            System.out.println("--------Bem vindo ao sistema de controle de folha de ponto--------");
            System.out.println("Faça o login para continuar ");
            //variaveis de instancia
            boolean acesso = false;
            int i = 0;
            //continua pedindo a senha até três erros caso o não acerte
            while (!acesso) {
                System.out.print("Login: ");
                login = sc.nextLine();
                System.out.print("Senha: ");
                senha = sc.nextLine();
                //verifica o login e senha
                acesso = Objects.equals(login, user.getLogin()) && Objects.equals(senha, user.getSenha());
                i++;
                if (acesso == false) {
                    System.out.println("Login ou Senha inválidos!!!\n\nTente novamente\n");
                }

                if (i == 3) {
                    System.out.println("Encerrando sistema por excesso de tentativas");
                    System.exit(0);
                }
            }
            //abre o menu inicial
            System.out.println("\nLogin efetuado com sucesso!!!\n");
            pontoEletronico.menuPrincipal(sc, lista);
        } catch (Exception e) {
            System.out.println("Erro ao tentar iniciar o sistema, contate o administrador");
        }
    }

    public List<Funcionario> getLista() {
        return lista;
    }

}
