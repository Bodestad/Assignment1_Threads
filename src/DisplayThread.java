import java.util.Observable;

/**
 * Created by gustavbodestad on 2016-05-16.
 */
public class DisplayThread implements Runnable {

    private Controller controller;

    public DisplayThread(Controller inController) {
        controller = inController;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            controller.changeDisplay();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
