import javax.swing.JFrame;

public class Runner {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Game");
    Screen screen = new Screen();
    frame.add(screen);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    screen.animate();
  }
}