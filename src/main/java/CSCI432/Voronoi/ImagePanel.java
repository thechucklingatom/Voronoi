package CSCI432.Voronoi;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Created by thechucklingatom on 11/23/2016.
 */
public class ImagePanel extends JPanel {

	private Image image;

	public ImagePanel(Image image){
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		graphics.drawImage(image, 20, 100, this);
	}
}
