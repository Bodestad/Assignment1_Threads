import java.time.Clock;

/**
 * Created by gustavbodestad on 2016-05-16.
 */
public class ClockThread implements Runnable {

    private Controller controller;

    public ClockThread(Controller inController) {
        controller = inController;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            controller.displayClock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
