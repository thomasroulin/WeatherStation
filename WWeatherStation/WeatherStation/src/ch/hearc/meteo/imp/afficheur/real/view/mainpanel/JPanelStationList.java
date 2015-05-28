
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.meteo.imp.afficheur.real.customs.QKTCheckBox;
import ch.hearc.meteo.imp.afficheur.real.customs.QKTScrollBarUI;
import ch.hearc.meteo.imp.afficheur.real.moo.Manager;
import ch.hearc.meteo.imp.afficheur.real.moo.Station;
import ch.hearc.meteo.imp.afficheur.real.view.JFrameMain;

public class JPanelStationList extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationList(JFrameMain parent, Manager manager)
		{
		this.parent = parent;
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
		Collection<Station> stations = manager.getStationList();

		panelList.removeAll();

		for(Station s: stations)
			{
			QKTCheckBox cb = new QKTCheckBox(s.getName());
			cb.setSelected(s.isVisible());
			addCheckBoxListener(s, cb);
			panelList.add(cb);
			}
		this.updateUI();
		}

	private void addCheckBoxListener(Station s, QKTCheckBox cb)
		{
		cb.addItemListener(new ItemListener()
			{

				@Override
				public void itemStateChanged(ItemEvent event)
					{
					parent.setSeriesVisible(0, !s.isVisible());
					s.setVisible(!s.isVisible());
					}
			});
		}

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

		panelList = new JPanel();
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane(panelList);
		scrollPane.setPreferredSize(new Dimension(200, 400));
				scrollPane.getVerticalScrollBar().setUI(new QKTScrollBarUI(QKTScrollBarUI.VERTICAL));
				scrollPane.getHorizontalScrollBar().setUI(new QKTScrollBarUI(QKTScrollBarUI.HORIZONTAL));

		FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
		setLayout(flowlayout);

		add(scrollPane);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JFrameMain parent;
	private Manager manager;

	// Tools
	private JPanel panelList;

	}
