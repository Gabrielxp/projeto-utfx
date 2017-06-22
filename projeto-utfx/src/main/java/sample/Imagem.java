package sample;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Criado por Gabriel de Paula em 20/06/2017.
 */
public class Imagem {

    private Integer qtAmarelo;
    private Integer qtLaranja;
    private Integer qtAzulClaro;
    private Integer qtAzulEscuro;
    private Integer qtPreto;
    private Integer qtBege;
    private Integer qtBranco;



    private String caminho;

    private Map<Color, Integer> corQuantidadeMap;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Map<Color, Integer> getCorQuantidadeMap() {
        return corQuantidadeMap;
    }

    public void setCorQuantidadeMap(Map<Color, Integer> corQuantidadeMap) {
        this.corQuantidadeMap = corQuantidadeMap;
    }

    public void incrementaQuantidadeDeCor(Color color){

        if(corQuantidadeMap == null) {
            corQuantidadeMap = new HashMap<Color, Integer>();
        }

        if(corQuantidadeMap.containsKey(color)){
            corQuantidadeMap.put(color, corQuantidadeMap.get(color) + 1);
        }else{
            corQuantidadeMap.put(color, 1);
        }

    }

    public Integer getQtAmarelo() {
        return qtAmarelo;
    }

    public void setQtAmarelo(Integer qtAmarelo) {
        this.qtAmarelo = qtAmarelo;
    }

    public Integer getQtLaranja() {
        return qtLaranja;
    }

    public void setQtLaranja(Integer qtLaranja) {
        this.qtLaranja = qtLaranja;
    }

    public Integer getQtAzulClaro() {
        return qtAzulClaro;
    }

    public void setQtAzulClaro(Integer qtAzulClaro) {
        this.qtAzulClaro = qtAzulClaro;
    }

    public Integer getQtAzulEscuro() {
        return qtAzulEscuro;
    }

    public void setQtAzulEscuro(Integer qtAzulEscuro) {
        this.qtAzulEscuro = qtAzulEscuro;
    }

    public Integer getQtPreto() {
        return qtPreto;
    }

    public void setQtPreto(Integer qtPreto) {
        this.qtPreto = qtPreto;
    }

    public Integer getQtBege() {
        return qtBege;
    }

    public void setQtBege(Integer qtBege) {
        this.qtBege = qtBege;
    }

    public Integer getQtBranco() {
        return qtBranco;
    }

    public void setQtBranco(Integer qtBranco) {
        this.qtBranco = qtBranco;
    }
}
