package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Criado por Gabriel de Paula em 20/06/2017.
 */
public class Converter {

    public static List<Image> fileToImages(List<File> files, StringBuffer descricaoBuffer){

        List<Image> imagens = new ArrayList<Image>();
        for (File file : files) {

            imagens.add(new Image(file.toURI().toString()));
            descricaoBuffer.append(file.getAbsolutePath()).append("\n");

        }
        return imagens;
    }

    public static List<Imagem> converterFxImagetoImagem(List<Image> imagensParaProcessar){
        List<Imagem> imagensComCoresExtraidas = new ArrayList<Imagem>();

        for (Image image: imagensParaProcessar) {

            PixelReader pixelReader = image.getPixelReader();

            int width = (int)image.getWidth();
            int height = (int)image.getHeight();

            //Copy from source to destination pixel by pixel
            WritableImage writableImage = new WritableImage(width, height);
            PixelWriter pixelWriter = writableImage.getPixelWriter();

            Imagem imagemProcessada = new Imagem();
            for (int y = 0; y < height; y++){
                for (int x = 0; x < width; x++){
                    Color color = pixelReader.getColor(x, y);
                    imagemProcessada.incrementaQuantidadeDeCor(color);
                }
            }

            imagensComCoresExtraidas.add(imagemProcessada);

        }
        return imagensComCoresExtraidas;
    }

}
