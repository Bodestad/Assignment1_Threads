import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1
 */
public class GUIFrame implements ActionListener
{
	private JFrame frame;
	private JButton btnOpen;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnDisplay;
	private JButton btnDStop;
	private JButton btnClock;
	private JButton btnTStop;
	private JLabel lblPlaying;
	private JLabel lblPlayURL;
	private JPanel pnlMove;
	private JPanel pnlRotate;
	private JPanel pnlClock;
	private JLabel lblName;
	private JLabel lblTime;
	private Controller controller;
	private Random rand;

	/**
	 * Constructor.
	 * @param inController
     */
	public GUIFrame(Controller inController) {
		controller = inController;
		sendRefToController();
		rand = new Random();
		controller.setFrame(this);

	}

	/**
	 * Start application.
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}

	/**
	 * Constructing UI.
	 */
	private void InitializeGUI()
	{
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);

		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		pnlSound.add(btnOpen);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		pnlSound.add(btnPlay);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		pnlSound.add(btnStop);
		
		lblPlaying = new JLabel("Now Playing",JLabel.CENTER);
		lblPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		lblPlaying.setBounds(128, 16, 140, 30);
		pnlSound.add(lblPlaying);
		
		lblPlayURL = new JLabel("Music URL");
		lblPlayURL.setBounds(10, 44, 400, 13);
		pnlSound.add(lblPlayURL);

		frame.add(pnlSound);

		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);
		

		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);
		
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);
		
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		pnlMove.setLayout(null);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		lblName = new JLabel("Display Thread");
		lblName.setBounds(50, 50, 50, 50);
		pnlMove.add(lblName);

		pnlDisplay.add(pnlMove);
		frame.add(pnlDisplay);
		

		pnlClock = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Clock Thread");
		pnlClock.setBorder(b3);
		pnlClock.setBounds(240, 118, 222, 269);
		pnlClock.setLayout(null);

		btnClock = new JButton("Start Clock");
		btnClock.setBounds(10, 226, 121, 23);
		pnlClock.add(btnClock);
		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlClock.add(btnTStop);

		pnlRotate = new JPanel();
		lblTime = new JLabel();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlRotate.add(lblTime);
		pnlClock.add(pnlRotate);

		frame.add(pnlClock);

		/**
		 * Binds buttons to listener.
		 */
		btnDisplay.addActionListener(this);
		btnPlay.addActionListener(this);
		btnOpen.addActionListener(this);
		btnClock.addActionListener(this);
		btnTStop.addActionListener(this);
		btnStop.addActionListener(this);
		btnDStop.addActionListener(this);
	}

	/**
	 * Sends a reference of the UI to the controller.
	 */
	public void sendRefToController() {
		controller.setFrame(this);
	}

	/**
	 * Change position of the name in the Displaypanel
	 */
	public void changeNamePosition() {
		int posOne, posTwo;

		posOne = rand.nextInt(pnlMove.getWidth() - 20);
		posTwo = rand.nextInt(pnlMove.getHeight() - 20);

		lblName.setLocation(posOne, posTwo);
	}

	/**
	 * Change the time in the Clockpanel.
	 * @param inTime
     */
	public void displayTime(String inTime) {
		lblTime.setText(inTime);
	}

	/**
	 * Listener which handles all the buttons.
	 * @param e
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDisplay) {
			controller.startDisplayThread();
		}

		if (e.getSource() == btnPlay) {
			controller.startMusic();
		}

		if (e.getSource() == btnOpen) {
			lblPlayURL.setText(controller.openFile());
		}

		if (e.getSource() == btnClock) {
			controller.startClockThread();
		}

		if (e.getSource() == btnTStop) {
			controller.stopClockThread();
		}

		if (e.getSource() == btnStop) {
			controller.stopMusic();
		}

		if (e.getSource() == btnDStop) {
			controller.stopDisplayThread();
		}
	}
}
