package frames;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Question;
import ft_project.Game;
import ft_project.GameController;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuestionFrame extends Frame {
		
	/**
	 * 
	 */
	private JLabel lblNum1;
	private JLabel lblNum2;
	private JLabel lblArithOp;
	private JButton btnSubmit;
	private JPanel contentPane;
	private JTextField textF_Answer;
	
	private boolean isComplete = false;

	/**
	 * Create the frame.
	 */
	public QuestionFrame(Question question) {
		isComplete = false;
		
		Game game = GameController.getGameController();
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNum1 = new JLabel(String.valueOf(question.getNum1()));
		GridBagConstraints gbc_lblNum1 = new GridBagConstraints();
		gbc_lblNum1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNum1.gridx = 0;
		gbc_lblNum1.gridy = 0;
		contentPane.add(lblNum1, gbc_lblNum1);
		
		lblArithOp = new JLabel(String.valueOf(question.getArithOp()));
		GridBagConstraints gbc_lblArithOp = new GridBagConstraints();
		gbc_lblArithOp.insets = new Insets(0, 0, 5, 0);
		gbc_lblArithOp.gridx = 0;
		gbc_lblArithOp.gridy = 1;
		contentPane.add(lblArithOp, gbc_lblArithOp);
		
		lblNum2 = new JLabel(String.valueOf(question.getNum2()));
		GridBagConstraints gbc_lblNum2 = new GridBagConstraints();
		gbc_lblNum2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNum2.gridx = 0;
		gbc_lblNum2.gridy = 2;
		contentPane.add(lblNum2, gbc_lblNum2);
		
		textF_Answer = new JTextField();
		GridBagConstraints gbc_textF_Answer = new GridBagConstraints();
		gbc_textF_Answer.insets = new Insets(0, 0, 5, 0);
		gbc_textF_Answer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textF_Answer.gridx = 0;
		gbc_textF_Answer.gridy = 3;
		contentPane.add(textF_Answer, gbc_textF_Answer);
		textF_Answer.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		
		Instant start = Instant.now();
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Instant end = Instant.now();
				Duration timeElpased = Duration.between(start, end);
				question.setUserAnswer(Double.parseDouble(textF_Answer.getText()));
				question.setTime(timeElpased);
				System.out.printf("timeel: " + timeElpased.toString());
				game.isComplete(true);
			}
		});
		
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 4;
		contentPane.add(btnSubmit, gbc_btnSubmit);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public HashMap<Object, Object> getSessionData() {
		// TODO Auto-generated method stub
		return null;
	}
}
