package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.ModularSwerve.*;
import frc.robot.Constants.DRIVECOMMAND;
import java.util.Objects;

public class DriveCommand extends CommandBase
{
    //Suppress Warnings
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    //Instance Variables
    private double JoystickL_X;
    private double JoystickL_Y;
    private double JoystickR_X;
    private final SwerveBase Parent_Subsystem;
    private boolean Command_Complete = false;

    /**
     * Joystick drive command constructor
     * @param M_Controller - The driver's controller
     * @param Parent - The parent drivetrain to apply command
     */
    public DriveCommand(CommandXboxController M_Controller, SwerveBase Parent)
    {
        //Joystick inputs
        if(Math.abs(M_Controller.getRawAxis(DRIVECOMMAND.LEFT_X_AXIS_INDEX)) > DRIVECOMMAND.LEFT_X_JOYSTICK_DEADZONE) {JoystickL_X = M_Controller.getRawAxis(DRIVECOMMAND.LEFT_X_AXIS_INDEX);}else{JoystickL_X = 0.0;}
        if(Math.abs(M_Controller.getRawAxis(DRIVECOMMAND.LEFT_Y_AXIS_INDEX)) > DRIVECOMMAND.LEFT_Y_JOYSTICK_DEADZONE) {JoystickL_Y = M_Controller.getRawAxis(DRIVECOMMAND.LEFT_Y_AXIS_INDEX);}else{JoystickL_Y = 0.0;}
        if(Math.abs(M_Controller.getRawAxis(DRIVECOMMAND.RIGHT_X_AXIS_INDEX)) > DRIVECOMMAND.RIGHT_X_JOYSTICK_DEADZONE) {JoystickR_X = M_Controller.getRawAxis(DRIVECOMMAND.RIGHT_X_AXIS_INDEX);}else{JoystickR_X = 0.0;}
        //Square joystick inputs
        JoystickL_X = JoystickL_X * JoystickL_X * (Math.abs(JoystickL_X)/((JoystickL_X == 0)? (1) : (JoystickL_X)));
        JoystickL_Y = JoystickL_Y * JoystickL_Y * (Math.abs(JoystickL_Y)/((JoystickL_Y == 0)? (1) : (JoystickL_Y)));
        JoystickR_X = JoystickR_X * JoystickR_X * (Math.abs(JoystickR_X)/((JoystickR_X == 0)? (1) : (JoystickR_X)));
        //Set parent
        Parent_Subsystem = Parent;
        addRequirements(Parent_Subsystem);
    }

    //Initialize Command
    @Override
    public void initialize() {}

    //Execute Command
    @Override
    public void execute() 
    {
        //Parent_Subsystem.SwerveDrive(JoystickL_X, JoystickL_Y, JoystickR_X);
        Command_Complete = true;
    }
    //End Command
    @Override
    public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}

    //Check Command Complete
    @Override
    public boolean isFinished() {return Command_Complete;}
}
