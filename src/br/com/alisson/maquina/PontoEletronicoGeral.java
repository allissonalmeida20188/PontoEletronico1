/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.maquina;

import br.com.alisson.entidades.Funcionario;
import br.com.alisson.interfaces.PontoEletronico;
import br.com.alisson.util.Calculos;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Alisson
 */
public final class PontoEletronicoGeral implements PontoEletronico {

    private Map<Integer, FolhaPonto> registroPonto;
    private MaquinaDePonto maquina;

    public PontoEletronicoGeral(MaquinaDePonto maquina) {
        registroPonto = new HashMap();
        this.maquina = maquina;
    }

    /**
     * Método que inicia o menu principal
     *
     * @param scanner scanner do shell
     * @param lista lista do tipo Funcionario
     */
    @Override
    public void menuPrincipal(Scanner scanner, List<Funcionario> lista) {
        int op = 0;

        //cria as folhas de ponto de cada funcionário registrado
        registroPonto.put(1, new FolhaPonto());
        registroPonto.put(2, new FolhaPonto());
        registroPonto.put(3, new FolhaPonto());
        registroPonto.put(4, new FolhaPonto());
        registroPonto.put(5, new FolhaPonto());

        do {
            System.out.println("-----Menu Principal do Sistema-----");
            System.out.println("1. Bater Ponto");
            System.out.println("2. Acessar Menu");
            System.out.println("3. Sair");
            System.out.print(">>> ");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Bater Ponto");
                    Funcionario func = verificacaoAcesso(scanner, lista);
                    if (func != null) {
                        registrarPonto(func, LocalDateTime.now());
                    } else {
                        System.out.println("Não existem funcionário registrados");
                    }
                    break;
                case 2:
                    System.out.println("Acessar menu");
                    acessarMenu(scanner);
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
        //menuPrincipal(sc, lista);
    }

    /**
     * Menu de acesso a funções do sistema
     *
     * @param scanner Entrada do usuário
     */
    @Override
    public void acessarMenu(Scanner scanner) {
        int op = 0;
        do {
            System.out.println("1. Ver folha de ponto de funcionário");
            System.out.println("2. Ver horas trabalhadas de funcionário");
            System.out.println("3. Exportar folha de ponto de funcionário");
            System.out.println("4. Voltar");
            System.out.println("5. Sair");
            System.out.print(">>>");
            op = scanner.nextInt();

            Funcionario func;

            switch (op) {
                case 1:
                    func = verificacaoAcesso(scanner, maquina.getLista());
                    if (func != null) {
                        this.getFolhaPonto(func);
                    } else {
                        System.out.println("Matricula ou senha incorreta");
                    }
                    break;
                case 2:
                    func = verificacaoAcesso(scanner, maquina.getLista());
                    if (func != null) {
                        Calculos calculo = new Calculos();
                        System.out.println(calculo.getHorasTrabalhadas(registroPonto.get(func.getMatricula()), null));
                    } else {
                        System.out.println("Matricula ou senha incorreta");
                    }
                    break;
                case 3:
                    func = verificacaoAcesso(scanner, maquina.getLista());
                    if (func != null) {
                        this.exportarFolhaPonto(scanner, registroPonto.get(func.getMatricula()));
                    } else {
                        System.out.println("Matricula ou senha incorreta");
                    }
                    break;
                case 4:
                    menuPrincipal(scanner, maquina.getLista());
                    break;
                case 5:
                    System.out.println("Encerrando sistema");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        } while (op != 5);
    }

    /**
     * Método que registra a entrada de ponto dos funcionários
     *
     * @param funcionario Funcionario que registrará o ponto
     * @param horario Horário de entrada do ponto
     */
    @Override
    public void registrarPonto(Funcionario funcionario, LocalDateTime horario) {
        Ponto ponto = null;
        if (registroPonto.get(funcionario.getMatricula()) != null) {
            FolhaPonto fp = registroPonto.get(funcionario.getMatricula());
            List<Ponto> p = fp.getListaPonto() == null ? new ArrayList() : fp.getListaPonto();
            fp.setListaPonto(p);
            if (p.size() > 0) {
                if (Objects.equals(p.get(p.size() - 1).getTipo(), "Entrada") == true) {
                    ponto = new Ponto(funcionario, LocalDate.now(), LocalTime.now(), "Saída");
                    p.add(ponto);
                } else {
                    ponto = new Ponto(funcionario, LocalDate.now(), LocalTime.now(), "Entrada");
                    p.add(ponto);
                }
            } else {
                ponto = new Ponto(funcionario, LocalDate.now(), LocalTime.now(), "Entrada");
                p.add(ponto);
            }
        }
        String msg = "Ponto: ".concat(ponto.getTipo() + " de " + ponto.getFuncionario().getNome());
        msg = msg.concat(" - ".concat(ponto.getDataPonto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        msg = msg.concat(" às ".concat(ponto.getHorario().format(DateTimeFormatter.ofPattern("HH:mm"))) + "\n");
        System.out.println(msg);
    }

    /**
     * Este método escreve a folha de ponto no prompt de comando
     *
     * @param funcionario Funcionário que será mostrada a folha de ponto
     */
    @Override
    public void getFolhaPonto(Funcionario funcionario) {
        if (registroPonto.get(funcionario.getMatricula()) != null) {
            FolhaPonto fp = registroPonto.get(funcionario.getMatricula());
            System.out.println("\n\n\n");
            System.out.println("------------------------Folha de Ponto---------------------------");
            System.out.println("Nome do Funcionário: " + funcionario.getNome() + "\n");
            if (fp.getListaPonto() != null) {
                fp.getListaPonto().forEach(p -> {
                    String msg = p.getTipo() + ": " + p.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + p.getDataPonto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (Objects.equals("Entrada", p.getTipo())) {
                        System.out.print(msg);
                    } else {
                        System.out.println(" - ".concat(msg));
                    }
                });
            } else {
                System.out.println("Este funcionário não registrou nenhum ponto até o momento");
            }
            System.out.println("\n----------------------------------------------------------------\n\n\n");
        } else {
            System.out.println("Não existem folhas de ponto registratas");
        }
    }

    /**
     * Método que gera arquivo txt com a folha de ponto do funcioário
     *
     * @param scanner Entrada do usuário
     * @param folhaPonto Folha de ponto a ser gravada
     */
    public void exportarFolhaPonto(Scanner scanner, FolhaPonto folhaPonto) {
        System.out.print("\nDeseja realmente exporta a folha de ponto? (Y/N): ");
        String resp = scanner.next();
        if (Objects.equals("y", resp.toLowerCase())) {
            try {
                //caminho onde o arquivo será gravado
                String path = System.getProperty("user.home") + "\\Documents\\FolhaPonto_".concat(folhaPonto.getListaPonto().get(0).getFuncionario().getNome());
                //cria escritor de arquivo
                FileWriter fw = new FileWriter(path.concat(".txt"));
                //saida de caracteres para arquivo
                PrintWriter writer = new PrintWriter(fw);

                if (folhaPonto.getListaPonto() != null) {
                    writer.println("------------------------Folha de Ponto---------------------------");
                    writer.println("Nome do Funcionário: " + folhaPonto.getListaPonto().get(0).getFuncionario().getNome() + "\n");
                    folhaPonto.getListaPonto().forEach(p -> {
                        String msg = p.getTipo() + ": " + p.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + p.getDataPonto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        if (Objects.equals("Entrada", p.getTipo())) {
                            writer.print(msg);
                        } else {
                            writer.println(" - ".concat(msg));
                        }
                    });
                    writer.println("\n----------------------------------------------------------------");
                } else {
                    System.out.println("Este funcionário não registrou nenhum ponto até o momento");
                }

                //encerra a escrita do arquivo
                fw.close();
                System.out.println("Arquivo salvo em ".concat(path));
                System.out.println("\n\n");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Método de verificação de acesso
     *
     * @param scanner Entrada do usuário
     * @param lista Lista de Funcionários
     * @return Funcionario caso verificação tenha sucesso
     */
    private Funcionario verificacaoAcesso(Scanner scanner, List<Funcionario> lista) {
        System.out.print("Digite sua matricula: ");
        int matricula = scanner.nextInt();
        System.out.print("Digite sua senha: ");
        String senha = scanner.next();

        Iterator<Funcionario> f = lista.iterator();
        while (f.hasNext()) {
            Funcionario func = f.next();
            if (Objects.equals(matricula, func.getMatricula()) && Objects.equals(senha, func.getSenha())) {
                return func;
            }
        }
        return null;
    }
}
