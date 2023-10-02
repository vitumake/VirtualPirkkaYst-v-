package ohtu.control;

import javafx.application.Platform;
import ohtu.model.Pet;
import ohtu.view.PetGui;

public class PetController {
    private PetGui gui;
    private Pet pet = new Pet(0, 0);
    private Thread worker = null;
    private long workerAge = 0;

    public PetController(PetGui gui) {
        this.gui = gui;
    }

    public void setTarget(int[] target) {
        pet.setTarget(target);
        if(worker == null) {
            movePet();
        }
        if(worker != null && workerAge + 100 < System.currentTimeMillis()) {
            worker.interrupt();
            movePet();
        }
    }

    public void petStop() {
        if(worker != null) {
            worker.interrupt();
            worker = null;
        }
    }

    private void movePet() {

        worker = new Thread (()->{
            workerAge = System.currentTimeMillis();
            int step = pet.getSpeed();
            while(true) {
                int[] pos = pet.getPos();
                int[] target = pet.getTarget();
                if(pos[0] < target[0]) {
                    pos[0] += step;
                }
                if(pos[0] > target[0]) {
                    pos[0] -= step;
                }
                if(pos[1] < target[1]) {
                    pos[1] += step;
                }
                if(pos[1] > target[1]) {
                    pos[1] -= step;
                }
                pet.move(pos[0], pos[1]);
                Platform.runLater(()->{
                    gui.updatePetPos(pet.getPos());
                });
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        worker.start();
    }

    public static void main(String[] args) {
        PetGui.launch(PetGui.class);
    }
}
