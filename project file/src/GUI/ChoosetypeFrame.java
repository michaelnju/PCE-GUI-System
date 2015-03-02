package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
/*---This class alow you choose the type of the file which are Throughput,
 *  Update, Oder to create the new configuration files.
*/
public class ChoosetypeFrame extends javax.swing.JFrame {
	private JLabel lblAlert;
	private JPanel jPanel1;
	private JButton btnExit;
	private JButton btnOrder;
	private JButton btnUpdate;
	private JButton btnThroughput;
	public ChoosetypeFrame() {
		super();
		initGUI();
	}
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Choosing type window");
			getContentPane().setBackground(new java.awt.Color(192,192,192));
			getContentPane().setForeground(new java.awt.Color(192,192,192));
			{
				lblAlert = new JLabel();
				getContentPane().add(lblAlert);
				lblAlert.setText("Please select the type:");
				lblAlert.setBounds(36, 36, 193, 27);
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(48, 75, 268, 135);
				jPanel1.setBackground(new java.awt.Color(222,222,222));
				jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				jPanel1.setLayout(null);
				{
					btnThroughput = new JButton();
					jPanel1.add(btnThroughput);
					btnThroughput.setText("Throughput");
					btnThroughput.setBounds(67, 27, 121, 24);
					btnThroughput.setBackground(new java.awt.Color(255,255,255));
					btnThroughput.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					btnThroughput.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnThroughputActionPerformed(evt);
						}
					});
				}
				{
					btnUpdate = new JButton();
					jPanel1.add(btnUpdate);
					btnUpdate.setText("Update");
					btnUpdate.setBounds(67, 63, 121, 24);
					btnUpdate.setBackground(new java.awt.Color(255,255,255));
					btnUpdate.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					btnUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnUpdateActionPerformed(evt);
						}
					});
				}
				{
					btnOrder = new JButton();
					jPanel1.add(btnOrder);
					btnOrder.setText("Order\r\n");
					btnOrder.setBackground(new java.awt.Color(255,255,255));
					btnOrder.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					btnOrder.setBounds(67, 99, 121, 24);
					btnOrder.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnOrderActionPerformed(evt);
						}
					});
				}
			}
			{
				btnExit = new JButton();
				getContentPane().add(btnExit);
				btnExit.setText("Exit");
				btnExit.setBounds(132, 226, 84, 24);
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnExitActionPerformed(evt);
					}
				});
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	private void btnExitActionPerformed(ActionEvent evt) {
		this.dispose();
	}
	private void btnThroughputActionPerformed(ActionEvent evt) {
		ThroughputFrame jfm = new ThroughputFrame();
		jfm.setVisible(true);
		jfm.setLocationRelativeTo(null);
		this.dispose();
	}
	private void btnUpdateActionPerformed(ActionEvent evt) {
		UpdateFrame jfm = new UpdateFrame();
		jfm.setVisible(true);
		jfm.setLocationRelativeTo(null);
		this.dispose();
	}
	
	private void btnOrderActionPerformed(ActionEvent evt) {
		OrderFrame jfm = new OrderFrame();
		jfm.setVisible(true);
		jfm.setLocationRelativeTo(null);
		this.dispose();
	}
	
}
