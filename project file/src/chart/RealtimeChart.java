package chart;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
/*
 * RealtimeChart is a real-time and dynamic chart class, 
 * in which the x-axis is the dynamic time that can be 
 * set depend on your prefer and the y-axis is the data. 
 * */
public class RealtimeChart extends ChartPanel {
	private static final long serialVersionUID = 1L;
	public static TimeSeries timeSeries; // this is used to create a line
	public RealtimeChart(String chartContent, String title, String yaxisName) {
		super(createChart(chartContent, title, yaxisName));
	}
	private static JFreeChart createChart(String chartContent, String title,
			String yaxisName){
		timeSeries = new TimeSeries(chartContent);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(
				timeSeries); 
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,
				"Time/sec", yaxisName, timeseriescollection, true, true, false);
		/*"title" used to set the title of the chart. 
		 * "Time/sec" is the x-axis's name
		 * "yaxisName is the y-axis's name
		 *" timeseriescollection" is the line shown in the chart
		 * */
		XYPlot xyplot = jfreechart.getXYPlot();
		DateAxis xaxis = (DateAxis) xyplot.getDomainAxis();
		//---The following 2 lines are used to set the x-axis data¡¯s format
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		xaxis.setDateFormatOverride(sdf);
		//---The following line  set the auto range of the x-axis. 
		xaxis.setFixedAutoRange(30000D);
		return jfreechart;
	}
}
