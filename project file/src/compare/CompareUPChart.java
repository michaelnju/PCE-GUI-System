package compare;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/*This class is used the same with the CompareRTChart
 * Only used by different chart.
 * */
public class CompareUPChart extends ChartPanel {
	private static final long serialVersionUID = 1L;
	private static XYSeriesCollection linechartdataset1
	,linechartdataset2;
	public static XYSeries linechartDataSeries1;
	public static XYSeries linechartDataSeries2;
	static ChartFrame chartFrame;
	public CompareUPChart(String xname, String yname) {
		super(createChart(xname, yname));
	}
	private static JFreeChart createChart(String xname, String yname) {
		linechartDataSeries1 = new XYSeries("Data1");
		linechartDataSeries2 =  new XYSeries("Data2");
		linechartdataset1 = new XYSeriesCollection();
		 linechartdataset2= new XYSeriesCollection();
		 linechartdataset1.addSeries(linechartDataSeries1);
		 linechartdataset1.addSeries(linechartDataSeries2);
		JFreeChart jfreechart = ChartFactory.createXYLineChart(null, xname,
				yname, linechartdataset1, PlotOrientation.VERTICAL, true, true,
				false);
		XYPlot xyplot = jfreechart.getXYPlot();
		xyplot.setDataset(1,linechartdataset2);
		xyplot.setRenderer(1,new DefaultXYItemRenderer());
		return jfreechart;
	}
}
