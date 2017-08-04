/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsbuddy_pricecheck;

import java.util.Arrays;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

/**
 *
 * @author bazzb
 */
public class graph {
    public graph(String itemName, double[] ts, long[] buy, long[] sell){   
        XYSeries y1 = new XYSeries("Buy");
        XYSeries y2 = new XYSeries("Sell");
        for (int i = 0; i < buy.length; i++) {
            y1.add(ts[i], buy[i]);
            y2.add(ts[i], sell[i]);
        }    
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(y1);   
        dataset.addSeries(y2);  
        
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
         itemName ,
         "Time" ,
         "Price" ,
         dataset,
         PlotOrientation.VERTICAL ,
         true , true , false);
       // Swing 
        int min1 = (int)Arrays.stream(buy).min().getAsLong();
        int max1 = (int)Arrays.stream(buy).max().getAsLong();
        int min2 = (int)Arrays.stream(sell).min().getAsLong();
        int max2 = (int)Arrays.stream(sell).max().getAsLong();
        xylineChart.getXYPlot().getRangeAxis().setRange(Math.min(min1, min2)*0.95, Math.max(max1, max2)*1.05);
      ChartFrame frame = new ChartFrame("RS Merch graph - " + itemName, xylineChart);
      frame.pack();
      frame.setSize(1400,600);
      frame.setVisible(true);
    }
    
    public graph(String title, long[] x, long[] y, String xlabel, String ylabel){
        XYSeries xs = new XYSeries(xlabel);
       // XYSeries ys = new XYSeries(ylabel);
        for (int i = 0; i < x.length; i++) {
            xs.add(x[i], y[i]);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xs);      
        
        //ChartFactory.
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
         title ,
         xlabel ,
         ylabel ,
         dataset,
         PlotOrientation.VERTICAL ,
         false , true , false);
       // Swing 
        int min = (int)Arrays.stream(y).min().getAsLong();
        int max = (int)Arrays.stream(y).max().getAsLong();
        xylineChart.getXYPlot().getRangeAxis().setRange(min*0.95, max*1.05);
      ChartFrame frame = new ChartFrame("RS Merch graph - " + title, xylineChart);
      frame.pack();
      frame.setVisible(true);
    }
    
   public void createGraph(){
       
   }
   
   public graph(String chartTitle )
   {
    //  super(applicationTitle);

      
//      //ChartPanel chartPanel = new ChartPanel( xylineChart );
//     // chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//     // final XYPlot plot = xylineChart.getXYPlot( );
// XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
//      renderer.setSeriesPaint( 0 , Color.RED );
//      renderer.setSeriesPaint( 1 , Color.GREEN );
//      renderer.setSeriesPaint( 2 , Color.YELLOW );
//      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
//      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
//      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
//      plot.setRenderer( renderer ); 
    //  setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( )
   {
      final XYSeries firefox = new XYSeries( "Firefox" );          
      firefox.add( 1.0 , 1.0 );          
      firefox.add( 2.0 , 4.0 );          
      firefox.add( 3.0 , 3.0 );          
      final XYSeries chrome = new XYSeries( "Chrome" );          
      chrome.add( 1.0 , 4.0 );          
      chrome.add( 2.0 , 5.0 );          
      chrome.add( 3.0 , 6.0 );          
      final XYSeries iexplorer = new XYSeries( "InternetExplorer" );          
      iexplorer.add( 3.0 , 4.0 );          
      iexplorer.add( 4.0 , 5.0 );          
      iexplorer.add( 5.0 , 4.0 );          
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( firefox );          
      dataset.addSeries( chrome );          
      dataset.addSeries( iexplorer );
      return dataset;
   }

    
    
}
