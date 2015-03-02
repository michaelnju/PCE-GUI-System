package GUI;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
/*This class is used to create a new throughput configuration file. 
 * After the creating,  the file will show both in the local file and the tree node. 
 * */
public class ThroughputFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	// private static final long serialVersionUID = 1L;
	private JButton save_btn;
	public JLabel Runduration_lbl;
	private JTextField packetsize_txtfield;
	private JLabel packetsize_lbl;
	private JTextField name_txt;
	public JLabel min_lbl;
	public JTextField runduration_txtfield;
	private JButton brow_acl;
	private JTextField acl_txtfield;
	private JLabel acl_lbl;
	private JRadioButton uniform_radio;
	private JRadioButton ununiform_radio;
	private JLabel traffic_lbl;
	private JButton help_btn;
	private JTextField traffic_txt;
	private JLabel warn_lbl;
	private JLabel jlb_Kb;
	private JLabel lbl_txtname;
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JLabel lbl_pattern;
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public ThroughputFrame() {
		super();
		initGUI();
	}
	/*---initGUI is used to set the layout file, you need not to waste time
	 *  here in the conditions that you do not want to make some change for the layout.
	 * */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setBackground(new java.awt.Color(228, 230, 227));
			this.setTitle("Throughput Data Input Window");
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setLocale(new java.util.Locale("en", "US"));
			{
				save_btn = new JButton("Save");
				buttonGroup1.add(getUniform_radio());
				buttonGroup1.add(getUnuniform_radio());
				getContentPane().add(
						getLbl_pattern(),
						new AnchorConstraint(652, 620, 696, 39,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getUnuniform_radio(),
						new AnchorConstraint(546, 673, 595, 158,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getUniform_radio(),
						new AnchorConstraint(479, 838, 528, 158,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getTrafiic_txt(),
						new AnchorConstraint(742, 970, 804, 31,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getWarn_lbl(),
						new AnchorConstraint(603, 1039, 657, 15,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_ABS));
				getContentPane().add(
						getTraffic_lbl(),
						new AnchorConstraint(396, 310, 443, 54,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getHelp_btn(),
						new AnchorConstraint(647, 973, 704, 620,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getJlb_Kb(),
						new AnchorConstraint(218, 945, 270, 732,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						save_btn,
						new AnchorConstraint(856, 546, 923, 326,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				save_btn.setPreferredSize(new java.awt.Dimension(87, 26));
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
				lbl_txtname = new JLabel();
				AnchorLayout lbl_txtnameLayout = new AnchorLayout();
				lbl_txtname.setLayout(lbl_txtnameLayout);
				getContentPane().add(
						lbl_txtname,
						new AnchorConstraint(42, 305, 96, 90,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				lbl_txtname.setText("Test Name");
				lbl_txtname.setPreferredSize(new java.awt.Dimension(85, 21));
			}
			{
				getContentPane().add(
						getAcl_lbl(),
						new AnchorConstraint(120, 288, 171, 133,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getAcl_txtfield(),
						new AnchorConstraint(125, 701, 187, 300,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getBrow_acl(),
						new AnchorConstraint(125, 958, 192, 729,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getPacketsize_lbl(),
						new AnchorConstraint(210, 298, 264, 82,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getPackesize_txtfield(),
						new AnchorConstraint(210, 701, 275, 300,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getRunduration_lbl(),
						new AnchorConstraint(301, 282, 355, 57,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getRunduration_txtfield(),
						new AnchorConstraint(306, 701, 365, 295,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getMin_lbl(),
						new AnchorConstraint(301, 871, 357, 737,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				getContentPane().add(
						getName_txt(),
						new AnchorConstraint(38, 701, 97, 305,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));

			}
			pack();
			this.setSize(410, 425);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}
	/*count_aclFile() function used to  to count a file’s 
	 * total numbers of the lines , when we calculate the probability 
	 * it will be called. the argument filepath is the address of the file selected
	 * */
	public static int count_aclFile(String filepath) throws Exception {
		int count = 0;
		FileReader fileReader = null;
		BufferedReader buffer = null;
		File newFile = new File(filepath);
		try {
			fileReader = new FileReader(newFile);
			buffer = new BufferedReader(fileReader);
			try {
				while (buffer.readLine() != null) {
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				buffer.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		return count;
	}
	/*save_btnActionPerformed function is used to save the file we created
	 * */
	private void save_btnActionPerformed(ActionEvent evt) throws Exception {
		String type_test = "Throughput";
		String txt_name = name_txt.getText();
		String acl_name = acl_txtfield.getText();
		String run_duration = runduration_txtfield.getText();
		String packetsize = packetsize_txtfield.getText();
		if (!txt_name.isEmpty() && !acl_name.isEmpty()
				&& !run_duration.isEmpty() && !packetsize.isEmpty()) {
			int n = ThroughputFrame.count_aclFile(acl_name);
			float possibility = (float) 1 / (float) n;//---compute the possibility
			String config_add = "input/" + type_test + "/" + txt_name + "-"
					+ type_test + ".config";//where the new file put in
			File newFile = new File(config_add);
			FileOutputStream fos = new FileOutputStream(newFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			String[] arrs = { acl_name, packetsize, run_duration }; 														
			for (int i = 0; i < arrs.length; i++) {
				bw.write(arrs[i] + "\n");
			}
			//--- if the ununiform_radio is selected, then 
			if (ununiform_radio.isSelected()) {
				String temp = traffic_txt.getText();
				if (!temp.isEmpty()) {
					String[] tempArr = {};
					float total_possibility = 0;
					float count_possibility = 0;
					tempArr = temp.split(",|;");
					int tempArrlength = tempArr.length;
					int[] line_lbl = new int[tempArrlength / 2];
					float[] possibility_lbl = new float[tempArrlength / 2];
					for (int i = 0, j = 0; i < tempArrlength - 1; i += 2) {
						line_lbl[j++] = Integer.parseInt(tempArr[i]);

					}
					for (int k = 1, j = 0; k < tempArrlength; k += 2) {
						possibility_lbl[j++] = Float.parseFloat(tempArr[k]);
						total_possibility += Float.parseFloat(tempArr[k]);
					}
					if (total_possibility > 1
							|| Integer.valueOf(packetsize) < 64) {
						bw.close();
						osw.close();
						fos.close();
						newFile.delete();
						if (total_possibility > 1)
							JOptionPane
									.showMessageDialog(
											null,
											"Sorry,the sum of the possibility cannot be greater than 1,please check it",
											null, JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane
									.showMessageDialog(
											null,
											"Sorry,the packetsize should be at least 64 bytes ,please check it ",
											null, JOptionPane.ERROR_MESSAGE);

					} else {
						DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(
								new MainFrame.leaf_node(txt_name
										+ "-Throughput.config", txt_name,
										type_test));
						MainFrame.child1.add(leaf);
						MainFrame.jtree.updateUI();
						count_possibility = (1 - total_possibility)
								/ (n - tempArrlength / 2);
						for (int m = 1, j = 0; m <= n; m++) {
							if (j < tempArrlength / 2 && m == line_lbl[j]) {
								bw.write(String.valueOf(possibility_lbl[j])
										+ "\n");
								j++;
							} else
								bw.write(String.valueOf(count_possibility)
										+ "\n");
						}
						bw.close();
						osw.close();
						fos.close();
						JOptionPane.showMessageDialog(null,
								"Add test successfully!", "Message alert",
								JOptionPane.WARNING_MESSAGE);
						this.dispose();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"You need to fill in the traffic pattern textbox ",
							null, JOptionPane.INFORMATION_MESSAGE);
					bw.close();
					return;
				}

			}
			// if the uniform_radio.isSelected then 
			else if (uniform_radio.isSelected()) {
				//make sure that the packetsize's number is larger than 64
				if (Integer.valueOf(packetsize) < 64) {
					JOptionPane
							.showMessageDialog(
									null,
									"Sorry,the packetsize should be  greater than 64,please check it ",
									null, JOptionPane.ERROR_MESSAGE);
				} else { //--- leaf created with the name we input and the time
					DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(
							new MainFrame.leaf_node(txt_name
									+ "-Throughput.config", txt_name, type_test));
					MainFrame.child1.add(leaf);
					for (int i = 0; i < n; i++)
						bw.write(String.valueOf(possibility) + "\n");
					bw.close();
					osw.close();
					fos.close();
					JOptionPane.showMessageDialog(null,
							"Add test successfully!", null,
							JOptionPane.INFORMATION_MESSAGE);
					MainFrame.jtree.updateUI();
					this.dispose();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"You need to fill in all the textbox", null,
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	private JLabel getAcl_lbl() {
		if (acl_lbl == null) {
			acl_lbl = new JLabel();
			AnchorLayout acl_lblLayout = new AnchorLayout();
			acl_lbl.setLayout(acl_lblLayout);
			acl_lbl.setText("ACL File");
			acl_lbl.setPreferredSize(new java.awt.Dimension(61, 20));
		}
		return acl_lbl;
	}

	private JTextField getAcl_txtfield() {
		if (acl_txtfield == null) {
			acl_txtfield = new JTextField();
			acl_txtfield.setPreferredSize(new java.awt.Dimension(158, 24));
		}
		return acl_txtfield;
	}

	private JButton getBrow_acl() {
		if (brow_acl == null) {
			brow_acl = new JButton();
			brow_acl.setText("File ");
			brow_acl.setFont(new java.awt.Font("微软雅黑", 0, 12));
			brow_acl.setPreferredSize(new java.awt.Dimension(90, 26));
			brow_acl.setLocale(new java.util.Locale("en", "US"));
			brow_acl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					brow_aclActionPerformed(evt);
				}
			});
		}
		return brow_acl;
	}

	private JLabel getPacketsize_lbl() {
		if (packetsize_lbl == null) {
			packetsize_lbl = new JLabel();
			packetsize_lbl.setText("Packet Size");
			packetsize_lbl.setPreferredSize(new java.awt.Dimension(85, 21));
		}
		return packetsize_lbl;
	}

	private JTextField getPackesize_txtfield() {
		if (packetsize_txtfield == null) {
			packetsize_txtfield = new JTextField();
			packetsize_txtfield
					.setPreferredSize(new java.awt.Dimension(158, 25));
		}
		return packetsize_txtfield;
	}

	private JLabel getRunduration_lbl() {
		if (Runduration_lbl == null) {
			Runduration_lbl = new JLabel();
			Runduration_lbl.setText("Run Durarion");
			Runduration_lbl.setPreferredSize(new java.awt.Dimension(89, 21));
		}
		return Runduration_lbl;
	}

	private JTextField getRunduration_txtfield() {
		if (runduration_txtfield == null) {
			runduration_txtfield = new JTextField();
			runduration_txtfield.setPreferredSize(new java.awt.Dimension(160,
					23));
		}
		return runduration_txtfield;
	}

	private JLabel getMin_lbl() {
		if (min_lbl == null) {
			min_lbl = new JLabel();
			min_lbl.setText("Minutes");
			min_lbl.setPreferredSize(new java.awt.Dimension(53, 22));
		}
		return min_lbl;
	}

	void openfile_1() {// 打开文件

		JFileChooser fc = new JFileChooser();
		//fc.setDialogTitle("Open File");
		fc.setDialogType(JFileChooser.APPROVE_OPTION);
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			acl_txtfield.setText(fc.getSelectedFile().toString());
		}
	}
	private void brow_aclActionPerformed(ActionEvent evt) {
		openfile_1();
	}
	private JTextField getName_txt() {
		if (name_txt == null) {
			name_txt = new JTextField();
			name_txt.setBounds(119, 15, 156, 24);
		}
		return name_txt;
	}
	private JLabel getJlb_Kb() {
		if (jlb_Kb == null) {
			jlb_Kb = new JLabel();
			jlb_Kb.setText("Bytes(>=64)");
			jlb_Kb.setPreferredSize(new java.awt.Dimension(84, 20));
		}
		return jlb_Kb;
	}

	private JButton getHelp_btn() {
		if (help_btn == null) {
			help_btn = new JButton();
			help_btn.setText("Format Instruction");
			help_btn.setPreferredSize(new java.awt.Dimension(139, 22));
			help_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					help_btnActionPerformed(evt);
				}
			});
		}
		return help_btn;
	}

	private JLabel getTraffic_lbl() {
		if (traffic_lbl == null) {
			traffic_lbl = new JLabel();
			traffic_lbl.setText("Traffic Pattern:");
			traffic_lbl.setPreferredSize(new java.awt.Dimension(101, 18));
		}
		return traffic_lbl;
	}

	private JLabel getWarn_lbl() {
		if (warn_lbl == null) {
			warn_lbl = new JLabel();
			warn_lbl.setText("Please specify special rule ID and probility pair;\r\n");
			warn_lbl.setPreferredSize(new java.awt.Dimension(394, 21));
		}
		return warn_lbl;
	}

	private JTextField getTrafiic_txt() {
		if (traffic_txt == null) {
			traffic_txt = new JTextField();
			traffic_txt.setPreferredSize(new java.awt.Dimension(370, 24));
		}
		return traffic_txt;
	}

	private void help_btnActionPerformed(ActionEvent evt) {
		AlertWindows alert = new AlertWindows();
		alert.setVisible(true);
		alert.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() - alert.getWidth()) / 2, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() - alert
				.getHeight()) / 2);

	}

	private JRadioButton getUniform_radio() {
		if (uniform_radio == null) {
			uniform_radio = new JRadioButton(
					"Uniform traffic distribution Among all rules", false);
			uniform_radio.setPreferredSize(new java.awt.Dimension(268, 19));
			uniform_radio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					uniform_radioActionPerformed(evt);
				}
			});
		}
		return uniform_radio;
	}

	private JRadioButton getUnuniform_radio() {
		if (ununiform_radio == null) {
			ununiform_radio = new JRadioButton(
					"Non-Uniform traffic distribution", true);// 设置为默认选择
			ununiform_radio.setPreferredSize(new java.awt.Dimension(203, 19));
			ununiform_radio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					traffic_txt.setVisible(true);
					warn_lbl.setVisible(true);
					help_btn.setVisible(true);
					lbl_pattern.setVisible(true);
				}
			});
		}
		return ununiform_radio;
	}

	private void uniform_radioActionPerformed(ActionEvent evt) {
		warn_lbl.setVisible(false);
		traffic_txt.setVisible(false);
		help_btn.setVisible(false);
		lbl_pattern.setVisible(false);
	}

	private JLabel getLbl_pattern() {
		if (lbl_pattern == null) {
			lbl_pattern = new JLabel();
			lbl_pattern.setText("In format like\t  (ID1,prob;ID2,prob; ...)");
			lbl_pattern.setPreferredSize(new java.awt.Dimension(229, 17));
		}
		return lbl_pattern;
	}
}
