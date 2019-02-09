package pengRobinsonEquation;

import java.util.ArrayList;
import srkEquation.Cubic;
import srkEquation.SRKParamPanel;

public class GeneratePRArray
{
	public static float[][] vaporComp;
	public static float[][] liquidComp;
	
	public static void GeneratePRArray()
	{
		//Composition of component 3 does not have to be defined.  It's the difference between x1 + x2 and 1.
		double x3;
		double stepSize = SRKParamPanel.stepSize;
		
		//Used to solve SRK cubic equation
		Cubic cubicSolver = new Cubic();
		
		//Calculates the number of rows in array based on step size from the SRKParamPanel.  Needs to round to use this value
		//Graphing space is square, so numRow = numColumn.  Only need 1 going forward
		double numRow = 1 / stepSize;
		
		numRow = numRow * 100;
		numRow = numRow + 0.5f;
		numRow = (int) numRow;
		numRow = numRow / 100;
		
		double[] x1CompArray = new double[(int) numRow];
		double[] x2CompArray = new double[(int) numRow];
		
		//Creates 2 arrays with component 1 and 2 compositions.
		double adder = 0;
		for(int i = 0; i < numRow; ++i)
		{
			x1CompArray[i] = adder;
			x2CompArray[i] = adder;
			adder = adder + stepSize;
		}
		
		// Ideal Gas Constant R = (m^3*bar)/(mol*K)
		double r = 0.00008314; 
		
		// The following are the pure component values of the PR equation constants.
		// Refer to the original paper to understand where they come from

		double a1 = (0.45723553 * (Math.pow(r, 2) * Math.pow(SRKParamPanel.getTc1(), 2)) / SRKParamPanel.getPc1());
		double a2 = (0.45723553 * (Math.pow(r, 2) * Math.pow(SRKParamPanel.getTc2(), 2)) / SRKParamPanel.getPc2());
		double a3 = (0.45723553 * (Math.pow(r, 2) * Math.pow(SRKParamPanel.getTc3(), 2)) / SRKParamPanel.getPc3());
		
		double b1 = (0.07779607 * (r * SRKParamPanel.getTc1())/SRKParamPanel.getPc1());
		double b2 = (0.07779607 * (r * SRKParamPanel.getTc2())/SRKParamPanel.getPc2());
		double b3 = (0.07779607 * (r * SRKParamPanel.getTc3())/SRKParamPanel.getPc3());
		
		double tr1 = SRKParamPanel.getTemp() / SRKParamPanel.getTc1();
		double tr2 = SRKParamPanel.getTemp() / SRKParamPanel.getTc2();
		double tr3 = SRKParamPanel.getTemp() / SRKParamPanel.getTc3();
		
		double m1 = (0.37464 + 1.54226 * SRKParamPanel.getW1() - 0.26993*Math.pow(SRKParamPanel.getW1(), 2));
		double m2 = (0.37464 + 1.54226 * SRKParamPanel.getW2() - 0.26993*Math.pow(SRKParamPanel.getW2(), 2));
		double m3 = (0.37464 + 1.54226 * SRKParamPanel.getW3() - 0.26993*Math.pow(SRKParamPanel.getW2(), 2));
		
		double alpha1 = Math.pow((1 + m1 * (1 - Math.pow(tr1, 0.5))), 2);
		double alpha2 = Math.pow((1 + m1 * (1 - Math.pow(tr2, 0.5))), 2);
		double alpha3 = Math.pow((1 + m1 * (1 - Math.pow(tr3, 0.5))), 2);
		
		//The following are the declaration of the mixture parameters.
		double alpha12 = Math.pow((alpha1 * alpha2), 2);
		double alpha23 = Math.pow((alpha2 * alpha3), 2);
		double alpha13 = Math.pow((alpha1 * alpha3), 2);
		
		double a12 = Math.pow((a1 * a2), 0.5);
		double a23 = Math.pow((a1 * a2), 0.5);
		double a13 = Math.pow((a1 * a2), 0.5);
		
		double am;
		double bm;
		
		double A;
		double B;
		
		double zv = 0;
		double zl = 0;
		
		double aa1;
		double bb1;
		double aa2;
		double bb2;
		double aa3;
		double bb3;
		
		double lnphivx1;
		double lnphilx1;
		double lnphivx2;
		double lnphilx2;
		double lnphivx3;
		double lnphilx3;
		
		double gibbsVapor;
		double gibbsLiquid;
		
		vaporComp = new float[(int) numRow][(int) numRow];
		liquidComp = new float[(int) numRow][(int) numRow];
		
		for(int i = 0; i < numRow; ++i)
		{
			for(int j = 0; j < numRow; ++j)
			{
					x3 = 1 - x1CompArray[i] - x2CompArray[j];
					
						//Once the values of composition x1, x2 and x3 are tested and found to be < 1
						//the mixture values of the SRK equation constants are calculated.
						am =  (Math.pow(x1CompArray[i], 2)*a1*alpha1 + 2*x1CompArray[i]*x2CompArray[j]*a12*alpha12 + 2*x1CompArray[i]*x3*a13*alpha13 + Math.pow(x2CompArray[j], 2)*a2*alpha2 + 2*x2CompArray[j]*x3*a23*alpha23 + Math.pow(x3, 2)*a3*alpha3);
						bm = x1CompArray[i]*b1 + x2CompArray[j]*b2 + x3*b3;
						
						aa1 = a1 * alpha1 / am;
						bb1 = b1/bm;
						aa2 = a2 * alpha2 / am;
						bb2 = b2/bm;
						aa3 = a3 * alpha3 / am;
						bb3 = b3 / bm;
						
						A =  (am*(SRKParamPanel.getPress()/(Math.pow(r, 2)*Math.pow(SRKParamPanel.getTemp(), 2))));
						B =  ((bm * SRKParamPanel.getPress()) / (r * SRKParamPanel.getTemp()));
						
						//Open source software is used to solve for the roots of the cubic equation
						//refer to the documentation at the top of class Cubic.java to understand how this equation works
						cubicSolver.solve(1, -1*(1-B), A-3*Math.pow(B, 2)-2*B, -1*(A*B-Math.pow(B, 2)-Math.pow(B, 3)));
						
						//Depending on the number of roots the following decides whether values are
						//for the liquid or vapor phase.  Roots of equation are compressibility factor Z.
						if (cubicSolver.nRoots == 1)
						{
							zv = 0;
							zl =  cubicSolver.x1;
						}
						else if(cubicSolver.nRoots == 2)
						{
							zl =  cubicSolver.x1;
							zv =  cubicSolver.x2;
						}
						else if(cubicSolver.nRoots == 3)
						{
							zl =  cubicSolver.x1;
							zv =  cubicSolver.x3;
							
						}
						
						// The following solves for the fugacity coefficients of the mixture.
						lnphivx1 =  (bb1*(zv-1) - Math.log(zv-B) - (A/B)*(2*aa1-bb1)*Math.log(1+(B/zv)));
						lnphilx1 =  (bb1*(zl-1) - Math.log(zl-B) - (A/B)*(2*Math.pow(aa1, 0.5)-bb1)*Math.log(1+(B/zl)));
						
						lnphivx2 =  (bb2*(zv-1) - Math.log(zv-B) - (A/B)*(2*aa2-bb1)*Math.log(1+(B/zv)));
						lnphilx2 =  (bb2*(zl-1) - Math.log(zl-B) - (A/B)*(2*Math.pow(aa2, 0.5)-bb2)*Math.log(1+(B/zl)));
						
						lnphivx3 =  (bb3*(zv-1) - Math.log(zv-B) - (A/B)*(2*aa3-bb3)*Math.log(1+(B/zv)));
						lnphilx3 =  (bb3*(zl-1) - Math.log(zl-B) - (A/B)*(2*Math.pow(aa3, 0.5)-bb3)*Math.log(1+(B/zl)));
						
						// Gibbs free energy is used to determine phase stability
						gibbsVapor =  (x1CompArray[i]*Math.log(x1CompArray[i] + lnphivx1) + x2CompArray[j]*(Math.log(x2CompArray[j]) + lnphivx2) + x3*(Math.log(x3) + lnphivx3));
						gibbsLiquid =  (x1CompArray[i]*Math.log(x1CompArray[i]) + lnphilx1 + x2CompArray[j]*Math.log(x2CompArray[j]) + lnphilx2 + x3*Math.log(x3) + lnphilx3);
						
						
						//System.out.println("Gibbs Liquid Value: " + gibbsLiquid);
						// Finally Gibbs energy is added to the double array to be graphed.
						vaporComp[i][j] = (float) gibbsVapor;
						liquidComp[i][j] = (float) gibbsLiquid;
				}
			}
		}
	}