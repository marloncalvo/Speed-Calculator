package frames;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import data.Question;
import data.Session;
import data.User;
import ft_project.Controller;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserHistroyFrame extends Frame {
	public static final int MAIN = 1;
	public static final int LEADERBOARD = 2;

	private static Frame frame;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private int toLoad = MAIN;
	
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
		
		JButton btnNewButton = new JButton("Main Screen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toLoad = MAIN;
				Controller.frameCompleted(frame);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Leaderboards");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toLoad = LEADERBOARD;
				Controller.frameCompleted(frame);
			}
		});
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
			model.addRow(new Object[] {(model.getRowCount()+1), q.getNum1(), 
					q.getArithOp(), q.getNum2(), q.getCorrectAnswer(), 
					q.getUserAnswer(), (q.getTime().toString().replace("PT", "").replace("S", ""))});
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
		HashMap<Object, Object> data = new HashMap<>();
		data.put("toLoad", toLoad);
		
		return data;
	}
}
