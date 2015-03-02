package GUI;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import compare.DCMainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public class DataCompSelFrm extends javax.swing.JFrame {
	private JLabel lblAlert;
	private JButton btnCancel;
	private JPanel jPanel1;
	private JButton btnUp;
	private JButton btnOr;
	private JButton btnTh;
	private ButtonGroup btnGroup;
	public DataCompSelFrm() {
		super();
		initGUI();
	}
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	//---initGUI() is used to set the layout, you can ignore it.
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			{
				lblAlert = new JLabel();
				lblAlert.setLayout(null);
				getContentPane().add(lblAlert, new AnchorConstraint(127, 1057, 260, 73, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				lblAlert.setText("Please choose the type to compare:\r\n");
				lblAlert.setPreferredSize(new java.awt.Dimension(384, 34));
			}
			{
				btnCancel = new JButton();
				getContentPane().add(btnCancel, new AnchorConstraint(860, 562, 954, 360, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				btnCancel.setText("Cancel\r\n");
				btnCancel.setPreferredSize(new java.awt.Dimension(79, 24));
				btnCancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						btnCancelActionPerformed(evt);
					}
				});
			}
			{
				jPanel1 = new JPanel();
				AnchorLayout jPanel1Layout = new AnchorLayout();
				getContentPane().add(jPanel1, new AnchorConstraint(260, 791, 809, 121, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jPanel1.setBackground(new java.awt.Color(204,204,204));
				jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(261, 140));
				{
					btnTh = new JButton();
					jPanel1.add(btnTh, new AnchorConstraint(196, 710, 367, 254, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					btnTh.setText("Throughput");
					btnTh.setBackground(new java.awt.Color(255,255,255));
					btnTh.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					btnTh.setPreferredSize(new java.awt.Dimension(119, 24));
					btnTh.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnThActionPerformed(evt);
						}
					});
				}
				{
					btnUp = new JButton();
					jPanel1.add(btnUp, new AnchorConstraint(460, 710, 632, 254, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					btnUp.setText("Update\r\n");
					btnUp.setBackground(new java.awt.Color(255,255,255));
					btnUp.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					btnUp.setPreferredSize(new java.awt.Dimension(119, 24));
					btnUp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnUpActionPerformed(evt);
						}
					});
				}
				{
					btnOr = new JButton();
					jPanel1.add(btnOr, new AnchorConstraint(717, 710, 889, 254, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					btnOr.setText("Order\r\n");
					btnOr.setBackground(new java.awt.Color(255,255,255));
					btnOr.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					btnOr.setPreferredSize(new java.awt.Dimension(119, 24));
					btnOr.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnOrActionPerformed(evt);
						}
					});
				}
			}
			btnGroup=new ButtonGroup();
			pack();
			this.setSize(406, 294);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	//--- this is the button "Cancel" action 
	private void btnCancelActionPerformed(ActionEvent evt)
	{
		this.dispose();
	}
	//--- this is the button when you select the "Throughput"
	private void btnThActionPerformed(ActionEvent evt) {
		DCMainFrame.type="Throughput";	
		DCMainFrame dataFrm=new DCMainFrame();
		dataFrm.setVisible(true);
		dataFrm.setLocationRelativeTo(null);
		this.dispose();
	}
	//--- this is the button when you select the "Update"
	private void btnUpActionPerformed(ActionEvent evt) {
		DCMainFrame.type="Update";
		DCMainFrame dataFrm=new DCMainFrame();
		dataFrm.setVisible(true);
		dataFrm.setLocationRelativeTo(null);
		this.dispose();
	}
	//--- this is the button when you select the "Order"
	private void btnOrActionPerformed(ActionEvent evt) {
		DCMainFrame.type="Order";
		DCMainFrame dataFrm=new DCMainFrame();
		dataFrm.setVisible(true);
		dataFrm.setLocationRelativeTo(null);
		this.dispose();
	}
}
