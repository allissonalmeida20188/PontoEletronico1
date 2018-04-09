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
        LocalTime horas = null;
        String msg = "Horas Trabalhadas ";
        if (lista != null) {
            lista.forEach(p -> {
                if (dia != null) {
                    if (p.getDataPonto().equals(dia)) {
                        horas.plusHours(p.getHorario().getHour());
                        horas.plusMinutes(p.getHorario().getMinute());
                    }
                } else {
                    horas.plusHours(p.getHorario().getHour());
                    horas.plusMinutes(p.getHorario().getMinute());
                }
            });
            //agrega sufixo da mensagem
            msg = dia != null ? msg.concat("em ".concat(dia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))) : msg.concat("");
            msg = msg.concat(": ".concat(horas.format(DateTimeFormatter.ofPattern("HH:mm"))));
            return msg;
        }
        return "Não existem registro deste funcionário";
    }

}
