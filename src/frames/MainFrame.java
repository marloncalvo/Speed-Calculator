package frames;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		MainPane.setBorder(new EmptyBorder(5, 100, 5, 100));
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
		textF_Name.setToolTipText("Please enter a nickname!");
		GridBagConstraints gbc_textF_Name = new GridBagConstraints();
		gbc_textF_Name.insets = new Insets(0, 0, 5, 0);
		gbc_textF_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_Name.gridx = 3;
		gbc_textF_Name.gridy = 0;
		InfoPane.add(textF_Name, gbc_textF_Name);
		textF_Name.setColumns(10);
		StringFilter.setStringFilter(textF_Name);
		
		JLabel lblNumberOfProblems = new JLabel("Number of Problems:");
		GridBagConstraints gbc_lblNumberOfProblems = new GridBagConstraints();
		gbc_lblNumberOfProblems.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfProblems.gridx = 1;
		gbc_lblNumberOfProblems.gridy = 1;
		InfoPane.add(lblNumberOfProblems, gbc_lblNumberOfProblems);
		
		textF_NumProb = new JTextField();
		textF_NumProb.setName("num_problems");
		textF_NumProb.setToolTipText("Number of questions to show. Please enter a number 1 - 100.");
		GridBagConstraints gbc_textF_NumProb = new GridBagConstraints();
		gbc_textF_NumProb.insets = new Insets(0, 0, 5, 0);
		gbc_textF_NumProb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_NumProb.gridx = 3;
		gbc_textF_NumProb.gridy = 1;
		InfoPane.add(textF_NumProb, gbc_textF_NumProb);
		IntFilter.setIntFilter(textF_NumProb, 100, true);
		
		JLabel lblNumberOfDigits = new JLabel("Number of Digits:");
		GridBagConstraints gbc_lblNumberOfDigits = new GridBagConstraints();
		gbc_lblNumberOfDigits.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfDigits.gridx = 1;
		gbc_lblNumberOfDigits.gridy = 2;
		InfoPane.add(lblNumberOfDigits, gbc_lblNumberOfDigits);
		
		textF_NumDig = new JTextField();
		textF_NumDig.setName("num_digits");
		textF_NumDig.setToolTipText("Number of digits for numbers. Please enter a number 1 - 6.");
		GridBagConstraints gbc_textF_NumDig = new GridBagConstraints();
		gbc_textF_NumDig.insets = new Insets(0, 0, 5, 0);
		gbc_textF_NumDig.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_NumDig.gridx = 3;
		gbc_textF_NumDig.gridy = 2;
		InfoPane.add(textF_NumDig, gbc_textF_NumDig);
		IntFilter.setIntFilter(textF_NumDig, 6, true);

		
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
		
		JLabel lblHelp = new JLabel("<HTML>For information about me, please click <U>here</U>.</HTML>");
		
		lblHelp.addMouseListener(new labelMouseListener());
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
		data.put(Controller.NAME_KEY, textF_Name.getText());
		data.put(Controller.NUM_PROBLEMS_KEY, textF_NumProb.getText());
		data.put(Controller.NUM_DIGS_KEY, textF_NumDig.getText());
		data.put(Controller.OPERATOR_KEY, cmbBox_ArithOps.getSelectedItem().toString());
		return data;
	}
	
	public JPanel getContentPane() {
		return MainPane;
	}
}

class labelMouseListener implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, aboutMe);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private final String aboutMe = "<HTML><p>Hello,<br>My name is Marlon Calvo, and I am the developer for this game.<br>"
			+ "This program was written in Java 8 utilizing the Swing framework.<br>"
			+ "I have been working on this project for multiple days now and I wish<br>"
			+ "that you have enjoyed this game.<br>"
			+ "Prior to making this game, I had extensive GUI development experience through my intership.<br>"
			+ "I enjoy the challenge of making a exentsible, stable, and untuitive design for users.<br>"
			+ "But, I find creating algorithms and improving run time for programs to be much more satisfying.<br>"
			+ "So if you have any suggestions as to where I can gain more real-world experience in that domain,<br>"
			+ " I'd greatly appreciate it.<br>"
			+ "I'm always looking to improve! :)<br><br>"
			+ "Thanks,<br>"
			+ "Marlon Calvo (Computer Science Undergrad at UCF)<br></HTML>";
}
