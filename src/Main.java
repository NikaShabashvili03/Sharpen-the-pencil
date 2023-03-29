import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {
    private int width = 1000;
    private int height = 500;
    public static Image pencil;
    public static Image sharpener;
    private String title = "Speed Runner";
    public static Main game;

    public static boolean isStart() {
        return Start;
    }

    public static void setStart(boolean start) {
        Start = start;
    }

    public static boolean Start = false;

    public static void main(String[] args) throws IOException {
        game = new Main();
        game.setSize(game.width,game.height);
        game.setTitle(game.title);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocation(200,150);
        GamePanel gameP = new GamePanel();
        game.add(gameP);

        game.setVisible(true);

        pencil = ImageIO.read(Main.class.getResourceAsStream("/Images/Pencil.png"));
        sharpener = ImageIO.read(Main.class.getResourceAsStream("/Images/sharpener.png"));
    }
    public static void onRepaint(Graphics g){
        if(!isStart()){
            Menu menu = new Menu(g);
        }else{
            Game game = new Game(g);
        }
    }
}
