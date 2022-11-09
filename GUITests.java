import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class GUITests
{
    static JLabel l = new JLabel();
    static JPanel p = new JPanel();
    static JFrame f=new JFrame("Transparent JTextField");
    static int time = 0;
    static String lab = "";



public static void main(String[]args)
{
   Timer t = new Timer("Timer");
   TimerTask increment = new TimerTask() {
        @Override
        public void run() {
            lab = String.format("%02d:%02d:%02d", (int)(time/3600), (int)(time%3600/60), (time%3600%60));
            l.setText(lab);
            f.repaint();
            f.setVisible(true);
            time++;
        }
    };

    l.setFont(new Font("Arial", Font.PLAIN, 48));
    l.setForeground(Color.green);
    l.setOpaque(false);
    p.add(l);
    f.add(p);
    f.setSize(300,50);
    f.setUndecorated(true);
    f.setAlwaysOnTop(true);
    p.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
    f.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
    f.setLocation(1650, 5);

    t.schedule(increment, 0, 1000);


}
}

