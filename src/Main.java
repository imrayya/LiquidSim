
import sim.GlobalSetting;
import sim.Simulation;
import ui.Frame2D;
import ui.NoModeSelectedException;

/**
 * Runs the rest of the project
 *
 * @author Kareem Horstink
 * @version 0.1
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        GlobalSetting.setCalculationMode(GlobalSetting.PLANER);
        GlobalSetting.setParticalSize(5);
        try {
            Frame2D frame = new Frame2D(sim);
        } catch (NoModeSelectedException ex) {
            System.out.println(ex);
        }
    }
    
}
