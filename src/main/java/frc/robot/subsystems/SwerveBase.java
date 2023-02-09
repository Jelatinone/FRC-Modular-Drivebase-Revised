//Root Package
package frc.robot.subsystems;

//Libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenixpro.hardware.Pigeon2;
import frc.robot.Constants.DRIVETRAIN;
import java.util.Objects;

//** Swerve drivetrain class */
public class SwerveBase extends SubsystemBase
{
    //Robot hardware
    private static SwerveModule[] Total_Modules;
    private static SwerveModule[] Front_Modules;
    private static SwerveModule[][] Face_Modules;
    private static Pigeon2 Primary_Gyroscope;

    //Drivebase data
    private static Integer Rotational_Face = DRIVETRAIN.DEFAULT_ROTATIONAL_FACE;

    /** Swerve drivetrain constructor */
    public SwerveBase()
    {
        Total_Modules = new SwerveModule[DRIVETRAIN.FACE_COUNT];
        Front_Modules = new SwerveModule[DRIVETRAIN.MODULES_PER_FACE];
        Face_Modules = new SwerveModule[DRIVETRAIN.FACE_COUNT][DRIVETRAIN.MODULES_PER_FACE];
        if(!DRIVETRAIN.AUTOMATIC_INSTANCIZATION)
        {
            for(int i = 0; i < (DRIVETRAIN.FACE_COUNT); i++) {Total_Modules[i] = new SwerveModule(DRIVETRAIN.DRIVE_MOTORS_INDEX_VALUES[i],DRIVETRAIN.AZIMUTH_MOTORS_INDEX_VALUES[i],DRIVETRAIN.CANCODER_INDEX_VALUES[i],DRIVETRAIN.CANCODER_OFFSET_VALUES[i]);}
            for(int i = 0; i < Face_Modules.length; i++) {for(int j = 0; j < Face_Modules[i].length; j++) {Face_Modules[i][j] = Total_Modules[i+j];}}
        }
        else
        {
            for(int i = 0; i < (DRIVETRAIN.FACE_COUNT * DRIVETRAIN.MODULES_PER_FACE); i++) {Total_Modules[i] = new SwerveModule(i,(i + DRIVETRAIN.FACE_COUNT),i,0.0);}
            for(int i = 0; i < Face_Modules.length; i++) {for(int j = 0; j < Face_Modules[i].length; j++) {Face_Modules[i][j] = Total_Modules[i+j];}}
        }
    }

    /**
     * Drivetrain periodic operations
     */
    @Override
    public void periodic() 
    {
        Front_Modules = Face_Modules[Rotational_Face];

        //TODO: Telemetry Updates
    }

  /** 
   * Given input executes sets motors to correct speeds
   * @param JoystickL_X - The Left Joystick X Component
   * @param JoystickL_Y - The Left Joystick Y Component
   * @param JoystickR_X - The Right Joystick X Component
   */
    public void SwerveDrive(double JoysticL_X, double JoystickL_Y, double JoystickR_X)
    {
        //TODO: Drive method
        
    }

    /**
     *  Decrements the Rotational_Face(Front Face)
     */
    public void DecrementRotationalFace(){if(Objects.equals(Rotational_Face,0)) {Rotational_Face = (DRIVETRAIN.FACE_COUNT-1);} else {Rotational_Face--;}}

    /**
     *  Increments the Rotational_Face(Front Face)
     */
    public void IncrementRotationalFace(){if(Objects.equals(Rotational_Face,(DRIVETRAIN.FACE_COUNT-1))) {Rotational_Face = 0;} else {Rotational_Face++;}}

}
