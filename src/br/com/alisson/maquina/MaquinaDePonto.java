package br.com.alisson.maquina;

import br.com.alisson.entidades.Administrador;
import java.util.Scanner;

public class MaquinaDePonto {

    private Scanner sc;
    private PontoEletronicoGeral pontoEletronico;

    public MaquinaDePonto() {
    }

    public void iniciarSistema() {
        try {
            sc = new Scanner(System.in);
            pontoEletronico = new PontoEletronicoGeral();
            pontoEletronico.menuPrincipal(sc, new Administrador());
        } catch (Exception e) {
            System.out.println("Erro ao tentar iniciar o sistema, contate o administrador");
        }
    }
    
}
