package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    final FileChooser fileChooser = new FileChooser();

    @FXML
    private TextArea nomeArquivos;

    public void abrirArquivos(){
        nomeArquivos.clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        List<File> list =
                fileChooser.showOpenMultipleDialog(null);
        List<Image> images;
        if (list != null) {
            StringBuffer nomes = new StringBuffer();
            for (File file : list) {

                nomes.append(file.getAbsolutePath()).append("\n");

            }
            nomeArquivos.setText(nomes.toString());
        }


                Image image = new Image( list.get(0).toURI().toString());


                PixelReader pixelReader = image.getPixelReader();

                int width = (int)image.getWidth();
                int height = (int)image.getHeight();

                //Copy from source to destination pixel by pixel
                WritableImage writableImage = new WritableImage(width, height);
                PixelWriter pixelWriter = writableImage.getPixelWriter();
                Map<Color, Integer> colorCountPixels = new HashMap<Color, Integer>();
                for (int y = 0; y < height; y++){
                    for (int x = 0; x < width; x++){

                        Color color = pixelReader.getColor(x, y);

                        if(colorCountPixels.containsKey(color)){
                            colorCountPixels.put(color, colorCountPixels.get(color) + 1);
                        }else{
                            colorCountPixels.put(color, 0);
                        }

                       // pixelWriter.setColor(x, y, color);
                    }
                }

        for (Map.Entry<Color,Integer> pair :colorCountPixels.entrySet()) {

            System.out.println("rgb(" + new DecimalFormat("0").format(pair.getKey().getRed() * 255)
                    + "," + new DecimalFormat("0").format(pair.getKey().getGreen() * 255)  +
                    "," + new DecimalFormat("0").format(pair.getKey().getBlue() * 255) +") Qt:" + pair.getValue());

        }






/*        Image image = new Image("");
        image.getPixelReader().getPixels();*/
    }

}
