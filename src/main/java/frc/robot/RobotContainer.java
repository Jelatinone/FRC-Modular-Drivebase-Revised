//Root Package
package frc.robot;

//Libraries
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.CONTAINER;
import frc.robot.commands.DriveCommand;
import frc.robot.modular.*;


/** Robot container class */
public class RobotContainer 
{
  //Instance Variables
  //SwerveBase M_Drivetrain;
  //Controllers
  private CommandXboxController M_Controller;
  //Buttons
  private Trigger Controller_Increment_Button;
  private Trigger Controller_Decrement_Button;
  private Trigger Controller_Wheel_Lock_Button;
  private Trigger Controller_Zero_Gyro_Button;

  /** Robot container constructor */
  public RobotContainer() 
  {
    //New Drivetrain Instance
    //M_Drivetrain = new SwerveBase();
    //M_Controller = new CommandXboxController(CONTAINER.CONTROLLER_INDEX);
    //Controller_Increment_Button = CONTAINER.CONTROLLER_INCREMENT_BUTTON;
    //Controller_Decrement_Button = CONTAINER.CONTROLLER_DECREMENT_BUTTON;
    //Controller_Wheel_Lock_Button = CONTAINER.CONTROLLER_WHEEL_LOCKING_BUTTON;
    //Controller_Zero_Gyro_Button = CONTAINER.CONTROLLER_GYRO_ZERO_BUTTON;
    //M_Drivetrain.setDefaultCommand(new DriveCommand(M_Controller,M_Drivetrain));   
    
    //Configure Bindings
    configureBindings();
  }

  /** Bind actions to buttons */
  private void configureBindings() 
  {
    //When Button Pressed, Increment Rotational Face
    //Controller_Increment_Button.onTrue(Commands.run(M_Drivetrain::IncrementRotationalFace));
    //When Button Pressed, Decrement Rotational Face
    //Controller_Decrement_Button.onTrue(Commands.run(M_Drivetrain::DecrementRotationalFace));
    //When Button Pressed, Toggle Wheel Locking
    //Controller_Wheel_Lock_Button.onTrue(Commands.run(M_Drivetrain::toggleWheelLocking));
    //When Button Pressed, Zero Gyroscope
    //Controller_Zero_Gyro_Button.onTrue(Commands.run(M_Drivetrain::toggleWheelLocking));
  }

  /** @return the robot's autonomous command */
  public Command getAutonomousCommand() {return Commands.print("No autonomous command configured");}
}
