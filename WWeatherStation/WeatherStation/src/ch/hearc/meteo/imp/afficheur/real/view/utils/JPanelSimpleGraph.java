
package ch.hearc.meteo.imp.afficheur.real.view.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeriesCollection;

public class JPanelSimpleGraph extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSimpleGraph(String graphTitle, TimeSeriesCollection collection)
		{
		this.graphTitle = graphTitle;
		this.collection = collection;

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

	public void setSeriesVisible(int serie, boolean visible) {
		plot.getRenderer().setSeriesVisible(serie, visible);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// Chart
		JFreeChart chart = createChart();
		chartPanel = new ChartPanel(chart);

		// Layout
		setLayout(new BorderLayout());

		// Add
		add(chartPanel, BorderLayout.CENTER);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		//rien
		}

	private JFreeChart createChart()
		{
		// create the chart...
		final JFreeChart chart = ChartFactory.createTimeSeriesChart(graphTitle, // chart title
				"Timestamp", // x axis label
				graphTitle, // y axis label
				collection, // data
				true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		plot = chart.getXYPlot();

		plot.setBackgroundPaint(Color.darkGray);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.lightGray);

		// change the auto tick unit selection to integer units only...
		ValueAxis rangeAxis = plot.getRangeAxis();
		DateAxis dateAxis = (DateAxis) plot.getDomainAxis();

		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		dateAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
		dateAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);

		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private String graphTitle;
	private TimeSeriesCollection collection;

	// Tools
	private ChartPanel chartPanel;
	private XYPlot plot;

	}
