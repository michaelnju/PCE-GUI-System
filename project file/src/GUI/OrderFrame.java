package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
/*This class is used to create a new Order configuration file. 
 * After the creating,  the file will show both in the local file and the tree node. 
 * */
public class OrderFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel testname_lbl;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField orderinterval_txt;
	private JButton aclselect_btn;
	private JLabel jLabel5;
	private JTextField testname_txt;
	private JLabel jLabel6;
	private JLabel jLabel4;
	private JLabel packetsize_lbl;
	private JButton cancel_btn;
	private JButton save_btn;
	private JTextField runduration_txt;
	private JTextField packetsize_txt;
	private JTextField aclfile_txt;
	private JLabel jLabel1;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				OrderFrame inst = new OrderFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public OrderFrame() {
		super();
		initGUI();
	}
	/*---initGUI is used to set the layout file, you need not to waste time
	 *  here in the conditions that you do not want to make some change for the layout.
	 * */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Order data inout\r\n");
			getContentPane().setLayout(null);
			{
				testname_lbl = new JLabel();
				getContentPane().add(testname_lbl, "Center");
				testname_lbl.setText("Acl file\r\n");
				testname_lbl.setBounds(55, 58, 67, 38);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, "Center");
				jLabel1.setText(" Order interval");
				jLabel1.setBounds(12, 93, 112, 38);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, "Center");
				jLabel2.setText("Packet Size");
				jLabel2.setBounds(31, 127, 67, 38);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, "Center");
				jLabel3.setText("Run duration");
				jLabel3.setBounds(25, 165, 96, 38);
			}
			{
				aclfile_txt = new JTextField();
				getContentPane().add(aclfile_txt);
				aclfile_txt.setBounds(122, 66, 129, 24);
			}
			{
				orderinterval_txt = new JTextField();
				getContentPane().add(orderinterval_txt);
				orderinterval_txt.setBounds(122, 101, 129, 24);
			}
			{
				packetsize_txt = new JTextField();
				getContentPane().add(packetsize_txt);
				packetsize_txt.setBounds(122, 135, 129, 24);
			}
			{
				runduration_txt = new JTextField();
				getContentPane().add(runduration_txt);
				runduration_txt.setBounds(122, 173, 129, 24);
			}
			{
				save_btn = new JButton();
				getContentPane().add(save_btn);
				save_btn.setText("Save");
				save_btn.setBounds(102, 232, 74, 24);
				save_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							save_btnActionPerformed(evt);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			{
				cancel_btn = new JButton();
				getContentPane().add(cancel_btn);
				cancel_btn.setText("Cancel");
				cancel_btn.setBounds(187, 232, 74, 24);
				cancel_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						cancel_btnActionPerformed(evt);
					}
				});
			}
			{
				packetsize_lbl = new JLabel();
				getContentPane().add(packetsize_lbl);
				packetsize_lbl.setText("Bytes(>=64)");
				packetsize_lbl.setBounds(268, 138, 87, 17);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Minute\r\n");
				jLabel4.setBounds(268, 176, 43, 17);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Packets\r\n");
				jLabel5.setBounds(268, 104, 54, 17);
			}
			{
				aclselect_btn = new JButton();
				getContentPane().add(aclselect_btn);
				aclselect_btn.setText("Select");
				aclselect_btn.setBounds(265, 66, 74, 24);
				aclselect_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						aclselect_btnActionPerformed(evt);
					}
				});
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6, "Center");
				jLabel6.setText("Test name\r\n");
				jLabel6.setBounds(36, 21, 67, 38);
			}
			{
				testname_txt = new JTextField();
				getContentPane().add(testname_txt);
				testname_txt.setBounds(121, 29, 129, 24);
			}
			pack();
			this.setSize(394, 354);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}
	/*save_btnActionPerformed function is used to save the file we created
	 * */
	private void save_btnActionPerformed(ActionEvent evt) throws Exception {
		String txt_name = testname_txt.getText();
		String acl_name = aclfile_txt.getText();
		String order_interval = orderinterval_txt.getText();
		String packetsize = packetsize_txt.getText();
		String run_duration = runduration_txt.getText();
		if (!txt_name.isEmpty() && !acl_name.isEmpty()
				&& !order_interval.isEmpty() && !packetsize.isEmpty()
				&& !run_duration.isEmpty()) {
			if (Integer.valueOf(packetsize) >= 64) {
				String config_add = "input/" + "Order" + "/" + txt_name + "-"
						+ "Order" + ".config"; // //
				File newFile = new File(config_add);
				FileOutputStream fos = new FileOutputStream(newFile);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
				String[] arrs = { acl_name, order_interval, packetsize,
						run_duration }; // insert to the common value
				for (int i = 0; i < arrs.length; i++) {
					bw.write(arrs[i] + "\n");
				}
				bw.close();
				osw.close();
				fos.close();
				JOptionPane.showMessageDialog(null, "Add test successfully!",
						"Message alert", JOptionPane.WARNING_MESSAGE);
				this.dispose();
				DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(
						new MainFrame.leaf_node(txt_name + "-Order.config",
								txt_name, "Order"));
				MainFrame.child3.add(leaf);
				MainFrame.jtree.updateUI();
				this.dispose();
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Sorry,the packetsize should be at least 64 bytes ,please check it ",
								null, JOptionPane.ERROR_MESSAGE);

			}

		} else {
			JOptionPane.showMessageDialog(null,
					"You need to fill in all the textbox", null,
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	private void cancel_btnActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	private void aclselect_btnActionPerformed(ActionEvent evt) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open File");
		fc.setDialogType(JFileChooser.APPROVE_OPTION);
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			aclfile_txt.setText(fc.getSelectedFile().toString());
		}
	}

}
