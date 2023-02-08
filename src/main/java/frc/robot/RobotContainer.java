//Root Package
package frc.robot;

//Libraries
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.*;

/** Robot container class */
public class RobotContainer 
{
  //Instance Variables
  SwerveBase M_Drivetrain;
  /** Robot container constructor */
  public RobotContainer() 
  {
    //New Drivetrain Instance
    M_Drivetrain = new SwerveBase();
    
    //Configure Bindings
    configureBindings();
  }

  /** Bind actions to buttons */
  private void configureBindings() {}

  /** @return the robot's autonomous command */
  public Command getAutonomousCommand() {return Commands.print("No autonomous command configured");}
}
