package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.WindowConstants;
/*This class is used to give the user an example of the format
 *  when he selected the Non-uniform traffic distribution. It's 
 *  simple. If you want to make the words change you can do.
 * 
 * */
public class AlertWindows extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JButton ok_btn;
	private JEditorPane jEditorPane1;

	public AlertWindows() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Format instruction window");
			getContentPane().setLayout(null);
			{
				ok_btn = new JButton();
				getContentPane().add(ok_btn);
				ok_btn.setText("OK");
				ok_btn.setBounds(157, 166, 112, 29);
				ok_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ok_btnActionPerformed(evt);
					}
				});
			}
			{
				jEditorPane1 = new JEditorPane();
				getContentPane().add(jEditorPane1);
				
				//---yes, you can change the words here. 
				jEditorPane1
						.setText("Specify probability of rules in as<rule_id>,<probability>, separated with ;."
								+ " \r\nUnspecified rules will have remaining probability divided evenly.\r\n"
								+ "For example,for an ACL with 4 rules,if you specify \r\n    2,0.5;4,0.3;"
								+ "\r\nthen the probabilities for rule 1,2,3,4 are 0.1, 0.5, 0.1, 0.3."
								+ "\r\nThe sum of the input probabilities should be not larger than 1.\r\nThank you!\r\n");
				jEditorPane1.setBounds(12, 21, 427, 133);
				jEditorPane1.setEditable(false);
			}
			pack();
			this.setSize(474, 245);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void ok_btnActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
