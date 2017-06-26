package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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

    @FXML
    private CheckBox treinoCheckBox;

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


    }

    /**
     * Metodo para iniciar o processamento.
     */
    public void iniciar(){
        consultaWeka.clear();
        consultaWeka.setText(processar().toString());

    }

    /**
     * Método de processamento e montagem de relatorio pronto para o WEKA.
     * @return
     */
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

        dados.append("@ATTRIBUTE ").append("personagem").append(" NUMERIC\n");

        dados.append("@DATA\n");

        if ( imagensComCores != null){
            //For que pega as cores e seta no objeto de imagem para o relatorio
            for (int i = 0; i<imagensComCores.size(); i++) {

                Map<Color, Integer> imagens = imagensComCores.get(i).getCorQuantidadeMap();
                for (Map.Entry<Color,Integer> pair : imagens.entrySet()) {
                    Integer vermelho =  Integer.parseInt(new DecimalFormat("0").format(pair.getKey().getRed() * 255));
                    Integer verde =  Integer.parseInt(new DecimalFormat("0").format(pair.getKey().getGreen() * 255));
                    Integer azul =  Integer.parseInt(new DecimalFormat("0").format(pair.getKey().getBlue() * 255));

                    //Amarelo
                    if(vermelho == 239 && verde == 189 && azul == 0){
                        imagensComCores.get(i).setQtAmarelo(pair.getValue());
                    }


                    //AzulEscuro
                    if(vermelho == 0 && verde == 8 && azul == 132){
                        imagensComCores.get(i).setQtAzulEscuro(pair.getValue());
                    }

                    //AzulClaro
                    if(vermelho == 0 && verde == 107 && azul == 173){
                        imagensComCores.get(i).setQtAzulClaro(pair.getValue());
                    }

                    //Laranja
                    if(vermelho == 247 && verde == 99 && azul == 16){
                        imagensComCores.get(i).setQtLaranja(pair.getValue());
                    }


                    //Bege
                    if(vermelho == 189 && verde == 173 && azul == 107){
                        imagensComCores.get(i).setQtBege(pair.getValue());
                    }

                    //Branco
                    if(vermelho == 255 && verde == 255 && azul == 255){
                        imagensComCores.get(i).setQtBranco(pair.getValue());
                    }

                    //PretoClareado
                    if(vermelho == 33 && verde == 33 && azul == 33){
                        imagensComCores.get(i).setQtPreto(pair.getValue());
                    }

/*                    System.out.println("rgb(" + new DecimalFormat("0").format(pair.getKey().getRed() * 255)
                            + "," + new DecimalFormat("0").format(pair.getKey().getGreen() * 255) +
                            "," + new DecimalFormat("0").format(pair.getKey().getBlue() * 255) + ") Qt:" + pair.getValue());*/
                }

            }
            //Conclui a montagem do relatorio
            for (Imagem imagem: imagensComCores) {
                dados.append(imagem.getQtAmarelo()).append(", ")
                     .append(imagem.getQtAzulEscuro()).append(", ")
                     .append(imagem.getQtAzulClaro()).append(", ")
                     .append(imagem.getQtLaranja()).append(", ")
                     .append(imagem.getQtBege()).append(", ")
                     .append(imagem.getQtBranco()).append(", ")
                     .append(imagem.getQtPreto());
                    if(treinoCheckBox.isSelected()){
                        if(imagem.getNome().contains("bart")){
                            dados.append(",0");
                        }else{
                            dados.append(",1");
                        }
                    }else{
                        dados.append(", ?");
                    }
                    dados.append("\n");
            }


        }


        return dados;
    }


}
