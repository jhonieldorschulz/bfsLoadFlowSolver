package br.com.unioeste.pgeec;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhasorsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testComplex(){
		Complex lhs = new Complex(1.0, 3.0);
		Complex rhs = new Complex(2.0, 5.0);

		Complex answer = lhs.add(rhs);
		System.out.println("lhs.add(rhs):" + lhs.add(rhs));
		System.out.println("lhs.subctract(rhs):" + lhs.subtract(rhs));
		System.out.println("lhs.abs():" + lhs.abs());
		System.out.println("lhs.conjugate():" + lhs.conjugate() );

//		Complex n = new Complex()

//		answer = lhs.conjugate();

	}

	@Test
	public void testPhasors(){
		Complex phasor1 = new Complex(288000.00, 139484.76619330153);
		System.out.println("-------------------------------------------");
		System.out.println("phasor1.abs():" + phasor1.abs());
		System.out.println("phasor.acos():" + phasor1.acos().conjugate());
		System.out.println("Math.acos(0.9):" + Math.acos(0.9));

		Double magnitude = 320000.00;
		Double angRad = Math.acos(0.9);

		System.out.println("magnitude:" + magnitude);
		System.out.println("ang:" + angRad);

		Double real = magnitude * Math.cos(angRad);
		Double imag = magnitude * Math.sin(angRad);

		System.out.println("real:" + real);
		System.out.println("imag:" + imag);


	}

//	@Test
//	public void testVoltages(){
//		Double voltageMagnitude = 13645.699129239396;
//		Double angRad = -0.001920204;
//
//		Double vReal = voltageMagnitude * Math.cos(angRad);
//		Double vImag = voltageMagnitude * Math.sin(angRad);
//
//		System.out.println("---------------------");
//		System.out.println("vReal:" + vReal);
//		System.out.println("vImag:" + vImag);
//
//		Complex voltage = new Voltage(voltageMagnitude * Math.cos(angRad),
//				voltageMagnitude * Math.sin(angRad));
//
//		System.out.println("voltage.getReal():" + voltage.getReal());
//		System.out.println("voltage.getImaginary():" + voltage.getImaginary());
//
//
//		Double current = 23.4506139;
//		Double ang = -0.4491065;
//	}


	@Test
	public void testVoltageExtractData(){
		Double magnitudeInitialVoltage = 34500.00;
		Double angDegreeInitialVoltage = -25.83611;

		Double angDegInitialVoltage = Math.toDegrees(0.0);

		Double vReal = magnitudeInitialVoltage * Math.cos(0.0);
		Double vImag = magnitudeInitialVoltage * Math.sin(0.0);

		System.out.println("--------------------------------------------");
		System.out.println("angRadInitialVoltage:" + angDegInitialVoltage);
		System.out.println("vReal:" + vReal);
		System.out.println("vImag:" + vImag);





		Complex voltage = new Complex(vReal, vImag);

		System.out.println("voltage.abs():" + voltage.abs());
		System.out.println("voltage.acos():" + voltage.acos().conjugate());
//		System.out.println("voltage.cosh():" + voltage.cosh());
	}





	@Test
	public void testPhasors2(){
		Complex phasor1 = new Complex(62000.00, 35000.00);
		System.out.println("phasor.abs():" + phasor1.abs());
		System.out.println("phasor.acos().getReal():" + phasor1.acos().getReal());
		System.out.println("phasor.acos().getImaginary():" + phasor1.acos().getImaginary());
		System.out.println("phasor.getReal():" + phasor1.getReal());
		System.out.println("phasor.getImaginary():" + phasor1.getImaginary());
		System.out.println("phasor1.tan():" + phasor1.tan());
		System.out.println("phasor1.tanh():" + phasor1.tanh());

		Double powerFactor = phasor1.getReal()/ phasor1.abs();
		System.out.println("powerFactor: " + powerFactor);

	}

	@Test
	public void testInjCurrent(){
		Complex voltage = new Complex(34500.00, 0.0);
		Complex power = new Complex(570000.00, 278969.532).conjugate();
		System.out.println("----------------------------------------");
		System.out.println("power.getReal():" + power.getReal());
		System.out.println("power.getImaginary():" + power.getImaginary());
		System.out.println("power.acos():" + power.acos());


		Complex inj = power.conjugate().divide(voltage).conjugate();

		System.out.println("----------------------------------------");
		System.out.println("inj:" + inj.abs() + " / " + inj.acos());

	}

	@Test
	public void testComparisonPython(){
//		Phasor power = new Phasor(290, 180);
//		power.tan();
//		Phasor powerConjugate = new Phasor(power.conjugate().getReal(), power.conjugate().getImaginary());
//		System.out.println("---------------------------------");
//		System.out.println("power.abs():" + powerConjugate.abs());
//		System.out.println("power.acos():"+ powerConjugate.acos().getReal());
//		System.out.println("power.angRad():"+ powerConjugate.getAngRad());
//		System.out.println("imag/real:" + powerConjugate.getImaginary() / powerConjugate.abs());
		Complex power = new Complex(290, 180).conjugate();
		System.out.println("---------------------------------");
		System.out.println("power.abs():" + power.abs());
		System.out.println("power.getReal():" + power.getReal());
		System.out.println("power.getImaginary():" + power.getImaginary());
		System.out.println("power.acos():"+ power.acos().getReal());
		System.out.println("---------------------------------");
		Complex voltage = new Complex(14.87, 0.0);
		System.out.println("voltage.abs():" + voltage.abs());
		System.out.println("voltage.getReal():" + voltage.getReal());
		System.out.println("voltage.getImaginary():" + voltage.getImaginary());
		System.out.println("voltage.acos():" + voltage.acos().getReal());
		System.out.println("---------------------------------");
		Complex current = power.divide(voltage);
		System.out.println("current.abs():" + current.abs());
		System.out.println("current.getReal():" + current.getReal());
		System.out.println("current.getImaginary():" + current.getImaginary());
		System.out.println("current.acos():" + current.acos().getReal());

	}

	@Test
	public void testImpedance(){
		Double resistance = 0.936975;
		Double reactance = 0.72695;

		Complex impedance = new Complex(resistance, reactance);

		System.out.println("-------------------------------");
		System.out.println("impedance.abs():" + impedance.abs());
		System.out.println("impedance.acos():" + impedance.acos());
		System.out.println("impedance.atan():" + impedance.atan().abs());
		System.out.println("impedance.getReal():" + impedance.getReal());
		System.out.println("impedance.tan():" + impedance.tan());
		System.out.println("impedance.tanh():" + impedance.tanh());
		System.out.println("impedance.cos():" + impedance.cos());
		System.out.println("impedance.cosh():" + impedance.cosh());
		System.out.println("impedance.exp():" +impedance.exp());
		System.out.println("impedance.exp():" +impedance.exp());
		System.out.println("impedance.log():" +impedance.log());


		System.out.println("atan(i/r):" + Math.atan(impedance.getImaginary()/impedance.getReal()));
		System.out.println("impedance.getImaginary():" + impedance.getImaginary());


//		System.out.println("atan")

	}

	@Test
	public void testInjectCurrent2(){
		Complex power = new Complex(92.9, 45.0).conjugate();
		Complex voltage = new Complex(2.4, 0.0);

		Complex current = power.divide(voltage);
		System.out.println("-----------------------------------------");
		System.out.println("current.abs():" + current.abs());
		System.out.println("current.abs():" + current.acos());
		System.out.println("angDegree:" + Math.toDegrees(current.log().getImaginary()) );
		System.out.println("current.getReal():" + current.getReal());
		System.out.println("current.getImaginary():" + current.getImaginary());

	}

}
