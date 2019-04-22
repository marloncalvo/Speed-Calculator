package frames;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import ft_project.Controller;
import utils.IntFilter;
import utils.StringFilter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class MainFrame extends Frame{
	private static Frame frame;
	private JPanel MainPane;
	private JTextField textF_Name;
	private JTextField textF_NumProb;
	private JTextField textF_NumDig;
	private JComboBox<String> cmbBox_ArithOps;

	/**
	 * Create the frame.
	 */
	public static Frame createMainFrame() {
		frame = new MainFrame();
		return frame;
	}
	
	private MainFrame() {
		MainPane = new JPanel();
		MainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_MainPane = new GridBagLayout();
		gbl_MainPane.columnWidths = new int[]{0, 0};
		gbl_MainPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_MainPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_MainPane.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		MainPane.setLayout(gbl_MainPane);
		
		JPanel HeaderPane = new JPanel();
		GridBagConstraints gbc_HeaderPane = new GridBagConstraints();
		gbc_HeaderPane.insets = new Insets(0, 0, 5, 0);
		gbc_HeaderPane.fill = GridBagConstraints.BOTH;
		gbc_HeaderPane.gridx = 0;
		gbc_HeaderPane.gridy = 0;
		MainPane.add(HeaderPane, gbc_HeaderPane);
		HeaderPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblArithmeticHelper = new JLabel("ARITHMETIC HELPER");
		lblArithmeticHelper.setHorizontalAlignment(SwingConstants.CENTER);
		lblArithmeticHelper.setFont(new Font("Dialog", Font.BOLD, 32));
		HeaderPane.add(lblArithmeticHelper);
		
		JPanel InfoPane = new JPanel();
		GridBagConstraints gbc_InfoPane = new GridBagConstraints();
		gbc_InfoPane.insets = new Insets(0, 0, 5, 0);
		gbc_InfoPane.fill = GridBagConstraints.BOTH;
		gbc_InfoPane.gridx = 0;
		gbc_InfoPane.gridy = 1;
		MainPane.add(InfoPane, gbc_InfoPane);
		GridBagLayout gbl_InfoPane = new GridBagLayout();
		gbl_InfoPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_InfoPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_InfoPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_InfoPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		InfoPane.setLayout(gbl_InfoPane);
		
		JLabel lblName = new JLabel("Enter your Nick name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		InfoPane.add(lblName, gbc_lblName);
		
		textF_Name = new JTextField();
		textF_Name.setName("name");
		GridBagConstraints gbc_textF_Name = new GridBagConstraints();
		gbc_textF_Name.insets = new Insets(0, 0, 5, 0);
		gbc_textF_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_Name.gridx = 3;
		gbc_textF_Name.gridy = 0;
		InfoPane.add(textF_Name, gbc_textF_Name);
		textF_Name.setColumns(10);
		PlainDocument doc3 = (PlainDocument) textF_Name.getDocument();
	      doc3.setDocumentFilter(new StringFilter());
		
		JLabel lblNumberOfProblems = new JLabel("Number of Problems:");
		GridBagConstraints gbc_lblNumberOfProblems = new GridBagConstraints();
		gbc_lblNumberOfProblems.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfProblems.gridx = 1;
		gbc_lblNumberOfProblems.gridy = 1;
		InfoPane.add(lblNumberOfProblems, gbc_lblNumberOfProblems);
		
		textF_NumProb = new JTextField();
		textF_NumProb.setName("num_problems");
		GridBagConstraints gbc_textF_NumProb = new GridBagConstraints();
		gbc_textF_NumProb.insets = new Insets(0, 0, 5, 0);
		gbc_textF_NumProb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_NumProb.gridx = 3;
		gbc_textF_NumProb.gridy = 1;
		InfoPane.add(textF_NumProb, gbc_textF_NumProb);
		textF_NumProb.setColumns(10);
		PlainDocument doc = (PlainDocument) textF_NumProb.getDocument();
	      doc.setDocumentFilter(new IntFilter());
		
		JLabel lblNumberOfDigits = new JLabel("Number of Digits");
		GridBagConstraints gbc_lblNumberOfDigits = new GridBagConstraints();
		gbc_lblNumberOfDigits.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfDigits.gridx = 1;
		gbc_lblNumberOfDigits.gridy = 2;
		InfoPane.add(lblNumberOfDigits, gbc_lblNumberOfDigits);
		
		textF_NumDig = new JTextField();
		textF_NumDig.setName("num_digits");
		GridBagConstraints gbc_textF_NumDig = new GridBagConstraints();
		gbc_textF_NumDig.insets = new Insets(0, 0, 5, 0);
		gbc_textF_NumDig.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_NumDig.gridx = 3;
		gbc_textF_NumDig.gridy = 2;
		InfoPane.add(textF_NumDig, gbc_textF_NumDig);
		textF_NumDig.setColumns(10);
		PlainDocument doc2 = (PlainDocument) textF_NumDig.getDocument();
	      doc2.setDocumentFilter(new IntFilter());
		
		JLabel lblTypeOfOperation = new JLabel("Type of Operation:");
		GridBagConstraints gbc_lblTypeOfOperation = new GridBagConstraints();
		gbc_lblTypeOfOperation.insets = new Insets(0, 0, 5, 5);
		gbc_lblTypeOfOperation.gridx = 1;
		gbc_lblTypeOfOperation.gridy = 3;
		InfoPane.add(lblTypeOfOperation, gbc_lblTypeOfOperation);
		
		cmbBox_ArithOps = new JComboBox<String>();
		cmbBox_ArithOps.setName("op");
		cmbBox_ArithOps.setModel(new DefaultComboBoxModel<String>(new String[] {"[--Select--]", "Addition [+]", "Subtraction [-]", "Multiplication [*]", "Division [/]", "Random"}));
		GridBagConstraints gbc_cmbBox_ArithOps = new GridBagConstraints();
		gbc_cmbBox_ArithOps.insets = new Insets(0, 0, 5, 0);
		gbc_cmbBox_ArithOps.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBox_ArithOps.gridx = 3;
		gbc_cmbBox_ArithOps.gridy = 3;
		InfoPane.add(cmbBox_ArithOps, gbc_cmbBox_ArithOps);
		
		JPanel ButtonPane = new JPanel();
		GridBagConstraints gbc_ButtonPane = new GridBagConstraints();
		gbc_ButtonPane.gridx = 0;
		gbc_ButtonPane.gridy = 2;
		MainPane.add(ButtonPane, gbc_ButtonPane);
		GridBagLayout gbl_ButtonPane = new GridBagLayout();
		gbl_ButtonPane.columnWidths = new int[]{0, 0, 0};
		gbl_ButtonPane.rowHeights = new int[]{0, 0, 0};
		gbl_ButtonPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_ButtonPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		ButtonPane.setLayout(gbl_ButtonPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.closeApplication();
			}
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 5);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 0;
		ButtonPane.add(btnClose, gbc_btnClose);
		
		JButton btnBegin = new JButton("Begin");
		btnBegin.setActionCommand("loadQuestion");
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isComplete()) {
					JOptionPane.showMessageDialog(null, "Invalid Input. Please make sure all entries are filled out");
				}
				else
					Controller.frameCompleted(frame);
			}
		});
		GridBagConstraints gbc_btnBegin = new GridBagConstraints();
		gbc_btnBegin.insets = new Insets(0, 0, 5, 0);
		gbc_btnBegin.gridx = 1;
		gbc_btnBegin.gridy = 0;
		ButtonPane.add(btnBegin, gbc_btnBegin);
		
		JLabel lblHelp = new JLabel("For Help, Please see Manual");
		GridBagConstraints gbc_lblHelp = new GridBagConstraints();
		gbc_lblHelp.gridwidth = 2;
		gbc_lblHelp.insets = new Insets(10, 0, 0, 5);
		gbc_lblHelp.gridx = 0;
		gbc_lblHelp.gridy = 1;
		ButtonPane.add(lblHelp, gbc_lblHelp);
	}
	
	public boolean isComplete() {
		if(!textF_Name.getText().trim().isEmpty() && !textF_NumProb.getText().trim().isEmpty()
				&& !textF_NumDig.getText().trim().isEmpty() && cmbBox_ArithOps.getSelectedIndex() != 0)
			return true;
		else
			return false;
	}
	
	public HashMap<Object, Object> getSessionData() {
		HashMap<Object, Object> data = new HashMap<>();
		data.put(textF_Name.getName(), textF_Name.getText());
		data.put(textF_NumProb.getName(), textF_NumProb.getText());
		data.put(textF_NumDig.getName(), textF_NumDig.getText());
		data.put(cmbBox_ArithOps.getName(), cmbBox_ArithOps.getSelectedItem().toString());
		return data;
	}
	
	public JPanel getContentPane() {
		return MainPane;
	}
}
