/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alisson.util;

import br.com.alisson.maquina.FolhaPonto;
import br.com.alisson.maquina.Ponto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alisson
 */
public final class Calculos {

    public Calculos() {
    }

    /**
     * Método que calcula horas trabalhadas de um determinado dia ou total da
     * folha
     *
     * @param folhaPonto Folha de ponto do funcionário
     * @param dia Caso informado calcula o dia que será calculado
     * @return String contendo as horas trabalhadas
     */
    public String getHorasTrabalhadas(FolhaPonto folhaPonto, LocalDate dia) {
        List<Ponto> lista = folhaPonto.getListaPonto();
        LocalTime hTrabalhada;
        int horas = 0;
        int minutos = 0;
        String msg = "\n\nHoras Trabalhadas ";
        if (lista != null) {
            for (Ponto p : lista) {
                if (dia != null) {
                    if (p.getDataPonto().equals(dia)) {
                        if (Objects.equals("Entrada", p.getTipo())) {
                            horas += lista.get(lista.indexOf(p) + 1).getHorario().getHour() - p.getHorario().getHour();
                            minutos += lista.get(lista.indexOf(p) + 1).getHorario().getMinute() - p.getHorario().getMinute();
                        }
                    }
                } else {
                    if (Objects.equals("Entrada", p.getTipo())) {
                        horas += lista.get(lista.indexOf(p) + 1).getHorario().getHour() - p.getHorario().getHour();
                        minutos += lista.get(lista.indexOf(p) + 1).getHorario().getMinute() - p.getHorario().getMinute();
                    }
                }
            }
            hTrabalhada = LocalTime.of(horas, minutos, 0);
            //agrega sufixo da mensagem
            msg = dia != null ? msg.concat("em ".concat(dia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))) : msg.concat("");
            msg = msg.concat(": ".concat(hTrabalhada.format(DateTimeFormatter.ofPattern("HH:mm"))));
            return msg.concat("\n\n");
        }
        return "Não existem registro deste funcionário";
    }

}
