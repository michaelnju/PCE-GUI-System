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
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
/*
 * This class is used to create a new Update configuration file. 
 * After the creating,  the file will show both in the local file and the tree node. */
public class UpdateFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel name_lbl;
	private JLabel acl_lbl;
	private JTextField testname_txt;
	private JButton cancel_btn;
	private JButton save_btn;
	private JLabel ruduration_lbl;
	private JLabel jLabel1;
	private JButton aclbrowser_btn;
	private JTextField runduration_txt;
	private JTextField packetsize_txt;
	private JTextField aclfile_txt;
	private JLabel runduration_lbl;
	private JLabel packetsize_lbl;
	public UpdateFrame() {
		super();
		initGUI();
	}
	/*---initGUI is used to set the layout file, you need not to waste time
	 *  here in the conditions that you do not want to make some change for the layout.
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Update data input windown\r\n");
			getContentPane().setLayout(null);
			this.setFont(new java.awt.Font("Arial", 0, 10));
			{
				name_lbl = new JLabel();
				getContentPane().add(name_lbl, "Center");
				name_lbl.setText("Test name");
				name_lbl.setBounds(32, 22, 83, 31);
			}
			{
				acl_lbl = new JLabel();
				getContentPane().add(acl_lbl);
				acl_lbl.setText("Acl File");
				acl_lbl.setBounds(47, 73, 40, 17);
			}
			{
				packetsize_lbl = new JLabel();
				getContentPane().add(packetsize_lbl);
				packetsize_lbl.setText("Packet Size");
				packetsize_lbl.setBounds(25, 118, 70, 17);
			}
			{
				runduration_lbl = new JLabel();
				getContentPane().add(runduration_lbl);
				runduration_lbl.setText("Runduration");
				runduration_lbl.setBounds(23, 154, 71, 17);
			}
			{
				testname_txt = new JTextField();
				getContentPane().add(testname_txt);
				testname_txt.setBounds(104, 26, 144, 24);
			}
			{
				aclfile_txt = new JTextField();
				getContentPane().add(aclfile_txt);
				aclfile_txt.setBounds(104, 70, 144, 24);
			}
			{
				packetsize_txt = new JTextField();
				getContentPane().add(packetsize_txt);
				packetsize_txt.setBounds(105, 115, 144, 24);
			}
			{
				runduration_txt = new JTextField();
				getContentPane().add(runduration_txt);
				runduration_txt.setBounds(106, 151, 144, 24);
			}
			{
				aclbrowser_btn = new JButton();
				getContentPane().add(aclbrowser_btn);
				aclbrowser_btn.setText("Select ");
				aclbrowser_btn.setBounds(260, 70, 81, 24);
				aclbrowser_btn.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 12));
				aclbrowser_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						aclbrowser_btnActionPerformed(evt);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Bytes(>64)");
				jLabel1.setBounds(261, 118, 91, 17);
			}
			{
				ruduration_lbl = new JLabel();
				getContentPane().add(ruduration_lbl);
				ruduration_lbl.setText("Minute");
				ruduration_lbl.setBounds(262, 154, 47, 17);
			}
			{
				save_btn = new JButton();
				getContentPane().add(save_btn);
				save_btn.setText("Save");
				save_btn.setBounds(81, 221, 73, 24);
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
				cancel_btn.setText("Cancel\r\n");
				cancel_btn.setBounds(171, 221, 79, 24);
				cancel_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						cancel_btnActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(380, 300);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void aclbrowser_btnActionPerformed(ActionEvent evt) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open File");
		fc.setDialogType(JFileChooser.APPROVE_OPTION);
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			aclfile_txt.setText(fc.getSelectedFile().toString());
		}
	}

	private void save_btnActionPerformed(ActionEvent evt) throws Exception {

		String txt_name = testname_txt.getText();
		String acl_name = aclfile_txt.getText();
		String packetsize = packetsize_txt.getText();
		String run_duration = runduration_txt.getText();
		if (!txt_name.isEmpty() && !acl_name.isEmpty() && !packetsize.isEmpty()
				&& !run_duration.isEmpty()) {
			if (Integer.valueOf(packetsize) >= 64) {
				String config_add = "input/" + "Update/" + txt_name + "-"
						+ "Update" + ".config";
				File newFile = new File(config_add);
				FileOutputStream fos = new FileOutputStream(newFile);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
				String[] arrs = { acl_name, packetsize, run_duration }; // insert
																		// to
																		// the
																		// common
																		// value
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
						new MainFrame.leaf_node(txt_name + "-Update.config",
								txt_name, "Update"));
				MainFrame.child2.add(leaf);
				MainFrame.jtree.updateUI();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null,
						"You need to fill in all the textbox", null,
						JOptionPane.INFORMATION_MESSAGE);
				return;
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
}
