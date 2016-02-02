package org.usfirst.frc.team4856.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team4856.robot.Robot;
import org.usfirst.frc.team4856.robot.OI;
import org.usfirst.frc.team4856.robot.commands.LiftWithJoystick2;


/**
 *
 */
public class ElevatorWithoutPot extends Subsystem {
	//(e.g. lifter is a speed controller of type SpeedController)
	private SpeedController lifter;
	private DigitalInput topContact;
	private DigitalInput bottomContact;
	
	//constructor for ELevatorWithoutPot
	public ElevatorWithoutPot () {
		//super(); makes all the attributes of the parent class (in this case Subsystem)
		//available to the derived class (in this case ELevatorWIthoutPot) 
		super();
		lifter = new Talon(2); 
		//chassis = new RobotDrive();
		topContact = new DigitalInput(1);
	    bottomContact = new DigitalInput(2);
	    LiveWindow.addActuator("bottomContact", "LimitSwitch", bottomContact);
        LiveWindow.addActuator("topContact", "LimitSwitch", topContact);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new LiftWithJoystick2());
}
   
    //method for lift
    public void lift() {
	    if(isAtTop()){
	    	 cease();
	    } else {
	    	lifter.set(0.85); //sets the speed (speed ranges from -1 (100% speed backward) to 1 (100% speed forward))
			Timer.delay(0.01); //tells the program to wait 0.5 seconds until going on to the next line
			lifter.set(0);
	    }
    }
    public void lower(){
    	if(isAtBottom()) {
		   	 cease();
    	} else {
	    	lifter.set(-0.85);
	    	Timer.delay(0.01);
	    	lifter.set(0); 
	    }
    }
    public void cease(){
    	lifter.set(0);
    }
    
    public void lift(double speed){
    	
    	if(speed > 0) {
    		   if(isAtTop()){
    		    	 cease();
    		    } else {
    				lifter.set(speed);
    		    }
    	} else {
    		 	if(isAtBottom()) {
    			   	 cease();
    	    	} else {	
    	    		lifter.set(speed);
    	    	}
    	}
    }
    
    
    public boolean isAtTop() {
    	//return false;
    	return topContact.get();
    }
    
    public boolean isAtBottom() {
    	//return false;
    	return bottomContact.get();
    }
    
    
   
//the value for the left wheels will be controlled by the y value of the left joystick. Same for the right joystick.
	
}

