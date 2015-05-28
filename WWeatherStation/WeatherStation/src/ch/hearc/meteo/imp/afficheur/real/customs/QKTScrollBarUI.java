
package ch.hearc.meteo.imp.afficheur.real.customs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class QKTScrollBarUI extends BasicScrollBarUI
	{

	public QKTScrollBarUI(int orientation)
		{
		this.orientation = orientation;
		}

	protected JButton createZeroButton()
		{
		JButton button = new JButton();
		Dimension zeroDim = new Dimension(0, 0);
		button.setPreferredSize(zeroDim);
		button.setMinimumSize(zeroDim);
		button.setMaximumSize(zeroDim);
		return button;
		}

	@Override
	protected JButton createDecreaseButton(int orientation)
		{
		return createZeroButton();
		}

	@Override
	protected JButton createIncreaseButton(int orientation)
		{
		return createZeroButton();
		}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
		{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.LIGHT_GRAY);

		int x = trackBounds.x;
		int y = trackBounds.y;
		int w = trackBounds.width;
		int h = trackBounds.height;

		if (orientation == VERTICAL)
			{
			x += 10;
			w -= 10;
			}
		else if (orientation == HORIZONTAL)
			{
			y += 10;
			h -= 10;
			}

		g2d.fillRect(x, y, w, h);
		}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
		{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.DARK_GRAY);

		int x = thumbBounds.x;
		int y = thumbBounds.y;
		int w = thumbBounds.width;
		int h = thumbBounds.height;

		if (orientation == VERTICAL)
			{
			x += 10;
			w -= 10;
			}
		else if (orientation == HORIZONTAL)
			{
			y += 10;
			h -= 10;
			}

		g2d.fillRect(x, y, w, h);
		}



	private int orientation;

	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	}
