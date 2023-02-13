//Package
package frc.robot;

//Libraries
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import com.ctre.phoenix.sensors.CANCoder;


//** Constants class */
public final class Constants
{
    //** Swereve module constants class */
    public static final class MODULE
    {
        /** Module's driver motor class type */
        public static final class DRIVING_MOTOR_TYPE extends WPI_TalonFX {public DRIVING_MOTOR_TYPE(int i) {super(i);}}

        /** Module's azimuth motor class type */
        public static final class AZIMUTH_MOTOR_TYPE extends WPI_TalonFX {public AZIMUTH_MOTOR_TYPE(int i) {super(i);}}

        /** Module's encoder class type */
        public static final class ENCODER_TYPE extends CANCoder {public ENCODER_TYPE(int i) {super(i);}}

        /**  */
        public static final Runnable[] setInvertedMethods = {};

        /** Current limit on module drivers */
        public static final StatorCurrentLimitConfiguration DRIVE_CURRENT_LIMIT = new StatorCurrentLimitConfiguration(true, 80, 80, 0);
        
        /** Current limit on module azimuths */
        public static final StatorCurrentLimitConfiguration AZIMUTH_CURRENT_LIMIT = new StatorCurrentLimitConfiguration(true, 20, 20, 0);

        /** module drivers KP value */
        public final static Double DRIVE_KP = 0.044057;
        
        /** module drivers KI value */
        public final static Double DRIVE_KI = 0.0;
        
        /** module drivers KD value */
        public final static Double DRIVE_KD = 0.0;
        
        /** module drivers KF value */
        public final static Double DRIVE_KF = 0.028998;

        /** module drivers S Curve Strength value */
        public final static Integer DRIVE_S_CURVE_STRENGTH = 1;

        /** module azimuths KP value */
        public final static Double AZIMUTH_KP = 0.2;
        
        /** module azimuths KI value */
        public final static Double AZIMUTH_KI = 0.0;
        
        /** module azimuths KD value */
        public final static Double AZIMUTH_KD = 0.1;
        
        /** module azimuths KF value */
        public final static Double AZIMUTH_KF = 0.0;

        /** module drivers S Curve Strength value */
        public final static Integer AZIMUTH_S_CURVE_STRENGTH = 1;
    }
    public static final class DRIVETRAIN 
    {
        
    }
    public static final class DRIVECOMMAND
    {
        /** Primary Controller Left X Deadzone */
        public static final Double LEFT_X_JOYSTICK_DEADZONE = 0.05;
        /** Primary Controller Left Y Deadzone */
        public static final Double LEFT_Y_JOYSTICK_DEADZONE = 0.05;
        /** Primary Controller Right X Deadzone */
        public static final Double RIGHT_X_JOYSTICK_DEADZONE = 0.05;
        /** Primary Controller Left X Axis */
        public static final Integer LEFT_X_AXIS_INDEX = 0;
        /** Primary Controller Left Y Axis */
        public static final Integer LEFT_Y_AXIS_INDEX = 1;
        /** Primary Controller Right X Axis */
        public static final Integer RIGHT_X_AXIS_INDEX = 4;
    }
    public static final class CONTAINER 
    {
         /** Primary Controller Index */
         public static final Integer CONTROLLER_INDEX = 0;
         /** Primary Controller Increment Button */
         public static final Trigger CONTROLLER_INCREMENT_BUTTON = new CommandXboxController(CONTROLLER_INDEX).a();
         /** Primary Controller Decrement Button */
         public static final Trigger CONTROLLER_DECREMENT_BUTTON = new CommandXboxController(CONTROLLER_INDEX).b();
        /** Primary Controller Wheel Lock toggle Button */
        public static final Trigger CONTROLLER_WHEEL_LOCKING_BUTTON = new CommandXboxController(CONTROLLER_INDEX).x();
        /** Primary Controller Zero Gyroscope Button */
        public static final Trigger CONTROLLER_GYRO_ZERO_BUTTON = new CommandXboxController(CONTROLLER_INDEX).y();
    }
}
