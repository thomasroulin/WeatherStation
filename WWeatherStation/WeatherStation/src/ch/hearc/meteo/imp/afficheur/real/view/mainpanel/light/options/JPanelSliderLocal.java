
package ch.hearc.meteo.imp.afficheur.real.view.mainpanel.light.options;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.moo.ManagerLocal;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelSliderLocal extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSliderLocal(ManagerLocal manager)
		{
		this.manager = manager;

		geometry();
		apparence();
		control();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void updatePortCom(String portCom)
		{
		this.portCom = portCom;
		this.meteoServiceOptions = manager.getMeteoServiceOptions(portCom);
		}

	public void refresh()
		{
		int valueAltitude = (int)meteoServiceOptions.getAltitudeDT();
		int valuePressure = (int)meteoServiceOptions.getPressionDT();
		int valueTemperature = (int)meteoServiceOptions.getTemperatureDT();

		updateSlider(sliderAltitude, valueAltitude);
		updateSlider(sliderPressure, valuePressure);
		updateSlider(sliderTemperature, valueTemperature);

		setTitleBorders(valueAltitude, valuePressure, valueTemperature);
		}

	public void updateMeteoServiceOption() {
		refresh();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		int min = 10;
		int max = 10000;
		int valueAltitude;
		int valuePressure;
		int valueTemperature;

		try
			{
			valueAltitude = (int)manager.getMeteoServiceOptions(portCom).getAltitudeDT();
			valuePressure = (int)manager.getMeteoServiceOptions(portCom).getPressionDT();
			valueTemperature = (int)manager.getMeteoServiceOptions(portCom).getTemperatureDT();
			}
		catch (Exception e)
			{
			valueAltitude = (min + max) / 2;
			valuePressure = (min + max) / 2;
			valueTemperature = (min + max) / 2;
			e.printStackTrace();
			}

		buttonSetOptions = new JButton("Set");

		sliderAltitude = new JSlider(min, max, valueAltitude);
		sliderPressure = new JSlider(min, max, valuePressure);
		sliderTemperature = new JSlider(min, max, valueTemperature);

		borderAltitude = BorderFactory.createTitledBorder("");
		borderPressure = BorderFactory.createTitledBorder("");
		borderTemperature = BorderFactory.createTitledBorder("");

		setTitleBorders(valueAltitude, valuePressure, valueTemperature);

		sliderAltitude.setBorder(borderAltitude);
		sliderPressure.setBorder(borderPressure);
		sliderTemperature.setBorder(borderTemperature);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(sliderAltitude, c);

		c.gridy = 1;
		add(sliderPressure, c);

		c.gridy = 2;
		add(sliderTemperature, c);

		c.gridy = 3;
		add(buttonSetOptions, c);
		}

	private void apparence()
		{
		sliderTemperature.setOrientation(SwingConstants.HORIZONTAL);
		}

	private void control()
		{
		sliderAltitude.addChangeListener(createChangeListener());
		sliderPressure.addChangeListener(createChangeListener());
		sliderTemperature.addChangeListener(createChangeListener());

		buttonSetOptions.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					meteoServiceOptions.setAltitudeDT(sliderAltitude.getValue());
					meteoServiceOptions.setPressionDT(sliderPressure.getValue());
					meteoServiceOptions.setTemperatureDT(sliderTemperature.getValue());

					try
						{
						manager.getCentralRemote().updateMeteoServiceOptions(meteoServiceOptions);
						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}
					}
			});
		}

	private ChangeListener createChangeListener()
		{
		return new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent e)
					{
					int valueAltitude = sliderAltitude.getValue();
					int valuePressure = sliderPressure.getValue();
					int valueTemperature = sliderTemperature.getValue();
					setTitleBorders(valueAltitude, valuePressure, valueTemperature);
					}
			};
		}

	private void updateSlider(JSlider slider, int value)
		{
		if (value > slider.getMaximum())
			{
			slider.setMaximum(value + 50);
			}
		slider.setValue(value);
		}

	private void setTitleBorders(int valueAltitude, int valuePressure, int valueTemperature)
		{
		borderAltitude.setTitle("\u0394 Altitude =" + valueAltitude + " (ms)");
		borderPressure.setTitle("\u0394 Pressure =" + valuePressure + " (ms)");
		borderTemperature.setTitle("\u0394 Temperature =" + valueTemperature + " (ms)");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private ManagerLocal manager;
	private String portCom;
	private MeteoServiceOptions meteoServiceOptions;

	// Tools
	private JSlider sliderAltitude;
	private JSlider sliderPressure;
	private JSlider sliderTemperature;
	private TitledBorder borderAltitude;
	private TitledBorder borderPressure;
	private TitledBorder borderTemperature;
	private JButton buttonSetOptions;

	}
