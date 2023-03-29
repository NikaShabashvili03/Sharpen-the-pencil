import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    protected void paintComponent (Graphics g){
        super.paintComponents(g);
        // Menu painting
        if(Menu.isPressed() == true){
            Menu.setPencilX(Menu.getPencilX() - 50);
            if(Menu.getPencilX() <= 0){
                Menu.setPressed(false);
                Main.setStart(true);
            }
        }else{
            Menu.setPencilX(700);
        }

        // Game Painting


        Main.onRepaint(g);
        repaint();
    }


}