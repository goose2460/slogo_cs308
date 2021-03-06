import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import Feature.Feature;
import MovementAndImageAPI.src.ImageUpdater;
import MovementAndImageAPI.src.TurtleHandler;

/**
 * Allows user to choose an image to use for the turtle.
 * @author Yoonhyung
 *
 */
public class TurtleChooserFeature extends Feature {

    /**
     * Opens a filechooser for the turtle image when the button is clicked,
     * and updates the turtle's image with the chosen image.
     * 
     * @param button Associated button
     * @param root Group where the button is located
     * @param imageUpdater ImageUpdater for the turtle
     * @param turtleHandler Turtlehandler that controls the turtle
     */
    public void openTurtleChooser (Button button, Group root,
                                   ImageUpdater imageUpdater, TurtleHandler turtleHandler) {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                FileChooser fileChooser = new FileChooser();

                FileChooser.ExtensionFilter extFilterJPG = 
                        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = 
                        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                File file = fileChooser.showOpenDialog(null);
                BufferedImage bufferedImage = null;
                try {
                    if (file != null) {
                        bufferedImage = ImageIO.read(file);
                    }
                }
                catch (IOException e) {
                    System.out.println("The selected file could not be read.");
                }
                if (bufferedImage != null) {
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                    turtleHandler.updateImage(image);
                }
            }
        });
    }
}
