package frc.robot;
import edu.wpi.first.wpilibj.*;
import frc.robot.DriveTrain.*;
//import frc.team3006.Mechanisms.*;

public class Controller {
    private Joystick joystick = new Joystick(0);

    final private int buttonA = 1;
    final private int buttonY = 4;
    final private int buttonX = 3;
    final private int buttonB = 2;
    final private int buttonLeftBumper = 5;
    final private int buttonRightBumper = 6; 
    final private int leftXAxis = 0;
    final private int leftYAxis = 1; 
    final private int rightXAxis = 4;
    final private int rightYAxis = 5; 
    final private int leftTrigger = 2; 
    final private int rightTrigger = 3;
    final private double triggerDeadZone = 0.05;

    private DriveTrain driveTrain;
	private int driveTrainLeft1 = 1;
	private int driveTrainLeft2 = 2;
	private int driveTrainRight1 = 3;
	private int driveTrainRight2 = 4;


	private double maxPower;
    

    public Controller(int port) {

		joystick = new Joystick(port);
		
		
		
	/*	if(!Controller.isDriver && isDriver) {
			Controller.isDriver = isDriver;
		} else if (Controller.isDriver) {
			isDriver = false;
			System.out.println("Driver controller already created. This controller is connected to port: " + 
				port + " and will not have driver control");
		}*/

		
    }
    
    public void testFalcon()
    {
       double testSpeed = joystick.getRawAxis(leftYAxis);

       driveTrain.drive(testSpeed, 0);
    }
	
	public void tankDrive() {
		if(driveTrain != null) {
	
			double leftMotorMove = joystick.getRawAxis(leftYAxis);
			double rightMotorMove = joystick.getRawAxis(rightYAxis);
			if(joystick.getRawAxis(rightTrigger) > triggerDeadZone) {
				driveTrain.boost(leftMotorMove, rightMotorMove);
			} else {
				/*if(leftMotorMove < 0 && rightMotorMove > 0) {
					driveTrain.drive(leftMotorMove, rightMotorMove);
				} else if (leftMotorMove > 0 && rightMotorMove < 0) {
					driveTrain.reverse(leftMotorMove, rightMotorMove);
				}*/
				driveTrain.drive(leftMotorMove, rightMotorMove);
			}
		} else {
			System.out.println("Wrong controller. This is not the driver controller");
		}
    }
    
    public void setMaxDriveSpeed(double val) {
		driveTrain.setMaxSpeed(val);
	}

	public void setDriveTrain(DriveTrain driveTrain)
	{
		this.driveTrain = driveTrain;
	}

}