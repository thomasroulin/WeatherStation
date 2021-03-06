
package ch.hearc.meteo.imp.afficheur.real.moo;

import org.jfree.data.time.TimeSeries;
import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.spec.com.meteo.listener.event.Sources;

public class Station
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Station(Sources source)
		{
		this(source, true);
		}

	public Station(Sources source, boolean visible)
		{
		this.source = source;
		this.visible = visible;

		//TODO GET NEW WAYPOINTS WITH COUNTER_STATIONS
		this.geoposition = new GeoPosition(50, 7, 0, 8, 41, 0);
		COUNTER_STATIONS ++;

		String name = source.getPort();
		seriesAltitude = new TimeSeries(name + "'s altitude");
		seriesPression = new TimeSeries(name + "'s pressure");
		seriesTemperature = new TimeSeries(name + "'s temperature");
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setVisible(boolean visible)
		{
		this.visible = visible;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public GeoPosition getGeoposition()
		{
		return this.geoposition;
		}

	public String getName()
		{
		return this.source.getPort();
		}

	public TimeSeries getSeries(Sensor sensor)
		{
		switch(sensor)
			{
			case ALTITUDE:
				return getSeriesAltitude();
			case TEMPERATURE:
				return getSeriesTemperature();
			case PRESSURE:
				return getSeriesPression();
			}
		return null;
		}

	public TimeSeries getSeriesAltitude()
		{
		return this.seriesAltitude;
		}

	public TimeSeries getSeriesPression()
		{
		return this.seriesPression;
		}

	public TimeSeries getSeriesTemperature()
		{
		return this.seriesTemperature;
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isVisible()
		{
		return this.visible;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Sources source;
	private boolean visible;
	private GeoPosition geoposition;

	private TimeSeries seriesAltitude;
	private TimeSeries seriesPression;
	private TimeSeries seriesTemperature;

	private static int COUNTER_STATIONS = 0;

	}
