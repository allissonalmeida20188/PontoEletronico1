package br.com.alisson.entidades;

import java.math.BigDecimal;

public abstract class Funcionario {

    private String nome;
    private String cpf;
    private BigDecimal salario;
    private Integer Matricula;
    private String Senha;
    private boolean logado;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, BigDecimal salario, Integer Matricula, String Senha, boolean logado) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.Matricula = Matricula;
        this.Senha = Senha;
        this.logado = logado;
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Integer getMatricula() {
        return Matricula;
    }

    public void setMatricula(Integer Matricula) {
        this.Matricula = Matricula;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

}
