package frc.robot;

//import edu.wpi.first.wpilibj.Victor;
import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class DriveTrain
{
    //creates static variables
   
    private double maxSpeed;
    private SpeedControllerGroup rightGroup;
    private SpeedControllerGroup leftGroup;

    /*public DriveTrain(SpeedController... motors) {
        rightMotors = new SpeedControllerGroup();

    }*/

    public DriveTrain(List<SpeedController> leftMotors, List<SpeedController> rightMotors, double maxSpeed)
    {
        //constructor for drivetrain
        
        if(rightMotors.size() > 1)
            rightGroup = new SpeedControllerGroup(rightMotors.get(0), rightMotors.subList(1, rightMotors.size()).toArray(new SpeedController[rightMotors.size()]));
        else
            rightGroup = new SpeedControllerGroup(rightMotors.get(0));        
        
        if(leftMotors.size() > 1)
            leftGroup = new SpeedControllerGroup(leftMotors.get(0), leftMotors.subList(1, leftMotors.size()).toArray(new SpeedController[leftMotors.size()]));
        else
            leftGroup = new SpeedControllerGroup(leftMotors.get(0));          

       

        this.maxSpeed = maxSpeed; //max speed for any motor at any time
    }
   
    public void drive(double leftMotorMove, double rightMotorMove)
    {
        rightGroup.set(rightMotorMove);
        leftGroup.set(leftMotorMove);
       
    }

   

    public void boost(double leftMotorMove, double rightMotorMove)
    {
        if(Math.signum(leftMotorMove) * -1 == Math.signum(rightMotorMove)) // round two numbers to sigs, if motor powers are the same
        {
            leftMotorMove *= maxSpeed; //multiplies the motor power times the power of the maximum speed
            rightMotorMove *= maxSpeed;
        }

        drive(leftMotorMove * -1, rightMotorMove);
    }

    public void reverse(double leftMotorMove, double rightMotorMove)
    {
       drive(leftMotorMove * maxSpeed, rightMotorMove * -1 * maxSpeed);
    }

   
    public void setMaxSpeed(double maxSpeed)
    {
        this.maxSpeed = maxSpeed; //sets the static variable to the parameter
    }
}
