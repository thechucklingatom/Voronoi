package CSCI432.Voronoi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Created by thechucklingatom on 11/23/2016.
 */
public class GUI extends JFrame{

	MapsAPIAccesor mapsAPIAccesor;
	MapLocation cityFound;
	List<PlacesLocation> placesFound;
	JPanel panel;

	public GUI(){
		super();
		panel = new JPanel();
		setPreferredSize(new Dimension(1024, 768));
		setMinimumSize(new Dimension(1024, 768));
		setTitle("Voronoi app");

		setLayout(new BorderLayout());

		panel.setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(panel, BorderLayout.CENTER);

		setButtonsAndText();

		mapsAPIAccesor = new MapsAPIAccesor();
	}

	private void setButtonsAndText(){
		JPanel innerDrawPanel = new JPanel();
		JTextField cityTextBox = new JTextField();
		cityTextBox.setText("What city are you looking for?");
		cityTextBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cityTextBox.setText("");
			}
		});

		JPanel cityPanel = new JPanel();
		cityPanel.setLayout(new BorderLayout());
		cityPanel.add(cityTextBox, BorderLayout.CENTER);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(e -> {
			List<MapLocation> foundMapLocations = mapsAPIAccesor.getLocation(cityTextBox.getText());

			int index;

			if(foundMapLocations.size() > 1){
				Object Value = JOptionPane.showInputDialog(null,
						"Which city did you want?",
						"City",
						JOptionPane.QUESTION_MESSAGE,
						null,
						foundMapLocations.toArray(),
						foundMapLocations.toArray()[0]);
				index = foundMapLocations.indexOf(Value);
			}else{
				index = 0;
				JOptionPane.showMessageDialog(null,
						foundMapLocations.get(index),
						"City",
						JOptionPane.INFORMATION_MESSAGE);
			}

			MapLocation mapLocation = foundMapLocations.get(index);

			ImagePanel mapImage = new ImagePanel(
					mapsAPIAccesor.getMapImage(
							mapLocation.getFormatted_address()));
			innerDrawPanel.add(mapImage, BorderLayout.CENTER);
			//innerDrawPanel.paintComponents(getGraphics());
			mapImage.paintComponent(getGraphics());

		});
		cityPanel.add(searchButton, BorderLayout.EAST);

		panel.add(cityPanel, BorderLayout.PAGE_START);

		innerDrawPanel.setLayout(new BorderLayout());
		panel.add(innerDrawPanel, BorderLayout.CENTER);
	}
}
