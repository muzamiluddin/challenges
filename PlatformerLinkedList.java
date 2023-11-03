import java.util.LinkedList;

public class Platformer {
    private LinkedList<Integer> platform = new LinkedList<>();
    private int currentPosition;

    public Platformer(int n, int position) {
        for (int index = 0; index < n; index++) {
            this.platform.add(index);
        }
        this.currentPosition = this.platform.get(position);
    }

    public void jumpLeft() {
        int oldIndex = this.platform.indexOf(currentPosition);
        int newIndex = oldIndex - 2;
        this.currentPosition = this.platform.get(newIndex);
        this.platform.remove(oldIndex);
    }

    public void jumpRight() {
        int oldIndex = this.platform.indexOf(currentPosition);
        int newIndex = oldIndex + 2;
        this.currentPosition = this.platform.get(newIndex);
        this.platform.remove(oldIndex);
    }

    public int position() {
        return this.currentPosition;
    }

    public static void main(String[] args) {
        Platformer platformer = new Platformer(6, 3);
        System.out.println(platformer.position()); // should print 3

        platformer.jumpLeft();
        System.out.println(platformer.position()); // should print 1

        platformer.jumpRight();
        System.out.println(platformer.position()); // should print 4
    }
}
