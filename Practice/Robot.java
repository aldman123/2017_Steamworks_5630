package org.usfirst.frc.team5630.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

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
		//Variable declarations 
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	CANTalon leftSRX, rightSRX;
	Talon rightMotor, leftMotor, shooter1, shooter2;
	Joystick joystick;
	double rightX, rightY, leftTrigger, rightTrigger, leftX, leftY;
	boolean buttonA, buttonB, buttonX, buttonY, buttonRB, buttonLB, buttonLeftStickClick, buttonRightStickClick, buttonBack, buttonStart;
	boolean buttonALast, buttonBLast, buttonXLast, buttonYLast, buttonRBLast, buttonLBLast, buttonLeftStickClickLast, buttonRightStickClickLast, buttonBackLast, buttonStartLast;
	boolean shooterToggle;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		leftSRX = new CANTalon(2);
		rightSRX = new CANTalon(3);
		leftMotor = new Talon(0);
		shooter1 = new Talon(1);
		shooter2 = new Talon(2);
		rightMotor = new Talon(4);
		joystick = new Joystick(0);
		leftSRX.setInverted(true);
		leftMotor.setInverted(true);
		shooter1.setInverted(true);
		shooter2.setInverted(true);
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
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
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
	public void teleopInit(){
		rightSRX.changeControlMode(TalonControlMode.PercentVbus); //Changes to % Voltage
		leftSRX.changeControlMode(TalonControlMode.PercentVbus);
		shooterToggle = false;
		shooterToggle = false;
		robotDrive = new RobotDrive
	}
	@Override
	public void teleopPeriodic() {

		leftX = joystick.getRawAxis(0);
		leftY = joystick.getRawAxis(1);
		rightX = joystick.getRawAxis(4);
		rightY = joystick.getRawAxis(5);
		leftTrigger = joystick.getRawAxis(2);
		rightTrigger = joystick.getRawAxis(3);
		buttonA = joystick.getRawButton(1);
		buttonB = joystick.getRawButton(2);
		buttonX = joystick.getRawButton(3);
		buttonY = joystick.getRawButton(4);
		buttonLB = joystick.getRawButton(5);
		buttonRB = joystick.getRawButton(6);
		buttonBack = joystick.getRawButton(7);
		buttonStart = joystick.getRawButton(8);
		buttonLeftStickClick = joystick.getRawButton(9);
		buttonRightStickClick = joystick.getRawButton(10);
		
		rightSRX.set(rightY/2);
		rightMotor.set(rightY/2);
		leftSRX.set(leftY/2);
		leftMotor.set(leftY/2);
		if(buttonA != buttonALast && buttonA){//Checks if button A was clicked
			shooterToggle = !shooterToggle;
			System.out.println("ShooterToggle: " + shooterToggle);
		}

		
		if(shooterToggle){
			shooter1.set(0.4);
			shooter2.set(0.4);
		}else{
			shooter1.set(0);
			shooter2.set(0);
		}
		
		buttonALast = joystick.getRawButton(1);
		buttonBLast = joystick.getRawButton(2);
		buttonXLast = joystick.getRawButton(3);
		buttonYLast = joystick.getRawButton(4);
		buttonLBLast = joystick.getRawButton(5);
		buttonRBLast = joystick.getRawButton(6);
		buttonBackLast = joystick.getRawButton(7);
		buttonStartLast = joystick.getRawButton(8);
		buttonLeftStickClickLast = joystick.getRawButton(9);
		buttonRightStickClickLast = joystick.getRawButton(10);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}

