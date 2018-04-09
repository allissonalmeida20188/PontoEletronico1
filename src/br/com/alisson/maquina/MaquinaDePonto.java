package br.com.alisson.maquina;

import br.com.alisson.entidades.Administrador;
import br.com.alisson.entidades.Usuario;
import java.util.Objects;
import java.util.Scanner;

public class MaquinaDePonto {

    private Scanner sc;
    private PontoEletronicoGeral pontoEletronico;
    private String login;
    private String senha;
    private Usuario user;

    public MaquinaDePonto() {
        user = new Usuario("admin", "admin");
    }

    /**
     * Inicia o sistema
     */
    public void iniciarSistema() {
        try {
            //cria os objetos iniciais
            sc = new Scanner(System.in);
            pontoEletronico = new PontoEletronicoGeral();
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
            pontoEletronico.menuPrincipal(sc, new Administrador());
        } catch (Exception e) {
            System.out.println("Erro ao tentar iniciar o sistema, contate o administrador");
        }
    }

}
