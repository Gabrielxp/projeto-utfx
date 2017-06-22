package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Classe de controle da tela.
 */
public class Controller implements Initializable {

    @FXML
    private TextArea nomeArquivos;//Campo com o nome dos arquivos carregados

    @FXML
    private TextArea consultaWeka;//Campo com o nome dos arquivos carregados

    final FileChooser fileChooser = new FileChooser();//Componente buscador de arquivos

    List<Imagem> imagensComCores;

    private String[] colunas = {"amarelo", "azulEscuro", "azulClaro", "laranja", "bege", "branco", "pretoClareado"};

    //Método que inicializa filtros no fileChooser
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")

        );
    }

    //Método executado para abrir arquivos
    public void abrirArquivos() {

        List<File> list = fileChooser.showOpenMultipleDialog(null);
        List<Image> images;

        nomeArquivos.clear();

        if (list != null) {
            StringBuffer nomes = new StringBuffer();

            images = Converter.fileToImages(list, nomes);

            imagensComCores = Converter.converterFxImagetoImagem(images);

            nomeArquivos.setText(nomes.toString());

        }


/*        for (Map.Entry<Color,Integer> pair :colorCountPixels.entrySet()) {

            System.out.println("rgb(" + new DecimalFormat("0").format(pair.getKey().getRed() * 255)
                    + "," + new DecimalFormat("0").format(pair.getKey().getGreen() * 255)  +
                    "," + new DecimalFormat("0").format(pair.getKey().getBlue() * 255) +") Qt:" + pair.getValue());

        }*/


    }

    public void iniciar(){
        consultaWeka.clear();
        consultaWeka.setText(processar().toString());

    }

    public StringBuffer processar(){
        StringBuffer dados = new StringBuffer();
        dados.append("@RELATION SIMPSONS\n")
            .append("@ATTRIBUTE ").append(colunas[0]).append(" NUMERIC\n")
            .append("@ATTRIBUTE ").append(colunas[1]).append(" NUMERIC\n")
            .append("@ATTRIBUTE ").append(colunas[2]).append(" NUMERIC\n")
            .append("@ATTRIBUTE ").append(colunas[3]).append(" NUMERIC\n")
            .append("@ATTRIBUTE ").append(colunas[4]).append(" NUMERIC\n")
            .append("@ATTRIBUTE ").append(colunas[5]).append(" NUMERIC\n")
            .append("@ATTRIBUTE ").append(colunas[6]).append(" NUMERIC\n");
        dados.append("@DATA\n");
        if ( imagensComCores != null){

            for (Imagem imagem: imagensComCores) {
                Map<Color, Integer> imagens = imagem.getCorQuantidadeMap();
                for (Map.Entry<Color,Integer> pair : imagens.entrySet()) {
                    Integer vermelho =  Integer.parseInt(new DecimalFormat("0").format(pair.getKey().getRed() * 255));
                    Integer verde =  Integer.parseInt(new DecimalFormat("0").format(pair.getKey().getGreen() * 255));
                    Integer azul =  Integer.parseInt(new DecimalFormat("0").format(pair.getKey().getBlue() * 255));

                    //Preencher cor por cor no obg imagem
/*
                    if( (vermelho == 239 && verde == 189 && azul == 0)  ||
                        (vermelho == 0 && verde == 8 && azul == 132)    ||
                        (vermelho == 0 && verde == 107 && azul == 173)  ||
                        (vermelho == 247 && verde == 99 && azul == 16)  ||
                        (vermelho == 189 && verde == 173 && azul == 107)||
                        (vermelho == 255 && verde == 255 && azul == 255)||
                        (vermelho == 33 && verde == 33 && azul == 33)){
                        dados.append(pair.getValue() +", ");
                    }*/

                  /*  //AzulEscuro
                    if(vermelho == 0 && verde == 8 && azul == 132){
                        dados.append(pair.getValue() +", ");
                    }else{
                        dados.append("0, ");
                    }
*/
                    //AzulClaro
                  /*  if(vermelho == 0 && verde == 107 && azul == 173){
                        dados.append(pair.getValue() +", ");
                    }else{
                        dados.append("0, ");
                    }*/

                    //Laranja
/*                    if(vermelho == 247 && verde == 99 && azul == 16){
                        dados.append(pair.getValue() +", ");
                    }else{
                        dados.append("0, ");
                    }*/


                    //Bege
/*
                    if(vermelho == 189 && verde == 173 && azul == 107){
                        dados.append(pair.getValue() +", ");
                    }else{
                        dados.append("0, ");
                    }
*/

                    //Branco
               /*     if(vermelho == 255 && verde == 255 && azul == 255){
                        dados.append(pair.getValue() +", ");
                    }else{
                        dados.append("0, ");
                    }*/

                    //PretoClareado
  /*                  if(vermelho == 33 && verde == 33 && azul == 33){
                        dados.append(pair.getValue());
                    }else{
                        dados.append("0");
                    }*/
                    System.out.println("rgb(" + new DecimalFormat("0").format(pair.getKey().getRed() * 255)
                            + "," + new DecimalFormat("0").format(pair.getKey().getGreen() * 255) +
                            "," + new DecimalFormat("0").format(pair.getKey().getBlue() * 255) + ") Qt:" + pair.getValue());
                }
                dados.append("\n");

            }


        }


        return dados;
    }


}
