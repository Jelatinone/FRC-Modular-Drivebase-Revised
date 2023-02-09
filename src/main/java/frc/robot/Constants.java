//Root Package
package frc.robot;

//Libraries
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

//** Constants class */
public final class Constants 
{
    //** Swereve module constants class */
    public static final class MODULE
    {
        /** Current limit on module drivers */
        public static final StatorCurrentLimitConfiguration DRIVE_CURRENT_LIMIT = new StatorCurrentLimitConfiguration(true, 80, 80, 0);
        
        /** Current limit on module azimuths */
        public static final StatorCurrentLimitConfiguration AZIMUTH_CURRENT_LIMIT = new StatorCurrentLimitConfiguration(true, 20, 20, 0);
        
        /** Drive train gear ratio */
        public static final Double DRIVE_GEAR_RATIO = 6.75;

        /** Wheel diameter (Meters) */
        public static final Double WHEEL_DIAMETER_METERS = 0.1016;

        /** Drive train gear ratio */
        public static final Double MAXIMUM_LINEAR_SPEED = 5.4;

        /** Drive train gear ratio */
        public static final Double MAXIMUM_ROTATION_SPEED = (Math.PI * 2);

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
        /** Modular Instancization Boolean */
        public static final Boolean AUTOMATIC_INSTANCIZATION = false;

        /** Robot's width (Meters) */
        public static final Double ROBOT_WIDTH_METERS = 0.6858;

        /** Robot's length (Meters) */
        public static final Double ROBOT_LENGTH_METERS = 0.6858;

        /** robot number of face */
        public static Integer FACE_COUNT = 4;

        /** robot front(rotational) face */
        public static Integer DEFAULT_ROTATIONAL_FACE = 0;

        /** robot number of swerve modules per each face */
        public static final Integer MODULES_PER_FACE = 2;  

        /** Main Gyroscope Index Value */
        public static final Integer GYRO_INDEX = 4;  

        /** Azimuth Motor Indexes */
        public static final Integer[] AZIMUTH_MOTORS_INDEX_VALUES = {21,22,23,24};

        /** Drive Motor Indexes */
        public static final Integer[] DRIVE_MOTORS_INDEX_VALUES = {11,12,13,14};

        /** CanCODER Indexex */
        public static final Integer[] CANCODER_INDEX_VALUES = {5,6,7,8};

        /** CanCODER Offset Values */
        public static final Double[] CANCODER_OFFSET_VALUES = {-313.682,-166.553,-246.006,-204.258};
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
