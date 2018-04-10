/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.interfaces;

import br.com.alisson.entidades.Funcionario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alisson
 */
public interface PontoEletronico {

    public void menuPrincipal(Scanner scanner, List<Funcionario> lista);
    public void acessarMenu(Scanner scanner);
    public void registrarPonto(Funcionario funcionario, LocalDateTime horario);
    public void getFolhaPonto(Funcionario matricula);

}
