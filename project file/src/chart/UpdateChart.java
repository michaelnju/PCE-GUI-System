package chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/*
 * UpdateChart is a dynamic chart class but not the real-time, 
 * in which the x-axis is the number and the y-axis is the data. 
 * */
public class UpdateChart extends ChartPanel {
	private static final long serialVersionUID = 1L;
	private static XYSeriesCollection linechartdataset;
	public static XYSeries linechartDataSeries;
	public UpdateChart(String xname, String yname) {
		super(createChart(xname, yname));
	}

	private static JFreeChart createChart(String xname, String yname) {
		linechartdataset = new XYSeriesCollection();
		//---Following line is used to set the line's name
		linechartDataSeries = new XYSeries("Update time");
		linechartdataset.addSeries(linechartDataSeries);
		JFreeChart jfreechart = ChartFactory.createXYLineChart(null, xname,
				yname, linechartdataset, PlotOrientation.VERTICAL, false, true,
				false);
		XYPlot xyplot = jfreechart.getXYPlot();
		ValueAxis xaxis = xyplot.getDomainAxis();
		//---The following line  set the auto range of the x-axis.
		xaxis.setFixedAutoRange(50);
		return jfreechart;
	}
}
