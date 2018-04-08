/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.maquina;

import br.com.alisson.entidades.Funcionario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author Alisson
 */
public class Ponto {

    private Funcionario funcionario;
    private LocalDate dataPonto;
    private LocalTime horario;
    private String tipo;

    public Ponto() {
    }

    public Ponto(Funcionario funcionario, LocalDate dataPonto, LocalTime horario, String tipo) {
        this.funcionario = funcionario;
        this.dataPonto = dataPonto;
        this.horario = horario;
        this.tipo = tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(LocalDate dataPonto) {
        this.dataPonto = dataPonto;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.funcionario);
        hash = 67 * hash + Objects.hashCode(this.dataPonto);
        hash = 67 * hash + Objects.hashCode(this.horario);
        hash = 67 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ponto other = (Ponto) obj;
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.dataPonto, other.dataPonto)) {
            return false;
        }
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ponto{" + "funcionario=" + funcionario + ", dataPonto=" + dataPonto + ", horario=" + horario + ", tipo=" + tipo + '}';
    }

}
