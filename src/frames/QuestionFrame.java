package frames;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Question;
import ft_project.Game;
import utils.IntFilter;
import utils.NumberUtils;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	
	private Question question;
	
	private boolean isComplete = false;

	/**
	 * Create the frame.
	 */
	public QuestionFrame(Game game, Question question) {
		this.question = question;
		isComplete = false;
				
		contentPane = new JPanel();
		contentPane.setAlignmentX(10);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(layout);
		
		lblNum1 = new JLabel(String.valueOf(question.getNum1()));
		lblNum1.setFont(new Font("Monospace", Font.BOLD, 18));
		lblNum1.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(spacer()); 
		contentPane.add(lblNum1);
		
		lblArithOp = new JLabel(String.valueOf(question.getArithOp()));
		lblArithOp.setFont(new Font("Monospace", Font.BOLD, 18));
		lblArithOp.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(spacer());
		contentPane.add(lblArithOp);
		
		lblNum2 = new JLabel(String.valueOf(question.getNum2()));
		lblNum2.setFont(new Font("Monospace", Font.BOLD, 18));
		lblNum2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		contentPane.add(spacer());
		contentPane.add(lblNum2);
		
		textF_Answer = new JTextField();
		textF_Answer.setAlignmentX(Component.CENTER_ALIGNMENT);
		textF_Answer.setHorizontalAlignment(JTextField.CENTER);
		textF_Answer.setMaximumSize(new Dimension(300, 150));
		textF_Answer.setFont(new Font("Monospace", Font.BOLD, 26));
		IntFilter.setIntFilter(textF_Answer, getAnswerBoxSize(), IntFilter.NEGATIVES);
		contentPane.add(spacer()); 
		contentPane.add(textF_Answer);
		textF_Answer.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(spacer()); 
		contentPane.add(btnSubmit);
		
		Instant start = Instant.now();
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Instant end = Instant.now();
				Duration timeElpased = Duration.between(start, end);
				question.setUserAnswer(Long.parseLong(textF_Answer.getText()));
				question.setTime(timeElpased);
				game.isComplete(true);
			}
		});
	}
	
	private int getAnswerBoxSize() {
		final int EXTRA_DIGIT_MIN = 2;
		final int EXTRA_DIGIT_MAX = 4;

		System.out.println(NumberUtils.numberOfDigits(question.getCorrectAnswer()));
		final int a = NumberUtils.numberOfDigits(question.getNum1())
						+ NumberUtils.generateRandomInt(EXTRA_DIGIT_MIN, EXTRA_DIGIT_MAX);
		final int b = NumberUtils.numberOfDigits(question.getCorrectAnswer())
						+ NumberUtils.generateRandomInt(EXTRA_DIGIT_MIN, EXTRA_DIGIT_MAX);
		
		return NumberUtils.maxValue(a, b);
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
	
	private Component spacer() {
		Component box = Box.createVerticalGlue();
		box.setMaximumSize(new Dimension(150, 150));
		return box;
	}
}
