package srkEquation;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import srkEquation.SRKParamPanel;

import net.ericaro.surfaceplotter.surface.SurfaceUtils;
import pengRobinsonEquation.GeneratePRArray;
import runProgram.ReadCritPropData;

public class updateSavePanel extends JPanel implements ActionListener
{
	JButton updateGraph = new JButton("Update Graph");
	JButton saveGraph = new JButton("Save");
	
	int selectedIndex1;
	int selectedIndex2;
	int selectedIndex3;
	
	public updateSavePanel()
	{
		add(updateGraph);
		add(saveGraph);
		saveGraph.addActionListener(this);
		updateGraph.addActionListener(this);
		setBackground(Color.WHITE);
	}

	public void actionPerformed(ActionEvent a1)
	{
		if(a1.getSource().equals(saveGraph))
		{
			JFileChooser jfc = new JFileChooser();
			try {
				if (jfc.showSaveDialog(runProgram.RunProgram.jsp) == JFileChooser.APPROVE_OPTION )
					SurfaceUtils.doExportPNG(runProgram.RunProgram.jsp.getSurface(), jfc.getSelectedFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Before generating new surface plot, this section refreshes the parameters 
		//depending on which chemical components are chosen
		else if(a1.getSource().equals(updateGraph))
		{
			SRKParamPanel.setTemp(Float.parseFloat(SRKParamPanel.tempTextField.getText())); 
			SRKParamPanel.setPress(Float.parseFloat(SRKParamPanel.pressTextField.getText()));
			
			if(SRKParamPanel.lowResButton.isSelected())
			{
				SRKParamPanel.stepSize = 0.01;
			}
			else
			{
				SRKParamPanel.stepSize = 0.001;
			}
			
			selectedIndex1 = SRKParamPanel.comp1Box.getSelectedIndex();
			SRKParamPanel.setTc1(ReadCritPropData.tcArray.get(selectedIndex1));
			SRKParamPanel.setPc1(ReadCritPropData.pcArray.get(selectedIndex1));
			//SRKParamPanel.zc1 = SRKParamPanel.zcArray[selectedIndex1];
			SRKParamPanel.setW1(ReadCritPropData.wArray.get(selectedIndex1));
			
			selectedIndex2 = SRKParamPanel.comp2Box.getSelectedIndex();
			SRKParamPanel.setTc2(ReadCritPropData.tcArray.get(selectedIndex2));
			SRKParamPanel.setPc2(ReadCritPropData.pcArray.get(selectedIndex2));
			//SRKParamPanel.zc2 = SRKParamPanel.zcArray[selectedIndex2];
			SRKParamPanel.setW2(ReadCritPropData.wArray.get(selectedIndex2));
			
			selectedIndex3 = SRKParamPanel.comp3Box.getSelectedIndex();
			SRKParamPanel.setTc3(ReadCritPropData.tcArray.get(selectedIndex3));
			SRKParamPanel.setPc3(ReadCritPropData.pcArray.get(selectedIndex3));
			//SRKParamPanel.zc3 = SRKParamPanel.zcArray[selectedIndex3];
			SRKParamPanel.setW3(ReadCritPropData.wArray.get(selectedIndex3));		
			
			/** Creates two surfaces using data from the array.
			 * 
			 * @param xmin lower bound of x values
			 * @param xmax upper bound of x values
			 * @param ymin lower bound of y values
			 * @param ymax upper bound of y values
			 * @param size number of items in each dimensions (ie z1 = float[size][size] )
			 * @param z1 value matrix (null supported)
			 * @param z2 secondary function value matrix (null supported)
			 */
			//public void setValues(float xmin, float xmax, float ymin, float ymax, int size, float[][] z1, float[][] z2)
			
			if(Double.isNaN(SRKParamPanel.getTc1()) || Double.isNaN(SRKParamPanel.getTc2()) || Double.isNaN(SRKParamPanel.getTc3()))
			{
				JOptionPane.showMessageDialog(null, "Please Select 3 Chemical Components", "Non-Chemical Detected", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
			GenerateSRKArray3.GenerateSRKArray3();
			runProgram.RunProgram.sm.setValues(0, 1, 0, 1, GenerateSRKArray3.liquidComp.length, GenerateSRKArray3.liquidComp, GenerateSRKArray3.vaporComp);
			runProgram.RunProgram.sm.autoScale();
			runProgram.RunProgram.jsp.invalidate();
			runProgram.RunProgram.jsp.revalidate();
			runProgram.RunProgram.jsp.repaint();
			
			GeneratePRArray.GeneratePRArray();
			runProgram.RunProgram.sm2.setValues(0, 1, 0, 1, GeneratePRArray.liquidComp.length, GeneratePRArray.liquidComp, GeneratePRArray.vaporComp);
			runProgram.RunProgram.sm2.autoScale();
			runProgram.RunProgram.jsp2.invalidate();
			runProgram.RunProgram.jsp2.revalidate();
			runProgram.RunProgram.jsp2.repaint();
			}
		}
		
	}
}
