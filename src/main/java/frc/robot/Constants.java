//Root Package
package frc.robot;

//Libraries
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
//** Constants class */
public final class Constants 
{
    //** Swereve module constants class */
    public static class MODULE
    {
        /** Current limit on module drivers */
        public static final StatorCurrentLimitConfiguration DRIVE_CURRENT_LIMIT = new StatorCurrentLimitConfiguration(true, 80, 80, 0);
        
        /** Current limit on module azimuths */
        public static final StatorCurrentLimitConfiguration AZIMUTH_CURRENT_LIMIT = new StatorCurrentLimitConfiguration(true, 20, 20, 0);
        
        /** Drive train gear ratio */
        public static final Double DRIVE_GEAR_RATIO = 6.75;

        /** Wheel diameter  */
        public static final Double WHEEL_DIAMETER = 0.1016;

        /** module drivers KP value */
        public final static Double DRIVE_KP = 0.044057;
        
        /** module drivers KI value */
        public final static Double DRIVE_KI = 0.0;
        
        /** module drivers KD value */
        public final static Double DRIVE_KD = 0.0;
        
        /** module drivers KF value */
        public final static Double DRIVE_KF = 0.028998;
        
        /** module azimuths KP value */
        public final static Double AZIMUTH_KP = 0.2;
        
        /** module azimuths KI value */
        public final static Double AZIMUTH_KI = 0.0;
        
        /** module azimuths KD value */
        public final static Double AZIMUTH_KD = 0.1;
        
        /** module azimuths KF value */
        public final static Double AZIMUTH_KF = 0.0;
    }
    public static class DRIVETRAIN 
    {
        /** Modular Instancization Boolean */
        public static final Boolean AUTOMATIC_INSTANCIZATION = false;

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
}
