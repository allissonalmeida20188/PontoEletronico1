package br.com.alisson.entidades;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends Funcionario {

    private List<Funcionario> lista;

    public Administrador() {
        setMatricula(1);
        setSenha("123");
        lista = new ArrayList();
    }

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        this.lista = lista;
    }

}
