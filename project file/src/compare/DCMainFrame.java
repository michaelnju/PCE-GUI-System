package compare;
import GUI.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
@SuppressWarnings("serial")
public class DCMainFrame extends javax.swing.JFrame {
	private JLabel lblAlert;
	private JLabel lblAlert1;
	private JButton btnSelectData1;
	private JButton btnExit;
	private JButton btnCompare;
	private JButton btnSelectData2;
	private JTextField txtData2Path;
	private JLabel lblAlert2;
	private static JTextField txtData1Path;
	public static String type;
	private static  String path1;
	private static  String path2;
	public static CompareUPChart UPChart;
	public static CompareRTChart RTChart;
	static ArrayList<Integer>dataset1;
	private JPanel jPanel2;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JButton btnSorted;
	static ArrayList<Integer>dataset2;
	static boolean sorted=false;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public DCMainFrame() {
		super();
		initGUI();
	}
//---initGUI() is a function used to set the layout
	private void initGUI() {		
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Select Performace Data Window\r\n");
			getContentPane().setLayout(null);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(4, 176, 420, 136);
				jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				jPanel1.setLayout(null);
				{
					btnExit = new JButton();
					jPanel1.add(btnExit);
					btnExit.setText("Exit");
					btnExit.setBounds(308, 69, 85, 24);
					btnExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnExitData1ActionPerformed(evt);
						}
					});
				}
				{
					btnSorted = new JButton();
					jPanel1.add(btnSorted);
					btnSorted.setText("sortAndcompare");
					btnSorted.setBounds(23, 69, 136, 24);
					btnSorted.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnSortedActionPerformed(evt);
						}
					});
				}
				{
					btnCompare = new JButton();
					jPanel1.add(btnCompare);
					btnCompare.setText("Compare");
					btnCompare.setBounds(179, 69, 94, 24);
					btnCompare.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnCompareActionPerformed(evt);
						}
					});
					
				}
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("For viewing,you can order the data by y-axis, or just compare");
					jLabel1.setBounds(23, 26, 358, 17);
				}
			}
			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2);
				jPanel2.setBounds(4, 7, 419, 149);
				jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				jPanel2.setLayout(null);
				{
					lblAlert = new JLabel();
					jPanel2.add(lblAlert);
					lblAlert.setText("Please select the performance data:\r\n");
					lblAlert.setBounds(2, 2, 244, 34);
				}
				{
					lblAlert1 = new JLabel();
					jPanel2.add(lblAlert1);
					lblAlert1.setText("Data1:\r\n");
					lblAlert1.setBounds(25, 42, 41, 17);
				}
				{
					txtData1Path = new JTextField();
					jPanel2.add(txtData1Path);
					txtData1Path.setBounds(71, 39, 234, 24);
				}
				{
					txtData2Path = new JTextField();
					jPanel2.add(txtData2Path);
					txtData2Path.setBounds(71, 87, 234, 24);
				}
				{
					lblAlert2 = new JLabel();
					jPanel2.add(lblAlert2);
					lblAlert2.setText("Data2:");
					lblAlert2.setBounds(23, 90, 41, 17);
				}
				{
					btnSelectData1 = new JButton();
					jPanel2.add(btnSelectData1);
					btnSelectData1.setText("Select");
					btnSelectData1.setBounds(317, 39, 75, 24);
					btnSelectData1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnSelectData1ActionPerformed(evt);
						}
					});
				}
				{
					btnSelectData2 = new JButton();
					jPanel2.add(btnSelectData2);
					btnSelectData2.setText("Select");
					btnSelectData2.setBounds(317, 87, 75, 24);
					btnSelectData2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnSelectData2ActionPerformed(evt);
						}
					});
					
				}
			}
			pack();
			this.setSize(440, 363);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	/*btnExitData1ActionPerformed is
	 * the action of the ¡°Exit¡± button
	 * */
	private void btnExitData1ActionPerformed(ActionEvent evt){
		this.dispose();
		MainFrame.keepRunning = false; // stop ThreadRead & ThreadWrite
		MainFrame.process_running = false;   //let the other thread can start
		MainFrame.lblnum.setText("NULL"); 
	}
	@SuppressWarnings("static-access")
	/*The following two functions are actions of the select-buttons  used to 
	 * select the file from the output. 
	 * */
	private void btnSelectData1ActionPerformed(ActionEvent evt) {
		//---"output/"+type is used to locate the address to select the file
		JFileChooser fc=new JFileChooser("output/"+type);
		if(fc.showOpenDialog(this)==fc.APPROVE_OPTION)
		{
			txtData1Path.setText(fc.getSelectedFile().getName().toString());
		}
	}
	@SuppressWarnings("static-access")
	private void btnSelectData2ActionPerformed(ActionEvent evt) {
		JFileChooser fc=new JFileChooser("output/"+type);
		if(fc.showOpenDialog(this)==fc.APPROVE_OPTION)
		{
			txtData2Path.setText(fc.getSelectedFile().getName().toString());
		}
	}
	/*The thread ¡°ReadTask¡± used to read the data from the two lines.
	 * */
	private static final Runnable ReadTask = new Runnable() {
		public void run() {
			dataset1=new ArrayList<Integer>();
			dataset2=new ArrayList<Integer>();
			String currentline1 = null;
			String currentline2 = null;
			MainFrame.keepRunning = true;   
			System.out.println("Opening file " + path1);
			System.out.println("Opening file " + path2);
			File file1 = new File(path1);
			File file2 = new File(path2);
			BufferedReader reader1,reader2;
			try {
				reader1 = new BufferedReader(new FileReader(file1));
				reader2 = new BufferedReader(new FileReader(file2));
				System.out.println("Reading file " + path1);
				System.out.println("Reading file " + path2);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
				return;
			}
		try {
		   while ((currentline1 = reader1.readLine()) != null)
			{
				System.out.println("Read: " + currentline1);
				String temp1[] = currentline1.split(",");	
			    dataset1.add(Integer.valueOf(temp1[1]));
				}//---this is used to collect the data to the dataset1
		while((currentline2 = reader2.readLine()) != null)
		{
				System.out.println("Read: " +currentline2);
				String temp2[] = currentline2.split(",");
				dataset2.add(Integer.valueOf(temp2[1]));
		}//---this is used to collect the data to the dataset2
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			reader1.close();
			reader2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Integer>datasetNew2=new ArrayList<Integer>();
		ArrayList<Integer>datasetNew1=new ArrayList<Integer>();
		//---Following lines are used to make the two dataset the same length
		if(dataset1.size()<dataset2.size())
		{
			for(int i=0;i<dataset1.size();i++)
				datasetNew2.add(dataset2.get(i));
			datasetNew1=dataset1;
		}
		else{
			for(int i=0;i<dataset2.size();i++)
				datasetNew1.add(dataset1.get(i));
			datasetNew2=dataset2;
		}
		//---If the user want to sort the data then sorted the data
		if(sorted)
		{
			Collections.sort(datasetNew1);
			Collections.sort(datasetNew2);
			sorted=false;
		}
		if(!type.equals("Update"))
			for(int i=0;i<datasetNew1.size();i++)
				CompareRTChart.linechartDataSeries1.add(i, datasetNew1.get(i));
			else
				for(int i=0;i<datasetNew1.size();i++)
					CompareUPChart.linechartDataSeries1.add(i, datasetNew1.get(i));
		if(!type.equals("Update"))
			for(int i=0;i<datasetNew2.size();i++)
				CompareRTChart.linechartDataSeries2.add(i, datasetNew2.get(i));
			else
				for(int i=0;i<datasetNew2.size();i++)
					CompareUPChart.linechartDataSeries2.add(i, datasetNew2.get(i));
		}
	};
//	@SuppressWarnings("resource")
//--- the compare button action	
	private void btnCompareActionPerformed(ActionEvent evt)
	{
		MainFrame.process_running=true;//this is to make sure that only one thread is running
		path1="output/"+type+"/"+txtData1Path.getText().trim();
		path2="output/"+type+"/"+txtData2Path.getText().trim();
		/*---Following two line make the two chart, the first argument is 
			the x-axis's name,the second is the y-axis's name
		*/
		RTChart=new CompareRTChart("Time", "Throughput (B/s)");
		UPChart=new CompareUPChart("Rule ID", "Update Time(ms)");
		if(!type.equals("Update"))
		{	
			//---if Update selected, the RTChart will be added in the MainFrame
			if(!MainFrame.splitPane.getBottomComponent().equals(RTChart))
				MainFrame.splitPane.setBottomComponent(RTChart);
			CompareRTChart.linechartDataSeries1.clear();
			CompareRTChart.linechartDataSeries2.clear();
			new Thread(ReadTask).start();
		}
		else
		{
			//---if UPChart selected, the UPChart will be added in the MainFrame
			if(!MainFrame.splitPane.getBottomComponent().equals(UPChart))
				MainFrame.splitPane.setBottomComponent(UPChart);
			CompareUPChart.linechartDataSeries1.clear();
			CompareUPChart.linechartDataSeries2.clear();
			new Thread(ReadTask).start();
		}
	}
	//--- the function btnSortedActionPerformed make the data sorted and then compare
	private void btnSortedActionPerformed(ActionEvent evt) {
		sorted=true;
		MainFrame.process_running=true;
		path1="output/"+type+"/"+txtData1Path.getText().trim();
		path2="output/"+type+"/"+txtData2Path.getText().trim();
		RTChart=new CompareRTChart("Time", "Throughput (B/s)");
		UPChart=new CompareUPChart("Rule ID", "Update Time(ms)");
		if(!type.equals("Update"))
		{	
			if(!MainFrame.splitPane.getBottomComponent().equals(RTChart))
				MainFrame.splitPane.setBottomComponent(RTChart);
			CompareRTChart.linechartDataSeries1.clear();
			CompareRTChart.linechartDataSeries2.clear();
			new Thread(ReadTask).start();
		}
		else
		{
			if(!MainFrame.splitPane.getBottomComponent().equals(UPChart))
				MainFrame.splitPane.setBottomComponent(UPChart);
			CompareUPChart.linechartDataSeries1.clear();
			CompareUPChart.linechartDataSeries2.clear();
			new Thread(ReadTask).start();
		}
	}
}

