//Root Package
package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
//Libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
    private static Integer Rotational_Face = DRIVETRAIN.DEFAULT_ROTATIONAL_FACE;
    private Boolean Wheel_Locking;

    /** Swerve drivetrain constructor */
    public SwerveBase()
    {
        Modules = new SwerveModule[DRIVETRAIN.FACE_COUNT];
        ModuleGroups = new SwerveModule[DRIVETRAIN.FACE_COUNT][DRIVETRAIN.MODULES_PER_FACE];
        if(!DRIVETRAIN.AUTOMATIC_INSTANCIZATION)
        {
            for(int i = 0; i < (DRIVETRAIN.FACE_COUNT); i++) {Modules[i] = new SwerveModule(DRIVETRAIN.DRIVE_MOTORS_INDEX_VALUES[i],DRIVETRAIN.AZIMUTH_MOTORS_INDEX_VALUES[i],DRIVETRAIN.CANCODER_INDEX_VALUES[i],DRIVETRAIN.CANCODER_OFFSET_VALUES[i]);}
            for(int i = 0; i < ModuleGroups.length; i++) {for(int j = 0; j < ModuleGroups[i].length; j++) {ModuleGroups[i][j] = Modules[i+j];}}
        }
        else
        {
            for(int i = 0; i < (DRIVETRAIN.FACE_COUNT * DRIVETRAIN.MODULES_PER_FACE); i++) {Modules[i] = new SwerveModule(i,(i + DRIVETRAIN.FACE_COUNT),i,0.0);}
            for(int i = 0; i < ModuleGroups.length; i++) {for(int j = 0; j < ModuleGroups[i].length; j++) {ModuleGroups[i][j] = Modules[i+j];}}
        }
    }

    /**
     * Drivetrain periodic operations
     */
    @Override
    public void periodic() 
    {
        //TODO: Telemetry Updates
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
                ModuleGroups[Rotational_Face][(JoystickL_X > 0)? (1): ((JoystickL_X < 0)? (0): (1))].toMagnitude(Math.pow(JoystickL_Y,2),(Math.atan((180/(JoystickR_X * 100)))));
                for(int i = 0; i < ModuleGroups.length; i++)
                    for(int j = 0; j < ModuleGroups[i].length; j++)
                        if(!Objects.equals(ModuleGroups[i][j],ModuleGroups[Rotational_Face][(JoystickL_X > 0)? (1): (0)]))
                            ModuleGroups[i][j].toMagnitude(Math.sqrt((JoystickL_X * JoystickL_X) + (JoystickL_Y + JoystickL_Y)), Math.atan2(JoystickL_X, JoystickL_Y));     
            }
            //Wheel locking true(direct swerve drive)
            else        
            {
                for(int i = 0; i < ModuleGroups.length; i++)
                    for(int j = 0; j < ModuleGroups[i].length; j++)
                        ModuleGroups[i][j].toMagnitude(Math.sqrt((JoystickL_X * JoystickL_X) + (JoystickL_Y + JoystickL_Y)), Math.atan2(JoystickL_X, JoystickL_Y));        
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
