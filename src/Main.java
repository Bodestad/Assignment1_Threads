import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Main
{
	public static void main(String[] args){
		GUIFrame test = new GUIFrame(new Controller());
		//GUIMutex test = new GUIMutex();
		//GUISemaphore test = new GUISemaphore();
		//GUIMonitor test = new GUIMonitor();
		//GUIChat test = new GUIChat();
		test.Start();

	}


}
