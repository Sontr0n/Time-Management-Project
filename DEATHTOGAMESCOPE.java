import javax.swing.JWindow;
import java.lang.Runtime;
import java.util.*;

public class DEATHTOGAMESCOPE {
    public static int fleetTimer = 0;
    public static int owTimer = 0;
    public static boolean canPlayGames = false;
    public static boolean stayOpen = true;
    public static boolean isOWOpen = isOpen("Overwatch");
    public static boolean isFleetOpen = isOpen("Fleet");


    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask tick = new TimerTask(){
            @Override
            public void run() {
                isOWOpen = isOpen("Overwatch");
                if (isOWOpen && canPlayGames) {
                    owTimer++;
                    if (owTimer >= 2400){
                        canPlayGames = false;
                        fleetTimer = 0;
                        kill("Overwatch");
                    }
                } else kill("Overwatch");

                if (canPlayGames == false) {
                    isFleetOpen = isOpen("Fleet");
                    if (isFleetOpen){
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







            timer.schedule(tick, 0, 1000);



        }







            public static boolean isOpen(String game) {return ProcessHandle.allProcesses().filter(p -> p.info().command().map(c -> c.contains(game)).orElse(false)).findFirst().isPresent();}


            public static void kill(String game) {
                ProcessHandle.allProcesses().filter(p -> p.info().command().map(c -> c.contains(game)).orElse(false)).findFirst().ifPresent(ProcessHandle::destroy);
            }

}
