package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Session;
import ft_project.Controller;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.awt.event.ActionEvent;

public class ResultsFrame extends Frame {

	private static Frame frame;
	
	private JPanel contentPane;
	private Session session;

	/**
	 * Create the frame.
	 */
	public static Frame createResultsFrame(Session session) {
		frame = new ResultsFrame(session);
		return frame;
	}
	
	private ResultsFrame(Session session) {
		this.session = session;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("Results");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 2;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblNewLabel = new JLabel("Player Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblPlayerName = new JLabel(session.getUser().getName());
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName.gridx = 2;
		gbc_lblPlayerName.gridy = 1;
		contentPane.add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblNewLabel_1 = new JLabel("Num Problem:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNumProblem = new JLabel(String.valueOf(session.getNumOfProblems()));
		GridBagConstraints gbc_lblNumProblem = new GridBagConstraints();
		gbc_lblNumProblem.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumProblem.gridx = 2;
		gbc_lblNumProblem.gridy = 2;
		contentPane.add(lblNumProblem, gbc_lblNumProblem);
		
		JLabel lblHeading = new JLabel("Num Correct:");
		GridBagConstraints gbc_lblHeading = new GridBagConstraints();
		gbc_lblHeading.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeading.gridx = 1;
		gbc_lblHeading.gridy = 3;
		contentPane.add(lblHeading, gbc_lblHeading);
		
		JLabel lblNumCorrect = new JLabel(String.valueOf(session.getNumCorrect()));
		GridBagConstraints gbc_lblNumCorrect = new GridBagConstraints();
		gbc_lblNumCorrect.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumCorrect.gridx = 2;
		gbc_lblNumCorrect.gridy = 3;
		contentPane.add(lblNumCorrect, gbc_lblNumCorrect);
		
		JLabel lblNewLabel_2 = new JLabel("Total Time:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblTotalTime = new JLabel(formatDuration(session.getTotalTime()));
		GridBagConstraints gbc_lblTotalTime = new GridBagConstraints();
		gbc_lblTotalTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalTime.gridx = 2;
		gbc_lblTotalTime.gridy = 4;
		contentPane.add(lblTotalTime, gbc_lblTotalTime);
		
		JLabel lblNewLabel_3 = new JLabel("Avg Time:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblAvgTime = new JLabel(formatDuration(session.getAvgTime()));
		GridBagConstraints gbc_lblAvgTime = new GridBagConstraints();
		gbc_lblAvgTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvgTime.gridx = 2;
		gbc_lblAvgTime.gridy = 5;
		contentPane.add(lblAvgTime, gbc_lblAvgTime);
		
		JLabel lblNewLabel_5 = new JLabel("Score:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 6;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblScore = new JLabel(String.valueOf(session.getScore()));
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblScore.gridx = 2;
		gbc_lblScore.gridy = 6;
		contentPane.add(lblScore, gbc_lblScore);
		
		JLabel lblNewLabel_4 = new JLabel("Perc Correct: ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 7;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblPercCorrect = new JLabel(String.valueOf((Math.floor(session.getCorrectPerc()*1e4)/1e4)*100) + "%");
		GridBagConstraints gbc_lblPercCorrect = new GridBagConstraints();
		gbc_lblPercCorrect.insets = new Insets(0, 0, 5, 5);
		gbc_lblPercCorrect.gridx = 2;
		gbc_lblPercCorrect.gridy = 7;
		contentPane.add(lblPercCorrect, gbc_lblPercCorrect);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.insets = new Insets(0, 0, 0, 5);
		gbc_btnFinish.gridx = 1;
		gbc_btnFinish.gridy = 9;
		contentPane.add(btnFinish, gbc_btnFinish);
		
		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Controller.frameCompleted(frame);
			}
		});
		GridBagConstraints gbc_btnHistory = new GridBagConstraints();
		gbc_btnHistory.gridx = 3;
		gbc_btnHistory.gridy = 9;
		contentPane.add(btnHistory, gbc_btnHistory);
	}
	
	private String formatDuration(Duration duration) {
	    long seconds = duration.getSeconds();
	    long absSeconds = Math.abs(seconds);
	    String positive = String.format(
	        "hours %d: minutes %02d: seconds %02d",
	        absSeconds / 3600,
	        (absSeconds % 3600) / 60,
	        absSeconds % 60);
	    return seconds < 0 ? "-" + positive : positive;
	}
	
	public boolean isComplete() {
		return true;
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	public  HashMap<Object, Object> getSessionData() {
		HashMap<Object, Object> data = new HashMap<>();
		data.put("user", session.getUser());
		return data;
	}

}
