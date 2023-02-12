//Package
package frc.robot.ModularSwerve;

//Libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ModularSwerve.SwerveModule.*;

/** 
 * A Swerve drivetrain class handling swerveDrive 
 * type drivetrains with built for modularity.
 * @author - Jelatinone
 * @version - 1.0
 * */
public class SwerveBase extends SubsystemBase
{
    //Drivetrain hardware

    //Drivetrain data
    private ModuleVector Expected_Resultant_Vector;
    private ModuleVector Real_Resultant_Vector;

    /** Constructor. */
    public SwerveBase()
    {

    }
}
