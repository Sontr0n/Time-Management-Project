import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimeManager {
    public static int fleetTimer = 0;
    public static int owTimer = 0;
    public static boolean canPlayGames = false;
    public static boolean stayOpen = true;
    public static boolean isOWOpen = isOpen("Overwatch");
    public static boolean isFleetOpen = isOpen("Fleet");
    static JLabel l = new JLabel();
    static JPanel p = new JPanel();
    static JFrame f=new JFrame("Transparent JTextField");
    static String lab = "";


    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask tick = new TimerTask(){
            @Override
            public void run() {
                isOWOpen = isOpen("Overwatch");
                if (isOWOpen && canPlayGames) {
                    lab = String.format("%02d:%02d:%02d", (int)(owTimer/3600), (int)(owTimer%3600/60), (owTimer%3600%60));
                    l.setText(lab);
                    l.setForeground(Color.green);
                    f.repaint();
                    f.setVisible(true);
                    owTimer++;
                    if (owTimer >= 2400){
                        canPlayGames = false;
                        fleetTimer = 0;
                        kill("Overwatch");
                    }
                } 

                if (canPlayGames == false) {
                    isFleetOpen = isOpen("Fleet");
                    kill("Overwatch");
                    if (isFleetOpen){
                        lab = String.format("%02d:%02d:%02d", (int)(fleetTimer/3600), (int)(fleetTimer%3600/60), (fleetTimer%3600%60));
                        l.setText(lab);
                        l.setForeground(Color.pink);
                        f.repaint();
                        f.setVisible(true);
                        fleetTimer++;

                        if (fleetTimer >= 7200) {
                            canPlayGames = true;
                            owTimer = 0;
                        } else if (fleetTimer < 7200) {
                            canPlayGames = false;

                        }

                    }
                }
            }
            };


            //set up the timer window
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


            //run the loop
            timer.schedule(tick, 0, 1000);



        }







            public static boolean isOpen(String game) {return ProcessHandle.allProcesses().filter(p -> p.info().command().map(c -> c.contains(game)).orElse(false)).findFirst().isPresent();}


            public static void kill(String game) {
                ProcessHandle.allProcesses().filter(p -> p.info().command().map(c -> c.contains(game)).orElse(false)).findFirst().ifPresent(ProcessHandle::destroy);
            }

}

