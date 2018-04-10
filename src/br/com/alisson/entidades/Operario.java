/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.entidades;

import java.math.BigDecimal;

/**
 *
 * @author Tiago
 */
public class Operario extends Funcionario{

    public Operario() {
    }

    public Operario(String nome, String cpf, BigDecimal salario, Integer Matricula, String Senha, boolean logado) {
        super(nome, cpf, salario, Matricula, Senha, logado);
    }
    
}
