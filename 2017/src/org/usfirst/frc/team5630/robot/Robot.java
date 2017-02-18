
package org.usfirst.frc.team5630.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	Talon motor;
	CANTalon motor1;
	Joystick joystickInput1;
	//RobotDrive drive;
	public void robotInit() {
		motor = new Talon(0);
		motor1 = new CANTalon(1);
		joystickInput1 = new Joystick(0);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {
		autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	int teleOpCounter;

	public void teleopInit() {
		teleOpCounter = 0;
		motor.set(0);
		motor1.set(0);
	}
	double X, Y;
	public void teleopPeriodic() {
//		if (teleOpCounter < 120) {
//			// motor.set(0.5);
//			motor1.set(0.5);
//			System.out.println(teleOpCounter);
//		} else {
//			motor.set(0);
//			motor1.set(0);
//		}
//		teleOpCounter++;
		X = joystickInput1.getX();
		Y = joystickInput1.getY();
		motor1.set(Y);
		motor.set(X);
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
