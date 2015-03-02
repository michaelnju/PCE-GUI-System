package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import org.jfree.data.time.Second;
import chart.RealtimeChart;
import chart.UpdateChart;
//---This java file is used to run the main gui where you can see the result and do operations.
public class MainFrame extends JPanel implements TreeSelectionListener {
	private static final long serialVersionUID = 1L;
	private static final boolean useProcess = !System.getProperty("os.name").startsWith("Windows"); 
//---following are some objects that create the tree list ,tree node,and menu.
	static JTree jtree;
	private static JFrame frame;
	private static JMenuBar jmenubar;
	private static JMenu jMenu1;
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode(
			"Config Files");
	public static DefaultMutableTreeNode child1;
	public static DefaultMutableTreeNode child2;
	public static DefaultMutableTreeNode child3;
	private static WatchProcess watch_process;
	public static String testname;
	public static String testtype;
	public static RealtimeChart throughputChart;
	public static UpdateChart updateChart;
	public static JLabel lblName;
	public static JLabel lblnum;
	public static JSplitPane splitPane;
	private static Process p;
	public static String csv_filename; //file data is read from
	public static boolean process_running = false; // this is used to make sure that only one thread can be running
	public static ConcurrentLinkedQueue<DataPoint> points;
	public static boolean keepRunning = false;  //this is used to control the thread
	/*
	 * This function “processUpdatesRT” is used to update the RealtimeChart,the x-axis is 
	 * time and the y-axis is the data
	 * */
	private static final Runnable processUpdatesRT = new Runnable() {
		public void run() {
			DataPoint p = points.peek();
			while (p != null) {
				points.remove();
				TimeZone tz = TimeZone.getTimeZone("GMT");
				Calendar calendar = Calendar.getInstance(tz);
				calendar.setTimeInMillis(p.x);
				Second xdate = new Second(calendar.getTime());
				RealtimeChart.timeSeries.addOrUpdate(xdate, p.y);
				if(p.rulenum!=0)
				{
					lblnum.setText(String.valueOf(p.rulenum));
				}
				p = points.peek();
				
			}
		}
	};
	/*The function “processUpdatesUP” is used to update the Updatechart, 
	 * as the x-data is not the time, 
	 * so it is easier than the realtime chat.
	 * */
	private static final Runnable processUpdatesUP = new Runnable() {
		public void run() {
			DataPoint p = points.peek();
			while (p != null) {
				points.remove();
				UpdateChart.linechartDataSeries.add(p.x, p.y);
				p = points.peek();
			}
		}
	};
	/*This function “ReadTask” is used to read the data in the output file one line
	 *  each time. and then push the data in the queue “p” .”keepRunning” is used 
	 * to control this reading process.if the user click the “Stop” menu in the 
	 * frame,then stopped.
	 * */
	private static final Runnable ReadTask = new Runnable() {
		public void run() {
			points = new ConcurrentLinkedQueue<DataPoint>(); 
			keepRunning = true;
			// wait for output file to exist
			System.out.println("Opening file " + csv_filename);
			File file = new File(csv_filename);
			while (keepRunning) {
				if (file.exists()) {
					break;
				} // exit while loop and go on to open the file
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file));
				System.out.println("Reading file " + csv_filename);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
				return;
			}
			// read from file
			while (keepRunning) {
				String currentline = null;
				try {
					if ((currentline = reader.readLine()) != null) {
						System.out.println("Read: " + currentline);
						String temp[] = currentline.split(",");
						DataPoint p = new DataPoint();
						p.x = Long.valueOf(temp[0]);
						p.y = Double.valueOf(temp[1]);
						if (MainFrame.testtype.equals("Order") && temp.length == 3) {
							p.rulenum = Integer.valueOf(temp[2]);
						}
						boolean wasEmpty = points.isEmpty();
						points.add(p);
						if (wasEmpty) {
							if ("Update".equals(MainFrame.testtype)) {
								SwingUtilities.invokeLater(processUpdatesUP);
							} else {
								SwingUtilities.invokeLater(processUpdatesRT);
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
	/*The onClickTree function is a response to the 
	 * click on the file tree node.
	 * */
	private static final MouseAdapter onClickTree = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() != 2)
				return; // ignore everything but double-clicks
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree
					.getLastSelectedPathComponent();
			if (!node.isLeaf())
				return;
			File fileArr[] = { new File("output/Throughput"),
					new File("output/Order"), new File("output/Update") };
			for (int i = 0; i < 3; i++) 		
				if (!fileArr[i].exists()) 
					fileArr[i].mkdirs();
			if (process_running) {
				JOptionPane
				.showMessageDialog(
						null,
						"Another test is running, stop it to start a new one",
						null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int i = JOptionPane.showConfirmDialog(null,
					"Do you want to run the selected test?", null,
					JOptionPane.YES_NO_OPTION);
			if (i != JOptionPane.YES_OPTION)
				return; // do nothing if they don't choose yes
			Object nodeInfo = node.getUserObject();
			leaf_node leaf = (leaf_node) nodeInfo;
			testname = leaf.nameused; 
			testtype = leaf.type_style;
			lblName.setVisible(false);
			lblnum.setVisible(false);
			keepRunning = true;
			//compute output filename
			Date now = new Date();
			SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			csv_filename = "output/" + MainFrame.testtype + "/"
					+ MainFrame.testname + "-" + dateFormate.format(now) + ".csv";
			if (!testtype.equals("Update")) {
				if (!splitPane.getBottomComponent().equals(throughputChart))
					splitPane.setBottomComponent(throughputChart);
				if (testtype.equals("Order")) {
					lblName.setVisible(true);
					lblnum.setVisible(true);
				}
				RealtimeChart.timeSeries.clear();
			} else {
				if (!splitPane.getBottomComponent().equals(updateChart)) {
					splitPane.setBottomComponent(updateChart);
				}
				UpdateChart.linechartDataSeries.clear();
			}// /用于控制读数据
			if (useProcess) {
				String commandline = "./run_test.sh " + leaf.nameused + " "
						+ leaf.type_style;
				System.out.println("Running: " + commandline);
				try {
					p = new ProcessBuilder("./run_test.sh", testname,
							leaf.type_style).start();
					process_running = true;
					watch_process = new WatchProcess(p);
					watch_process.start();
					//Start read task
					new Thread(ReadTask).start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				//instead of running process, just start a writer
				new ThreadWrite().start();
				process_running = true;
				//Start read task
				new Thread(ReadTask).start();
			}
		}
	};
	/*this is the main function to load the frame.
	 *  You can see a tree , a chart and a menubar 
	 *  splitted by the splitpane. 
	 * */
	private MainFrame() { 
		super(new GridLayout(1, 0));
		createNodes(root);
		jtree = new JTree(root);
		jtree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtree.addMouseListener(onClickTree);
		JScrollPane treeView = new JScrollPane(jtree);
		throughputChart = new RealtimeChart("Time", "", "Throughput (B/s)");
		updateChart = new UpdateChart("Rule ID", "Update Time(ms)");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(throughputChart);
		Dimension minimumSize = new Dimension(200, 200);
		throughputChart.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(200);
		splitPane.setPreferredSize(new java.awt.Dimension(500, 279));
		add(splitPane);
	}
	/*this is a class ”leaf_node” defined, you can see three data members.
	 * */
	public static class leaf_node {
		public String test_name;
		public String type_style;
		public String nameused;
		public leaf_node(String tname, String name_used, String typestyle) {
			test_name = tname;
			type_style = typestyle;
			nameused = name_used;
		}
		public String toString() {
			return test_name;
		}
	}
	/*this function createNodes is used to display the config files in the 
	input in form of a tree node*/
	private void createNodes(DefaultMutableTreeNode top) {
		child1 = new DefaultMutableTreeNode("Throughput Configurations");
		top.add(child1);
		child2 = new DefaultMutableTreeNode("Update Configurations");
		top.add(child2);
		child3 = new DefaultMutableTreeNode("Order Configurations");
		top.add(child3);
		File fileArr[] = { new File("input/Throughput"),
				new File("input/Order"), new File("input/Update") };
		for (int i = 0; i < 3; i++) {			
			if (!fileArr[i].exists()) {
				fileArr[i].mkdirs();
			}
			String leaffile[] = fileArr[i].list();
			String temp0[] = {};
			String temp1[] = {};
			for (String leafname : leaffile) {
				// parses filename as <testname>-<testtype>.config 
				temp0 = leafname.split("\\.");
				if (temp0.length < 2)
					continue; // no . in filename, not a config file
				if (!temp0[temp0.length - 1].equals("config"))
					continue; // extension is not .config; skip
				temp1 = temp0[0].split("-");
				if (temp1.length < 2)
					continue; // no - in filename, not a config file
				DefaultMutableTreeNode leaf_exist = new DefaultMutableTreeNode(
						new leaf_node(leafname, temp1[0], temp1[1]));
				if (i == 0)
					child1.add(leaf_exist);
				else if (i == 1)
					child3.add(leaf_exist);

				else
					child2.add(leaf_exist);
			}
		}
	}
	/*
	 * this is used to create the menu bar and define the actions. 
	 * */
	public static void menu_create() {
		jmenubar = new JMenuBar();
		jmenubar.setBackground(new java.awt.Color(255, 128, 64));
		{
			//“lblnum” view the rule number when the showing data belongs to Order. 
			lblName = new JLabel("               		 Currently testing rule:   ");
			JLabel lblBlankSpace = new JLabel(
					"                                                        ");
			lblnum = new JLabel("NULL");
			jMenu1 = new JMenu();
			jmenubar.add(jMenu1);
			jMenu1.setText("File"); //the menubar is named File
			jmenubar.add(lblBlankSpace);
			jmenubar.add(lblName);
			jmenubar.add(lblnum);
			lblName.setVisible(false);
			lblnum.setVisible(false);
			jMenu1.setBackground(new java.awt.Color(128, 128, 128));
			//---this is the menu "Create Test Configuration"  defined
			JMenuItem jmenu_new = new JMenuItem("Create Test Configuration");
			jmenu_new.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					ChoosetypeFrame jfrm2 = new ChoosetypeFrame();
					jfrm2.setVisible(true);
					jfrm2.setLocationRelativeTo(null);
				}
			});
			//---this is the menu "Delete Test Configuration"  defined
			JMenuItem jmenu_del = new JMenuItem("Delete Test Configuration");
			jmenu_del.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree
							.getLastSelectedPathComponent();
					if (node == null) {
						JOptionPane
						.showMessageDialog(
								null,
								"Please select one config file to delete",
								null, JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (node.isLeaf()) {
						
						Object nodeInfo = node.getUserObject();
						leaf_node leaf = (leaf_node) nodeInfo;
						if(leaf.nameused!=null)
						{
							
							testname = leaf.nameused; // used by read_process yes
							testtype = leaf.type_style;
							DefaultMutableTreeNode dad = (DefaultMutableTreeNode) node
									.getParent();
							dad.remove(node);
							jtree.updateUI();
							File filedel = new File("input/" + testtype + "/"
									+ testname + "-" + testtype + ".config");
							filedel.delete();
						}
						else
						{
							JOptionPane
							.showMessageDialog(
									null,
									"Please select one config file to delete",
									null, JOptionPane.INFORMATION_MESSAGE);
						}

					}
				}
			});
			//---this is the menu "Stop Currently Running Test"  defined
			JMenuItem jmenu_stop = new JMenuItem("Stop Currently Running Test");
			jmenu_stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keepRunning = false; // stop ThreadRead & ThreadWrite
					if (p != null) p.destroy(); // destroy process
					process_running = false;   //let the other thread can start
					lblnum.setText("NULL");
				}
			});
			//---this is the menu "Exit the System"  defined
			JMenuItem exitsystem_menuitem = new JMenuItem("Exit the System");
			exitsystem_menuitem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.exit(0); // it is just used to stop the view
				}
			});
			//---this is the menu "Compare Two Results"  defined
			JMenuItem jmenuCompare=new JMenuItem("Compare Two Results");
			jmenuCompare.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if(process_running){
						JOptionPane
						.showMessageDialog(
								null,
								"You must stop the running test to make a comparision",
								null, JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					DataCompSelFrm asFrame=new DataCompSelFrm();
					asFrame.setVisible(true);
					asFrame.setLocationRelativeTo(null);
				}
			});
			jMenu1.add(jmenu_new);
			jMenu1.add(jmenu_del);
			jMenu1.add(jmenuCompare);
			jMenu1.add(jmenu_stop);
			jMenu1.add(exitsystem_menuitem);
		}
		frame.setJMenuBar(jmenubar);
	}
	/* the function createAndShowGUI() is used to create and show the GUI
	 * */
	private static void createAndShowGUI() {
		frame = new JFrame("Packet Classfication Evalution System"); 
		menu_create();
		frame.add(new MainFrame());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(772, 557);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		//ignore changes in tree selection
	}

}
//--- The class DataPoint defined here 
class DataPoint {
	public Long x;
	public Double y;
	public int rulenum;
}
class ThreadWrite extends Thread { // /this is a test that write a random number
	// to a file
	@Override
	public void run() {
		File file = new File(MainFrame.csv_filename);
		try {
			System.out.println("Writing to " + MainFrame.csv_filename);
			int counter = 0;
			FileWriter out = new FileWriter(file);
			while (MainFrame.keepRunning) {
				if (MainFrame.testtype.equals("Order")) {
					out.write(counter*1000 + "," + randomNum() + "," + counter + "\n");
				} else if (MainFrame.testtype.equals("Throughput")) {
					out.write(counter*1000 + "," + randomNum() + "\n");
				} else if (MainFrame.testtype.equals("Update")) {
					out.write(counter + "," + randomNum()+ "\n");
				}
				out.flush();
				Thread.sleep(1000); 
				counter += 1;
			}
			out.close();
			System.out.println("Stopping writing.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private long randomNum() {
		return (long) (Math.random() * 10000 + 400);
	}
}
