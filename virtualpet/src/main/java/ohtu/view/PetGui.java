package ohtu.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ohtu.control.PetController;

public class PetGui extends Application {
    
    private Canvas canvas;
    private GraphicsContext gc;
    private PetController controller;
    Image petImage = new Image("pirkka.PNG", 100, 100, false, false);

    public void init() {
        controller = new PetController(this);
    }
    
    public void start(Stage window) {
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        window.setTitle("Virtual PirkkaYstävä");
        canvas = new Canvas(700, 700);
        gc = canvas.getGraphicsContext2D();

        // Mouse move event handler
        canvas.setOnMouseMoved((event)->{
            controller.setTarget(new int[]{(int) event.getX(), (int) event.getY()});
        });

        canvas.setOnMouseExited((event)->{
            controller.petStop();
        });

        root.getChildren().addAll(canvas);
        window.setScene(scene);
        window.show();

    }

    public void updatePetPos(int[] pos) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(petImage, pos[0], pos[1]);
    }
}
