
package org.usfirst.frc.team4856.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4856.robot.commands.Autonomous;
import org.usfirst.frc.team4856.robot.commands.TankDriveWithJoysticks;
import org.usfirst.frc.team4856.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4856.robot.subsystems.Elevator;
import org.usfirst.frc.team4856.robot.subsystems.ElevatorWithoutPot;
import org.usfirst.frc.team4856.robot.subsystems.Grabber;

/**
 * The VM (virtual machine) is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * Declaration of variables. (e.g., chassis is an instance of DriveTrain)
	 */
	public static DriveTrain chassis;
	public static OI oi;
	public static Elevator elevator;
	public static ElevatorWithoutPot elevatorwithoutpot;
	public static Grabber grabber;
	public static Command autonomousCommand;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    // instantiate the command used for the autonomous period
    //autonomousCommand = new ExampleCommand();
	//oi MUST be last so the program knows where to look for the correct methods
    
    public void robotInit() {
		elevator = new Elevator();
		elevatorwithoutpot = new ElevatorWithoutPot();
		chassis = new DriveTrain();
		grabber = new Grabber();
		oi = new OI();
		autonomousCommand = new Autonomous();	
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
        // schedule the autonomous command (example)
    	//if (the autonomousCommand does not return a null set (is not teleop), then run the autonomousCommand
    	  autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
       autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
