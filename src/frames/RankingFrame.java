package frames;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Ranking;
import ft_project.Controller;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RankingFrame extends Frame {

	private JPanel contentPane;
	private static Frame frame;
	
	/**
	 * Launch the application.
	 */
	public static Frame createRankingFrame() {
		frame = new RankingFrame();
		return frame;
	}

	/**
	 * Create the frame.
	 */
	public RankingFrame() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Leaderboard");
		panel_1.add(lblNewLabel);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{};
		gbl_panel.rowWeights = new double[]{};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("Main Screen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.frameCompleted(frame);
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(btnNewButton);
		
		
		Ranking rank = Ranking.getRankings();
		String[] records = rank.toString().split("\n");
		int i = 0;
		for(String record : records) {
			String[] a = record.split("\\s+");
			JLabel lblNewLabel_1 = new JLabel(a[0]);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = i;
			panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel(a[1]);
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = i;
			panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			i++;
		}
		
		
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
