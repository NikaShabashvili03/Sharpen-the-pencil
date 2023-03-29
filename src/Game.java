import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.io.IOException;
import java.util.*;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.awt.AlphaComposite.*;

public class Game extends Main{


    public static int pencilX = 130;
    public static boolean endWindow = false;

    private static int Score = 0;


    private static int getArrowSide() {
        return ArrowSide;
    }

    private static void setArrowSide(int arrowSide) {
        ArrowSide = arrowSide;
    }

    private static int ArrowSide = 0;
    private static Image background;
    private static Image resetGame;
    private static Image Arrow;

    public static int getStoped() {
        return stoped;
    }

    public static void setStopped(int stoped) {
        Game.stoped = stoped;
    }

    public static int stoped = 0;

    private static boolean press = false;
    Game(Graphics g){
        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(endWindow == true){
                    if(e.getKeyCode() == KeyEvent.VK_SPACE){
                        Main.setStart(false);
                        endWindow = false;
                        stopWatch.seconds = 0;
                        stopWatch.minutes = 0;
                        stopWatch.elapsedTime = 0;
                        stopWatch.setSeconds_string(stopWatch.seconds_string = String.format("%02d", stopWatch.seconds));
                        stopWatch.setMinutes_string(stopWatch.minutes_string = String.format("%02d", stopWatch.minutes));
                        ArrowSide = 0;
                        setStopped(0);
                        Score = 0;
                    }
                }
                if(press == true){
                    if(stopWatch.minutes != 5){
                        int min = 0;
                        int max = 2;
                        if(getArrowSide() == 0){
                            if (e.getKeyCode() == KeyEvent.VK_UP) {
                                setStopped(1);
                                setArrowSide((int)Math.floor(Math.random() * (max - min + 1) + min));
                                System.out.println(Game.ArrowSide);
                                pencilX -= 25;
                                press = false;
                                System.out.println(pencilX);
                                System.out.println(press);
                            };
                        }
                        if(getArrowSide() == 1){
                            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                                setStopped(1);
                                setArrowSide((int)Math.floor(Math.random() * (max - min + 1) + min));
                                System.out.println(Game.ArrowSide);
                                press = false;
                                pencilX -= 25;
                                System.out.println(pencilX);
                                System.out.println(press);
                            };
                        }
                        if(getArrowSide() == 2){
                            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                                setStopped(1);
                                setArrowSide((int)Math.floor(Math.random() * (max - min + 1) + min));
                                System.out.println(Game.ArrowSide);
                                press = false;
                                pencilX -= 25;
                                System.out.println(pencilX);
                                System.out.println(press);
                            }
                        }
                    }
                }
            };
            public void keyReleased(KeyEvent e){
                if(press == false){
                    if (e.getKeyCode() == KeyEvent.VK_UP) press = true;
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) press = true;
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) press = true ;
                }
            }
        });
        try {
            background = ImageIO.read(Main.class.getResourceAsStream("/Images/gbackground.png"));
            resetGame = ImageIO.read(Main.class.getResourceAsStream("/Images/resetGame.png"));
            if(ArrowSide == 0){
                Arrow = ImageIO.read(Main.class.getResourceAsStream("/Images/Up.png"));
            }
            if(ArrowSide == 1){
                Arrow = ImageIO.read(Main.class.getResourceAsStream("/Images/Left.png"));
            }
            if(ArrowSide == 2){
                Arrow = ImageIO.read(Main.class.getResourceAsStream("/Images/Right.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(background, 0, 0, 1000, 472, null);
        if(Game.getStoped() == 1 && stopWatch.minutes != 5){
            stopWatch.start();
        }
        if(pencilX <= 15){
            pencilX = 130;
            Score+=1;
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString( stopWatch.getMinutes_string() + ":" + stopWatch.getSeconds_string(),50,70);


        if(endWindow == false){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString( "Score: " + Score,850,70);
        }

        // Game
        g.drawImage(Arrow,35, 275,50,50,null);


        // Pencil
        g.drawImage(pencil, pencilX, 350, 300, 50, null);
        g.drawImage(sharpener, 10, 335, 100, 80, null);


        //
        if(endWindow == true){
            Color color = new Color(0, 0, 0, 0.5f); //Red
            g.setColor(color);
            g.fillRect(0,0,1000,500);
            g.drawImage(resetGame,0,0,1000,500,null);
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString( "Score: " + Score,430 ,250);
        }
    }

}
















class stopWatch {
    static int elapsedTime = 0;
    static int seconds = 0;
    static int minutes = 0;


    public static String seconds_string = String.format("%02d", seconds);

    public static String minutes_string = String.format("%02d", minutes);

    public static String getSeconds_string() {
        return seconds_string;
    }

    public static void setSeconds_string(String seconds_string) {
        stopWatch.seconds_string = seconds_string;
    }

    public static String getMinutes_string() {
        return minutes_string;
    }

    public static void setMinutes_string(String minutes_string) {
        stopWatch.minutes_string = minutes_string;
    }


    static Timer timer = new Timer(25, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime=elapsedTime+1000;
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            setSeconds_string(seconds_string = String.format("%02d", seconds));
            setMinutes_string(minutes_string = String.format("%02d", minutes));
            if(minutes == 5){
                timer.stop();
                Game.setStopped(3);
                Game.pencilX = 130;
                Game.endWindow = true;
            }
        }
    });
    public static void start(){
        timer.start();
    }
}

