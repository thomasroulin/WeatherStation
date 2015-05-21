
package ch.coursjava.meteo.affichage.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UseFrameMain
	{

	public static void main(final String[] args)
		{
		try
			{
	        UIManager.setLookAndFeel(
	                UIManager.getSystemLookAndFeelClassName());
			}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		new JFrameMain();
		}
	}
