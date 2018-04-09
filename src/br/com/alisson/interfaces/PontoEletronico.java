/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.interfaces;

import br.com.alisson.entidades.Funcionario;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author Alisson
 */
public interface PontoEletronico {
    
    public void menuPrincipal(Scanner scanner, Funcionario funcionario);
    public void menuDoAdm(Scanner scanner, Funcionario funcionario);
    public void menuDoFunc(Scanner scanner);
    public void acessarMenu(Scanner scanner, Funcionario funcionario);
    public void registrarPonto(Scanner scanner, Funcionario funcionario, LocalDateTime horario);
    public void getFolhaPonto(Funcionario matricula);
    
}
