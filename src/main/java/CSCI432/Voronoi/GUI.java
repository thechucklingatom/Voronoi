package CSCI432.Voronoi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Created by thechucklingatom on 11/23/2016.
 */
public class GUI extends JFrame {

	MapsAPIAccesor mapsAPIAccesor;
	MapLocation cityFound;
	List<PlacesLocation> placesFound;
	JPanel panel;
	List<String> placeTypes;
	ImagePanel imagePanel;
	List<PlaceNormalized> normalizedPlaces;

	public GUI() {
		super();

		insetPlacesTypes();

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

	private void setButtonsAndText() {
		VoronoiPanel innerDrawPanel = new VoronoiPanel();
		VoronoiPanel voronoiPanel = new VoronoiPanel();
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

			if (foundMapLocations.size() > 1) {
				Object Value = JOptionPane.showInputDialog(null,
						"Which city did you want?",
						"City",
						JOptionPane.QUESTION_MESSAGE,
						null,
						foundMapLocations.toArray(),
						foundMapLocations.toArray()[0]);
				index = foundMapLocations.indexOf(Value);
			} else {
				index = 0;
				JOptionPane.showMessageDialog(null,
						foundMapLocations.get(index),
						"City",
						JOptionPane.INFORMATION_MESSAGE);
			}

			cityFound = foundMapLocations.get(index);

			imagePanel = new ImagePanel(
					mapsAPIAccesor.getMapImage(
							cityFound.getFormatted_address()));
			innerDrawPanel.add(imagePanel, BorderLayout.CENTER);
			//innerDrawPanel.paintComponents(getGraphics());
			imagePanel.paintComponent(innerDrawPanel.getGraphics());

		});
		cityPanel.add(searchButton, BorderLayout.EAST);

		JPanel searchGroup = new JPanel();
		searchGroup.setLayout(new BorderLayout());
		searchGroup.add(cityPanel, BorderLayout.PAGE_START);

		JPanel placesPanel = new JPanel(new BorderLayout());
		JComboBox<String> placeTypesBox = new JComboBox<>(placeTypes.toArray(new String[0]));
		placesPanel.add(placeTypesBox, BorderLayout.CENTER);

		JButton placesSearch = new JButton("Search");
		placesSearch.addActionListener(e -> {
			List<String> typesSelected = new ArrayList<>();
			typesSelected.add(placeTypes.get(placeTypesBox.getSelectedIndex()));
			placesFound = mapsAPIAccesor.getPlaces(cityFound, 500, typesSelected);

			imagePanel.updateImage(mapsAPIAccesor.getMapImageWithExtras(placesFound));
			imagePanel.paintComponent(innerDrawPanel.getGraphics());

			normalizedPlaces = new ArrayList<>();
			double minLat = Double.POSITIVE_INFINITY, maxLat = Double.NEGATIVE_INFINITY;
			double minLng = Double.POSITIVE_INFINITY, maxLng = Double.NEGATIVE_INFINITY;
			for(PlacesLocation location : placesFound){
				if(location.getGeometry().getLocation().getLat() < minLat){
					minLat = location.getGeometry().getLocation().getLat();
				}
				if(location.getGeometry().getLocation().getLat() > maxLat){
					maxLat = location.getGeometry().getLocation().getLat();
				}
				if(location.getGeometry().getLocation().getLng() < minLng){
					minLng = location.getGeometry().getLocation().getLng();
				}
				if(location.getGeometry().getLocation().getLng() > maxLng){
					maxLng = location.getGeometry().getLocation().getLng();
				}
			}

			for(PlacesLocation location : placesFound){
				normalizedPlaces.add(new PlaceNormalized(location, maxLat, maxLng, minLat, minLng));
			}
			voronoiPanel.addPlaces(placesFound);

		});

		placesPanel.add(placesSearch, BorderLayout.EAST);

		searchGroup.add(placesPanel, BorderLayout.PAGE_END);

		panel.add(searchGroup, BorderLayout.PAGE_START);

		voronoiPanel.setPreferredSize(new Dimension(500, 500));

		panel.add(voronoiPanel, BorderLayout.EAST);

		innerDrawPanel.setLayout(new BorderLayout());
		panel.add(innerDrawPanel, BorderLayout.CENTER);

		JButton fortunesAlgorithm = new JButton("Run Fortune's Algorithm");
		fortunesAlgorithm.addActionListener(e -> {
			Voronoi voronoi = new Voronoi();
			List<VPoint> points = new ArrayList<>();

			for(PlaceNormalized places : normalizedPlaces){
				points.add(new VPoint(places.getLng(), places.getLat()));
			}

			List<VEdge> edges = voronoi.getEdges(points, 1, 1);
			voronoiPanel.addEdges(edges);
			System.out.println(edges);
		});
		panel.add(fortunesAlgorithm, BorderLayout.PAGE_END);
	}

	private void insetPlacesTypes() {
		placeTypes = new ArrayList<>();
		placeTypes.add("accounting");
		placeTypes.add("airport");
		placeTypes.add("amusement_park");
		placeTypes.add("aquarium");
		placeTypes.add("art_gallery");
		placeTypes.add("atm");
		placeTypes.add("bakery");
		placeTypes.add("bank");
		placeTypes.add("bar");
		placeTypes.add("beauty_salon");
		placeTypes.add("bicycle_store");
		placeTypes.add("book_store");
		placeTypes.add("bowling_alley");
		placeTypes.add("bus_station");
		placeTypes.add("cafe");
		placeTypes.add("campground");
		placeTypes.add("car_dealer");
		placeTypes.add("car_rental");
		placeTypes.add("car_repair");
		placeTypes.add("car_wash");
		placeTypes.add("casino");
		placeTypes.add("cemetery");
		placeTypes.add("church");
		placeTypes.add("city_hall");
		placeTypes.add("clothing_store");
		placeTypes.add("convenience_store");
		placeTypes.add("courthouse");
		placeTypes.add("dentist");
		placeTypes.add("department_store");
		placeTypes.add("doctor");
		placeTypes.add("electrician");
		placeTypes.add("electronics_store");
		placeTypes.add("embassy)");
		placeTypes.add("food");
	}
}
