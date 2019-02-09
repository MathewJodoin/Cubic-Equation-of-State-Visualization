package srkEquation;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import runProgram.ReadCritPropData;

public class SRKParamPanel extends JPanel
{
	
	private JLabel tempLabel = new JLabel("Temperature (K)");
	static JTextField tempTextField = new JTextField();
	private JLabel pressLabel = new JLabel("Pressure (bar)");
	static JTextField pressTextField = new JTextField();
	private JLabel comp1Label = new JLabel("Component 1");
	static JComboBox comp1Box = new JComboBox(ReadCritPropData.compNameArray2);
	private JLabel comp2Label = new JLabel("Component 2");
	static JComboBox comp2Box = new JComboBox(ReadCritPropData.compNameArray2);
	private JLabel comp3Label = new JLabel("Component 3");
	static JComboBox comp3Box = new JComboBox(ReadCritPropData.compNameArray2);
	private JLabel stepSizeLabel = new JLabel("Select Resolution");
	static JRadioButton lowResButton = new JRadioButton("Low Resolution", true);
	static JRadioButton highResButton = new JRadioButton("High Resolution");
	static ButtonGroup resolutionGroup = new ButtonGroup();

	
	private static float press;
	private static float temp;
	public static double stepSize = 0.01;
	
	private static float tc1 = 508.3f;
	private static float pc1 = 47.62f;
	private static float zc1 = .248f;
	private static float w1 = .668f;
	
	private static float tc2 = 508.3f;
	private static float pc2 = 47.62f;
	private static float zc2 = .248f;
	private static float w2 = .668f;
	
	private static float tc3 = 508.3f;
	private static float pc3 = 47.62f;
	private static float zc3 = .248f;
	private static float w3 = .668f;
	
	private static int selectedIndex;
	
	public SRKParamPanel()
	{
		setLayout(new GridLayout(13, 1));
		setVisible(true);
		setBackground(Color.WHITE);
		add(tempLabel);
		add(tempTextField);
		tempTextField.setText("297.039");
		add(pressLabel);
		add(pressTextField);
		pressTextField.setText("1.01325");
		add(comp1Label);
		add(comp1Box);
		comp1Box.setSelectedIndex(1);
		add(comp2Label);
		add(comp2Box);
		comp2Box.setSelectedIndex(2);
		add(comp3Label);
		add(comp3Box);
		comp3Box.setSelectedIndex(3);
		add(stepSizeLabel);
		resolutionGroup.add(lowResButton);
		resolutionGroup.add(highResButton);
		add(lowResButton);
		add(highResButton);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public static float getPress()
	{
		return press;
	}

	public static void setPress(float press)
	{
		SRKParamPanel.press = press;
	}

	public static float getTemp()
	{
		return temp;
	}

	public static void setTemp(float temp)
	{
		SRKParamPanel.temp = temp;
	}

	public static float getTc1()
	{
		return tc1;
	}

	public static void setTc1(float tc1)
	{
		SRKParamPanel.tc1 = tc1;
	}

	public static float getPc1()
	{
		return pc1;
	}

	public static void setPc1(float pc1)
	{
		SRKParamPanel.pc1 = pc1;
	}

	public static float getZc1()
	{
		return zc1;
	}

	public static void setZc1(float zc1)
	{
		SRKParamPanel.zc1 = zc1;
	}

	public static float getW1()
	{
		return w1;
	}

	public static void setW1(float w1)
	{
		SRKParamPanel.w1 = w1;
	}

	public static float getTc2()
	{
		return tc2;
	}

	public static void setTc2(float tc2)
	{
		SRKParamPanel.tc2 = tc2;
	}

	public static float getPc2()
	{
		return pc2;
	}

	public static void setPc2(float pc2)
	{
		SRKParamPanel.pc2 = pc2;
	}

	public static float getZc2()
	{
		return zc2;
	}

	public static void setZc2(float zc2)
	{
		SRKParamPanel.zc2 = zc2;
	}

	public static float getW2()
	{
		return w2;
	}

	public static void setW2(float w2)
	{
		SRKParamPanel.w2 = w2;
	}

	public static float getTc3()
	{
		return tc3;
	}

	public static void setTc3(float tc3)
	{
		SRKParamPanel.tc3 = tc3;
	}

	public static float getPc3()
	{
		return pc3;
	}

	public static void setPc3(float pc3)
	{
		SRKParamPanel.pc3 = pc3;
	}

	public static float getZc3()
	{
		return zc3;
	}

	public static void setZc3(float zc3)
	{
		SRKParamPanel.zc3 = zc3;
	}

	public static float getW3()
	{
		return w3;
	}

	public static void setW3(float w3)
	{
		SRKParamPanel.w3 = w3;
	}	
}
