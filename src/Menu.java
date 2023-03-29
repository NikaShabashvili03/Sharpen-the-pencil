import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Menu extends Main {
    private static Image background;

    public static boolean isPressed() {
        return Pressed;
    }


    public static void setPressed(boolean pressed) {
        Pressed = pressed;
    }

    public static int getPencilX() {
        return pencilX;
    }

    public static void setPencilX(int PencilX) {
        pencilX = PencilX;
    }

    public static boolean Pressed;
    public static int pencilX = 700;
    Menu(Graphics g){
        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(getPencilX() > 0){
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) setPressed(true);
                }
            }
            public void keyReleased(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_SPACE) setPressed(false);
            }
        });
        try {
            background = ImageIO.read(Main.class.getResourceAsStream("/Images/background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(background, 0, 0, 1000, 472, null);

        // Pencil and sharpener
        g.drawImage(pencil, getPencilX(), 200, 300, 50, null);
        g.drawImage(sharpener, 10, 185, 100, 80, null);
    }
}

