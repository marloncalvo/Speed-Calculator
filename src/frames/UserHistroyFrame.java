package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import data.Question;
import data.Session;
import data.User;
import ft_project.FrameController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserHistroyFrame extends Frame {

	private static Frame frame;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	
	public static Frame createUserHistoryFrame(User user) {
		frame = new UserHistroyFrame(user);
		return frame;
	}
	
	/**
	 * Create the frame.
	 */
	private UserHistroyFrame(User user) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Questions");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JSeparator separator = new JSeparator();
		separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(Integer.MAX_VALUE , 250));
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(100, 100));
		panel_1.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Quest. #", "Number 1", "Operation", "Number 2", "Corr. Ans.", "Usr. Ans.", "Time (s)"
				}
			);
		
		table.setModel(model);
		
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.PAGE_AXIS));
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(300, 35));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session session = user.getSession(comboBox.getSelectedItem().toString());
				addQuestionsToTable(session.getQuestions());
			}
		});
		DefaultComboBoxModel<String> mdl = new DefaultComboBoxModel<String>();
		for(Session s : user.getSessions()) {
			mdl.addElement(s.toString());
		}
		comboBox.setModel(mdl);
		comboBox.setSelectedIndex(0);
		addQuestionsToTable(user.getSessions().getFirst().getQuestions());
		
		panel_3.add(comboBox);
		panel_2.add(separator);
		panel_2.add(panel_1);
		panel_2.add(panel);
	}
	
	private void addQuestionsToTable(ArrayList<Question> questions) {
		model.setRowCount(0);
		for(Question q : questions) {
			model.addRow(new Object[] {(model.getRowCount()+1), q.getNum1(), q.getArithOp(), q.getNum2(), q.getCorrectAnswer(), q.getUserAnswer(), q.getTime()});
		}
		
		table.setModel(model);
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JPanel getContentPane() {
		// TODO Auto-generated method stub
		return contentPane;
	}

	@Override
	public HashMap<Object, Object> getSessionData() {
		// TODO Auto-generated method stub
		return null;
	}
}
