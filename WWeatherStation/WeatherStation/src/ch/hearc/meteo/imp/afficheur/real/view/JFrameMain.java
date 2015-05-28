
package ch.hearc.meteo.imp.afficheur.real.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelMain;
import ch.hearc.meteo.imp.afficheur.real.view.mainpanel.JPanelStationList;

public class JFrameMain extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain(Manager manager)
		{
		this.manager = manager;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refresh()
		{
		panelList.refresh();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setSeriesVisible(int serie, boolean visible)
		{
		panelMain.setSeriesVisible(serie, visible);
		}


	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelMain = new JPanelMain(manager);
		panelList = new JPanelStationList(this, manager);

		setLayout(new BorderLayout());

		add(panelList, BorderLayout.WEST);
		add(panelMain, BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("Station M�t�o");
		setSize(1200, 800);
		setLocationRelativeTo(null); 	// frame centrer
		setVisible(true); 				// last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Manager manager;

	// Tools
	private JPanelStationList panelList;
	private JPanelMain panelMain;

	}
