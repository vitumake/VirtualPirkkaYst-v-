package ohtu.model;

public class Pet {
    int[] pos = new int[2];
    int[] target = new int[2];
    int petSpeed = 5;

    public Pet(int x, int y) {
        pos[0] = x;
        pos[1] = y;
    }

    public int[] getPos() {
        return pos;
    }

    public int[] getTarget() {
        return target;
    }

    public void setTarget(int[] target) {
        this.target = target;
    }

    public void move(int x, int y) {
        pos[0] = x;
        pos[1] = y;
    }

    public int getSpeed() {
        return petSpeed;
    }
}
