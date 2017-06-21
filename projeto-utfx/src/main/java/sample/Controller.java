package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe de controle da tela.
 */
public class Controller implements Initializable {

    @FXML
    private TextArea nomeArquivos;//Campo com o nome dos arquivos carregados

    final FileChooser fileChooser = new FileChooser();//Componente buscador de arquivos

    List<Imagem> imagensComCores;

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

    }


}
