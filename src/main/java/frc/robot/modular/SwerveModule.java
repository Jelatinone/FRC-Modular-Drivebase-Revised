//Package
package frc.robot.modular;

//Libraries
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import java.lang.Thread;

/**
 * Independent swerve drivetrain hardware component class representing drivetrain's
 * both positionable and driveable wheels which is updated on an independent thread.
 * @author Jelatinone (CW)
 * @version 1.0
 * @since 1.0
 */
public final class SwerveModule extends Thread
{
    //Class constants
    public static final class DRIVING_MOTOR_CLASS extends WPI_TalonFX {public DRIVING_MOTOR_CLASS(int i) {super(i);}}
    public static final class AZIMUTH_MOTOR_CLASS extends WPI_TalonFX {public AZIMUTH_MOTOR_CLASS(int i) {super(i);}}
    public static final class ENCODER_CLASS extends CANCoder {public ENCODER_CLASS(int i) {super(i);}}

    //Module hardware
    private final DRIVING_MOTOR_CLASS Driving_Motor;
    private final AZIMUTH_MOTOR_CLASS Azimuth_Motor;
    private final ENCODER_CLASS Module_Encoder;

    //Module data
    private final SimpleVector Target_Vector;
    private final SimpleVector Current_Vector;

    //Class data
    private static final SimpleVector Current_Resultant_Vector = new SimpleVector();
    private static final SimpleVector Target_Resultant_Vector  = new SimpleVector();

    /** Constructor. */
    public SwerveModule() 
    {
        //Instance hardware
        Driving_Motor = new DRIVING_MOTOR_CLASS(0);
        Azimuth_Motor = new AZIMUTH_MOTOR_CLASS(0);
        Module_Encoder = new ENCODER_CLASS(0);

        //Instance data
        Target_Vector = new SimpleVector();
        Current_Vector = new SimpleVector();

        //Start module's thread
        this.start();
    }
    /** Update module */
    public void run()
    {
        synchronized(Current_Resultant_Vector)
        {
            Current_Resultant_Vector.updateVector(0,0);
        }
        synchronized(Target_Resultant_Vector)
        {
            Target_Resultant_Vector.updateVector(0,0);
        }
    }

    
}
/** 
 * SwerveModule wrapper class based on simple trigonometry to provide module with basic
 * vector functions for position estimation.
 * @author Jelatinone (CW)
 * @version 1.0
 * @since 1.0
 **/
class SimpleVector
{
    SimpleVector()
    {

    }

    public synchronized void updateVector(double X, double Y)
    {

    }

}
