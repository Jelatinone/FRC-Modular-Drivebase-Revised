//Root Package
package frc.robot.subsystems;

//Libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import com.ctre.phoenixpro.hardware.Pigeon2;
import frc.robot.Constants.DRIVETRAIN;
import java.util.Objects;

//** Swerve drivetrain class */
public class SwerveBase extends SubsystemBase
{
    //Robot hardware
    private static SwerveModule[] Modules;
    private static SwerveModule[][] ModuleGroups;
    private static Pigeon2 Primary_Gyroscope;

    //Drivebase data
    private static double[] Resultant_Vector;
    private static Integer Rotational_Face = DRIVETRAIN.DEFAULT_ROTATIONAL_FACE;
    private static Boolean Wheel_Locking;

    /** Swerve drivetrain constructor */
    public SwerveBase()
    {
        Modules = new SwerveModule[DRIVETRAIN.FACE_COUNT];
        ModuleGroups = new SwerveModule[DRIVETRAIN.FACE_COUNT][DRIVETRAIN.MODULES_PER_FACE];
        if(!DRIVETRAIN.AUTOMATIC_INSTANCIZATION)
        {
            //Fill Modules
            for(int i = 0; i < (DRIVETRAIN.FACE_COUNT); i++) 
                {Modules[i] = new SwerveModule(DRIVETRAIN.DRIVE_MOTORS_INDEX_VALUES[i],DRIVETRAIN.AZIMUTH_MOTORS_INDEX_VALUES[i],DRIVETRAIN.CANCODER_INDEX_VALUES[i],DRIVETRAIN.CANCODER_OFFSET_VALUES[i]);}
            //Instance ModuleGroups
            for(int i = 0; i < ModuleGroups.length; i++) 
                {for(int j = 0; j < ModuleGroups[i].length; j++) {ModuleGroups[i][j] = Modules[(i+j > ModuleGroups[i].length)? (0): (i+j)];}}
        }
        else
        {
            //Instance Modules
            for(int i = 0; i < (DRIVETRAIN.FACE_COUNT); i++) 
                {Modules[i] = new SwerveModule(i,(i + DRIVETRAIN.FACE_COUNT),i,0.0);}
            //Fill ModuleGroups
            for(int i = 0; i < ModuleGroups.length; i++) 
                {for(int j = 0; j < ModuleGroups[i].length; j++) {ModuleGroups[i][j] = Modules[(i+j > ModuleGroups[i].length)? (0): (i+j)];}}
        }
        Resultant_Vector = new double[] {0.0,0.0};
    }

    /**
     * Drivetrain periodic operations
     */
    @Override
    public void periodic() 
    {
        //Calculate resultant vector of motors
        double Total_Component_X = 0.0;
        double Total_Component_Y = 0.0;
        for(int i = 0 ; i < (DRIVETRAIN.FACE_COUNT); i++)
        {
            Total_Component_Y += (Math.asin(Modules[i].getRealRotation()) * Modules[i].getRealVelocity());
            Total_Component_X += (Math.acos(Modules[i].getRealRotation()) * Modules[i].getRealVelocity());
        }
        Resultant_Vector[0] = Math.atan(Total_Component_Y/Total_Component_X);
        Resultant_Vector[1] = Math.sin(Resultant_Vector[0]) * Total_Component_Y;
    }

  /** 
   * Given input executes sets motors to correct speeds
   * @param JoystickL_X - The Left Joystick X Component
   * @param JoystickL_Y - The Left Joystick Y Component
   * @param JoystickR_X - The Right Joystick X Component
   */
    public void SwerveDrive(double JoystickL_X, double JoystickL_Y, double JoystickR_X)
    {
        //X-locking
        if(Objects.equals(JoystickL_X,0.0) && Objects.equals(JoystickL_X,0.0) && Objects.equals(JoystickL_X,0.0))
            for(int i = 0; i < Modules.length; i++) 
                Modules[i].toAngle(new Rotation2d((Math.atan2(Math.sin(Math.atan2(DRIVETRAIN.ROBOT_WIDTH_METERS, DRIVETRAIN.ROBOT_LENGTH_METERS)),Math.cos(Math.atan2(DRIVETRAIN.ROBOT_WIDTH_METERS, DRIVETRAIN.ROBOT_WIDTH_METERS)))) % 360).getDegrees());
        //Normal Drive
        else
        {
            //Wheel Locking false(normal swerve drive)
            if(!Wheel_Locking)
            {
                ModuleGroups[Rotational_Face][(JoystickL_X > 0)? (1): ((JoystickL_X < 0)? (0): (1))].toVector(Math.pow(JoystickL_Y,2),(Math.atan((180/(JoystickR_X * 100)))));
                for(int i = 0; i < ModuleGroups.length; i++)
                    for(int j = 0; j < ModuleGroups[i].length; j++)
                        if(!Objects.equals(ModuleGroups[i][j],ModuleGroups[Rotational_Face][(JoystickL_X > 0)? (1): (0)]))
                            ModuleGroups[i][j].toVector(Math.sqrt((JoystickL_X * JoystickL_X) + (JoystickL_Y + JoystickL_Y)), Math.atan2(JoystickL_X, JoystickL_Y));     
            }
            //Wheel locking true(direct swerve drive)
            else        
            {
                for(int i = 0; i < ModuleGroups.length; i++)
                    for(int j = 0; j < ModuleGroups[i].length; j++)
                        ModuleGroups[i][j].toVector(Math.sqrt((JoystickL_X * JoystickL_X) + (JoystickL_Y + JoystickL_Y)), Math.atan2(JoystickL_X, JoystickL_Y));        
            }
        }
    }

    /**
     * Zero gyroscope at current yaw heading
     */
    public void zeroGyro() {Primary_Gyroscope.setYaw(0);}

    /**
     * Toggles wheel locking (direct drive) mode
     */
    public void toggleWheelLocking() {Wheel_Locking = !Wheel_Locking;}

    /**
     *  Decrements the Rotational_Face(Front Face)
     */
    public void DecrementRotationalFace(){if(Objects.equals(Rotational_Face,0)) {Rotational_Face = (DRIVETRAIN.FACE_COUNT-1);} else {Rotational_Face--;}}

    /**
     *  Increments the Rotational_Face(Front Face)
     */
    public void IncrementRotationalFace(){if(Objects.equals(Rotational_Face,(DRIVETRAIN.FACE_COUNT-1))) {Rotational_Face = 0;} else {Rotational_Face++;}}

    //ACESSORS

    /**
     * @return - Gyroscope current heading
     */
    public double getGyroHeading() {return Primary_Gyroscope.getAngle();}

}
