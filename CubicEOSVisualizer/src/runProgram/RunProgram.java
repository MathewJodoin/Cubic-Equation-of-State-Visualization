package runProgram;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;

import net.ericaro.surfaceplotter.JSurfacePanel;
import net.ericaro.surfaceplotter.Mapper;
import net.ericaro.surfaceplotter.surface.AbstractSurfaceModel;
import net.ericaro.surfaceplotter.surface.ArraySurfaceModel;
import net.ericaro.surfaceplotter.surface.SurfaceVertex;
import srkEquation.SRKParamPanel;
import srkEquation.updateSavePanel;

public class RunProgram {
	
	public static ReadCritPropData readData = new ReadCritPropData();
	static srkEquation.updateSavePanel upSvPanel = new updateSavePanel();
	public static JSurfacePanel jsp = new JSurfacePanel();
	public static ArraySurfaceModel sm = new ArraySurfaceModel();

	public static JSurfacePanel jsp2 = new JSurfacePanel();
	public static ArraySurfaceModel sm2 = new ArraySurfaceModel();
	public static JPanel plotPanel = new JPanel();

	public static void testSomething() {
		
		//Setting up JFrame with labels, size and close type
		jsp.setTitleText("Soave Redlich-Kwong Equation of State");
		JFrame jf = new JFrame("3-Component Phase Stability Comparison");
		jf.setSize(1600, 700);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creates Critical Property Arrays and uses them to populate the parameter panel "params"
		ReadCritPropData.ReadData();
		SRKParamPanel params = new SRKParamPanel();
		
		jsp2.setTitleText("Peng Robinson Equation of State");
		plotPanel.setLayout(new GridLayout(1,2));
		jf.getContentPane().add(plotPanel, BorderLayout.CENTER);
		plotPanel.add(jsp);
		plotPanel.add(jsp2);
		jsp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jsp2.setModel(sm2);
		
		//jf.getContentPane().add(jsp, BorderLayout.CENTER);
		jsp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jf.getContentPane().add(params, BorderLayout.WEST);
		jf.getContentPane().add(upSvPanel, BorderLayout.SOUTH);
		jf.setVisible(true);
		jsp.setModel(sm);
		// sm.doRotate();
		// canvas.doPrint();
		// sm.doCompute();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new RunProgram().testSomething();
			}
		});

	}

}
