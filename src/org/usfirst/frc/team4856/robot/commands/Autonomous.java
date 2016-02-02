package org.usfirst.frc.team4856.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
	    public Autonomous() {
	    	addSequential(new SpinQuarter());
	    	addSequential(new LiftEleAuto());
	        /*addSequential(new CloseGrabber());
	        addSequential(new LiftElevator());
	        addSequential(new DriveBackwards());
	        addSequential(new LowerElevator());
	        addSequential(new OpenGrabber());*/
	    }
}
