package org.usfirst.frc.team4856.robot.subsystems;

//import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team4856.robot.Robot;
//import org.usfirst.frc.team4856.robot.commands.TankDriveWithJoystick;
/**
 *
 */
public class Grabber extends Subsystem {
	private DigitalInput insideContact;
    private DigitalInput outsideContact;
	
// Subsystem is the parent class of DriveTrain.
// Though inheritance, DriveTrain inherits all the traits of the class Subsystem, and will have any new traits we assign to it.
	
	private SpeedController grabberMotor;
	public Grabber () {
		super();
		grabberMotor = new Victor (4);//grabberMotor runs the grabber 
		insideContact = new DigitalInput(3);
	    outsideContact = new DigitalInput(5);
        LiveWindow.addActuator("insideContact", "LimitSwitch", insideContact);
        LiveWindow.addActuator("outsideContact", "LimitSwitch", outsideContact);
		
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//setDefaultCommand(new OpenGrabber());
  //when no other command is running, the default command is tankdrivewithjoystick. Consult the command TankDriveWithJoystick for more info.
    
    	
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void open() {
    	if(isFullyOpen()){
    		stop();
    	} else {
    	    grabberMotor.set(0.7);
    	}
    	
    }
    
	public void close() {
	    if(isFullyClosed()){
	    	stop(); //why are you doing this to us
	    } else {
	    	grabberMotor.set(-0.7);
	    }
	    	
	}
	
	
    public void stop() {
    	grabberMotor.set(0);
    }
    
    public boolean isFullyClosed() {
    	//return false;
    	return !insideContact.get();
    } 
    
    public boolean isFullyOpen() {
    	//return false;
    	return !outsideContact.get();
    }
    
    
    
	
}

