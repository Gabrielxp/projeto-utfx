package sample;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Criado por Gabriel de Paula em 20/06/2017.
 */
public class Imagem {

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


}
