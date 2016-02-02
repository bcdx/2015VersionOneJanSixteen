/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team4856.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4856.robot.Robot;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in it's current
 * state PID values for simulation are different than in the real world due to minor differences.
 */
public class Elevator extends PIDSubsystem {
    private SpeedController lifter1;
    private Potentiometer pot;

    private static final double kP_real = 10, kI_real = 0.07,
            kP_simulation = 18, kI_simulation = 0.2;
     
    public Elevator() {
        super(kP_real, kI_real, 0);
        if (Robot.isSimulation()) { // Check for simulation and update PID values
            getPIDController().setPID(kP_simulation, kI_simulation, 0, 0);
        }
        setAbsoluteTolerance(0.005);
        
        lifter1 = new Talon(8);
        
        // Conversion value of potentiometer varies between the real world and simulation
        /*The Potentiometer constructor takes 3 parameters: a channel number for the analog input, 
         * a scale factor to multiply the 0-1 ratiometric value by to return useful units, and an 
         * offset to add after the scaling. Generally, the most useful scale factor will be the angular
         * or linear full scale of the potentiometer. For example, let's say you have an ideal single 
         * turn linear potentiometer attached to a robot arm. This pot will turn 360 degrees over the
         * 0V-5V range so using that for the scale factor 360 will result in the output being in degrees.
         * In order to prevent the potentiometer from breaking due to minor shifting in alignment, 
         * it may be installed with the "zero-point" of the arm a little ways into the potentiometers range, 
         * the example above represents the potentiometer being installed with an initial value of 30 degrees,
         * which is negated using the offset so that the output is 0 at the "zero-point" of the mechanism.
         * 
         * You can also pass an existing AnalogInput to the constructor in place of the channel if you wish 
         * to share the input with other code.*/
        if (Robot.isReal()) {
            pot = new AnalogPotentiometer(1, 0.05);
        } else {
            pot = new AnalogPotentiometer(1); // Defaults to meters
        }
    }

    public void initDefaultCommand() {}


    protected double returnPIDInput() {
        return pot.get();
    }


    /**
     * Use the motor as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double d) {
        lifter1.set(d);
    }
    
}