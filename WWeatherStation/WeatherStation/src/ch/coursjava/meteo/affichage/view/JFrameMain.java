
package ch.coursjava.meteo.affichage.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.coursjava.meteo.affichage.view.mainpanel.JPanelMain;
import ch.coursjava.meteo.affichage.view.mainpanel.JPanelStationList;

public class JFrameMain extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		buttonSim = new JButton("start");
		panelMain = new JPanelMain();
		panelList = new JPanelStationList();

		setLayout(new BorderLayout());

		JPanel panelControl = new JPanel();
		panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.PAGE_AXIS));

		panelControl.add(buttonSim);
		panelControl.setBackground(Color.DARK_GRAY);

		add(panelList, BorderLayout.WEST);
		add(panelMain, BorderLayout.CENTER);
		add(panelControl, BorderLayout.EAST);
		}

	private void control()
		{

		buttonSim.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					panelMain.startSim();
					}
			});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setTitle("Station M�t�o");
		setSize(800, 600);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelStationList panelList;
	private JPanelMain panelMain;
	private JButton buttonSim;


	}
