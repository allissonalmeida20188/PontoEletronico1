/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.maquina;

import br.com.alisson.entidades.Administrador;
import br.com.alisson.entidades.Funcionario;
import br.com.alisson.entidades.Operario;
import br.com.alisson.interfaces.PontoEletronico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Alisson
 */
public class PontoEletronicoGeral implements PontoEletronico {

    Map<Integer, FolhaPonto> registroPonto;

    public PontoEletronicoGeral() {
        registroPonto = new HashMap();
    }

    @Override
    public void menuPrincipal(Scanner sc, Funcionario funcionario) {
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
                    System.out.println("Qual a matricula?");
                    registrarPonto(sc, funcionario, LocalDateTime.now());
                    break;
                case 2:
                    System.out.println("Acessar menu");
                    acessarMenu(sc, funcionario);
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

    @Override
    public void menuDoAdm(Scanner scanner, Funcionario funcionario) {
        int op = 0;
        do {
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Ver folha de ponto de funcionário");
            System.out.println("3. Voltar");
            System.out.println("4. Sair");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Cadastrar Funcionário");
                    Funcionario f = new Operario();
                    System.out.println("Digite a matricula do novo funcionario: ");
                    f.setMatricula(scanner.nextInt());
                    System.out.println("Digite a senha do novo funcionario: ");
                    f.setSenha(scanner.next());
                    System.out.println("Digite o nome do novo funcionario: ");
                    f.setNome(scanner.next());
                    System.out.println("Digite o CPF do novo funcionario: ");
                    f.setCpf(scanner.next());
                    ((Administrador) funcionario).getLista().add(f);
                    break;
                case 2:
                    getFolhaPonto(funcionario);
                    break;
                case 4:
                    System.out.println("Encerrar programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (op != 2);
        menuPrincipal(scanner, funcionario);
    }

    @Override
    public void menuDoFunc(Scanner scanner) {
        int op = 0;
        do {
            System.out.println("1. Ver Horas Trabalhadas");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    //System.out.println("Você já trabalhou " + aux.getHorasTrab() + " horas neste mês");
                    break;
                case 2:
                    break;
            }
        } while (op != 2);
    }

    @Override
    public void registrarPonto(Scanner scanner, Funcionario funcionario, LocalDateTime horario) {
        System.out.println("Digite sua matrícula: ");
        int m = scanner.nextInt();
        System.out.println("Digite sua senha: ");
        String s = scanner.next();

        ((Administrador) funcionario).getLista().forEach((f) -> {
            if ((f.getMatricula() == m) && (f.getSenha().equals(s))) {
                if (registroPonto.get(f.getMatricula()) != null) {
                    FolhaPonto fp = registroPonto.get(f.getMatricula());
                    List<Ponto> p = fp.getListaPonto() == null ? new ArrayList() : fp.getListaPonto();
                    if (p.size() > 0) {
                        if (Objects.equals(p.get(p.size() - 1), "E")) {
                            p.add(new Ponto(f, LocalDate.now(), LocalTime.now(), "S"));
                        } else {
                            p.add(new Ponto(f, LocalDate.now(), LocalTime.now(), "E"));
                        }
                    } else {
                        p.add(new Ponto(f, LocalDate.now(), LocalTime.now(), "E"));
                    }
                }
            } else {
                System.out.println("Senha ou matricula incorreta");
            }
        });
    }

    @Override
    public void acessarMenu(Scanner scanner, Funcionario funcionario) {
        int op = 0;
        do {
            System.out.println("1. Menu do Administrador");
            System.out.println("2. Menu do Funcionário");
            System.out.println("3. Voltar");
            op = scanner.nextInt();

            String s;
            int m;
            switch (op) {
                case 1:
                    System.out.println("Digite sua matricula: ");
                    m = scanner.nextInt();
                    System.out.println("Digite sua senha: ");
                    s = scanner.next();
                    if ((funcionario.getMatricula() == m) && (funcionario.getSenha().equals(s))) {
                        System.out.println("Logado como adm");
                        menuDoAdm(scanner, funcionario);
                    } else {
                        System.out.println("Matricula ou senha incorreta");
                    }
                    break;
                case 2:
                    System.out.println("Digite sua matricula: ");
                    m = scanner.nextInt();
                    System.out.println("Digite sua senha: ");
                    s = scanner.next();
                    for (Funcionario f : ((Administrador) funcionario).getLista()) {
                        if (!f.getSenha().equals(s) || f.getMatricula() != m) {
                            System.out.println("Matricula ou senha incorreta");
                        } else {
                            //aux = f;
                            System.out.println("Logado como Funcionario");
                            menuDoFunc(scanner);
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

    @Override
    public void getFolhaPonto(Funcionario funcionario) {
        if (registroPonto.get(funcionario.getMatricula()) != null) {
            FolhaPonto fp = registroPonto.get(funcionario.getMatricula());
            System.out.println("------------Folha de Ponto--------------");
            System.out.println("Nome do Funcionário: " + funcionario.getNome());
            if (fp.getListaPonto() != null) {
                fp.getListaPonto().forEach(p -> {
                    System.out.println(p.getTipo() + p.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + p.getDataPonto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                });
            }else{
                System.out.println("Este funcionário não registrou nenhum ponto até o momento");
            }
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Não existem folhas de ponto registratas");
        }
    }

}
