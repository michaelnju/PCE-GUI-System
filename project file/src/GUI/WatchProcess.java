package GUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*The class watchprocess entends thread,
 * which is used to print the current output. In this class, there are two thread.
 * */
public class WatchProcess extends Thread {
	private Process p;
	WatchProcess(Process p_) {
		p = p_;
	}
	@Override
	public void run() {
		// Watch the process and report back when it ends
		// read from os here; maybe put into textbox
		// in gui, maybe just discard
		try {
			// Handle stdout...
			new Thread() {
			    public void run() {
			    	String currentline;
			    	BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					try {
						while (MainFrame.process_running) {
							if ((currentline = in.readLine()) != null) {
								System.out.println(currentline);
							}
						}
					} catch (IOException e) {
					} finally {
						try {
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			    }
			}.start();

			// Handle stderr...
			new Thread() {
			    public void run() {
			    	String currentline;
			    	BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					try {
						while (MainFrame.process_running) {
							if ((currentline = in.readLine()) != null) {
								System.out.println(currentline);
							}
						}
					} catch (IOException e) {
					} finally {
						try {
							in.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			    }
			}.start();

			int retCode = p.waitFor();
			System.out.println("Process exited with retcode " + retCode);
			MainFrame.keepRunning = false;
			MainFrame.process_running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
