package org.usfirst.frc.team5630.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
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
	// Variable declarations
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	CANTalon leftSRX, rightSRX, shooter1, shooter2;
	Talon rightMotor, leftMotor, intakeMotor, indexMotor, climberMotor;
	Joystick joystick;
	double rightX, rightY, leftTrigger, rightTrigger, leftX, leftY;
	boolean buttonA, buttonB, buttonX, buttonY, buttonRB, buttonLB, buttonLeftStickClick, buttonRightStickClick,
			buttonBack, buttonStart;
	boolean buttonALast, buttonBLast, buttonXLast, buttonYLast, buttonRBLast, buttonLBLast, buttonLeftStickClickLast,
			buttonRightStickClickLast, buttonBackLast, buttonStartLast;
	boolean shooterToggle;
	int numberOfPWMisZeroinARow;
	RobotDrive robotDrive;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		shooter1 = new CANTalon(1);
		shooter2 = new CANTalon(2);
		leftSRX = new CANTalon(3);
		leftMotor = new Talon(2);
		rightSRX = new CANTalon(4);
		rightMotor = new Talon(0);
		indexMotor = new Talon(1);
		// climberMotor = new Talon(2);
		intakeMotor = new Talon(3);

		joystick = new Joystick(0);
		// intakeMotor.setInverted(true);
		shooter1.setInverted(true);
		shooter2.setInverted(true);
		leftSRX.setInverted(false);
		leftMotor.setInverted(false);
		rightSRX.setInverted(false);
		rightMotor.setInverted(false);
		rightSRX.configPeakOutputVoltage(12.0f, -12.0f);
		leftSRX.configPeakOutputVoltage(12.0f, -12.0f);
		numberOfPWMisZeroinARow = 0;

		// shooter1.setInverted(false);
		// shooter2.setInverted(false);
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
	public void teleopInit() {
		rightSRX.changeControlMode(TalonControlMode.PercentVbus); // Changes to
																	// % Voltage
		leftSRX.changeControlMode(TalonControlMode.PercentVbus);
		shooterToggle = false;
		shooter2.changeControlMode(TalonControlMode.Follower);
		shooter2.set(1);
		robotDrive = new RobotDrive(leftSRX, leftMotor, rightSRX, rightMotor);// front
																				// left,
																				// rear
																				// left,
																				// front
																				// right,
																				// rear
																				// right

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
		/*
		 * if(buttonA != buttonALast && buttonA){//Checks if button A was
		 * clicked shooterToggle = !shooterToggle;
		 * System.out.println("ShooterToggle: " + shooterToggle); }
		 */

		/*
		 * if(buttonRB = true){//Checks if button A was clicked
		 * indexMotor.set(1); }
		 */

		// intakeMotor.set(rightTrigger);//Runs intake with right trigger speed

		/*
		 * if(shooterToggle){ shooter1.set(0.4); shooter2.set(0.4); }else{
		 * shooter1.set(0); shoot
		 */

		// climberMotor.set(leftTrigger);
		robotDrive.arcadeDrive(-leftY, rightX);
		if (buttonStart)
			shooter1.set(rightTrigger);
		// leftMotor.set(leftSRX.getOutputVoltage()/leftSRX.getBusVoltage());
		// rightMotor.set(rightSRX.getOutputVoltage()/rightSRX.getBusVoltage());
		// System.out.println("Output of left SRX: " +
		// leftSRX.getOutputVoltage() + "\t Output of right SRX: " +
		// rightSRX.getOutputVoltage());
		// System.out.println("PWM of left: " +
		// leftSRX.getOutputVoltage()/leftSRX.getBusVoltage() + "PWM of right: "
		// + rightSRX.getOutputVoltage()/rightSRX.getBusVoltage());
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
