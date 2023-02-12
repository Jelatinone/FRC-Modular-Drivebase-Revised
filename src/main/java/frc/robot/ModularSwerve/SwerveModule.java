//Package
package frc.robot.ModularSwerve;

//Libraries
import frc.robot.Constants.MODULE;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import java.util.Objects;

/** 
 * A swerveDrive module class built to act as an independent module of
 * the Swere drivetrain. 
 * @author - Jelatinone
 * @version - 1.0
 * */
public class SwerveModule
{

    //Module hardware
    private MODULE.DRIVING_MOTOR_TYPE Driving_Motor;
    private MODULE.AZIMUTH_MOTOR_TYPE Azimuth_Motor;
    private MODULE.ENCODER_TYPE Module_Encoder;

    //Module data
    private ModuleVector Real_Vector;
    private ModuleVector Expected_Vector;

    /** 
    * Constructor
    * @param Driving_Motor_Index - Index of module's driving motor
    * @param Azimuth_Motor_Index - Index of module's azimuth motor
    * @param Encoder_Index - Index of module's encoder
    * @param First_Encoder_Position - Position of first(Front-Left) module's encoder (Raw Sensor Units)
    */
    public SwerveModule(int Driving_Motor_Index, int Azimuth_Motor_Index, int Encoder_Index, double Encoder_Offset, double First_Encoder_Position)
    {
        //Instance module hardware
        Driving_Motor = new MODULE.DRIVING_MOTOR_TYPE(Driving_Motor_Index);
        Azimuth_Motor = new MODULE.AZIMUTH_MOTOR_TYPE(Azimuth_Motor_Index);
        Module_Encoder = new MODULE.ENCODER_TYPE(Encoder_Index);

        //Instance module data
        Real_Vector = new ModuleVector();
        Expected_Vector = new ModuleVector();

        //Encoder configurations
        Module_Encoder.configFactoryDefault();
        Module_Encoder.configMagnetOffset(Encoder_Offset);
        Module_Encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
        Module_Encoder.setPositionToAbsolute();

        //Drive motor configurations
        Driving_Motor.configFactoryDefault();
        Driving_Motor.setInverted(TalonFXInvertType.CounterClockwise);
        Driving_Motor.setNeutralMode(NeutralMode.Brake);
        //Driving_Motor.configStatorCurrentLimit(MODULE.DRIVE_CURRENT_LIMIT);
        //Driving_Motor.config_kP(0, MODULE.AZIMUTH_KP);
        //Driving_Motor.config_kI(0, MODULE.AZIMUTH_KI);
        //Driving_Motor.config_kD(0, MODULE.AZIMUTH_KD);
        //Driving_Motor.config_kF(0, MODULE.AZIMUTH_KF);
        //Driving_Motor.configMotionSCurveStrength(MODULE.DRIVE_S_CURVE_STRENGTH, 0);

        //Azimuth motor configurations
        Azimuth_Motor.configFactoryDefault();
        Azimuth_Motor.setInverted(TalonFXInvertType.CounterClockwise);
        Azimuth_Motor.setNeutralMode(NeutralMode.Brake);
        Azimuth_Motor.configRemoteFeedbackFilter(Module_Encoder, 0);
        Azimuth_Motor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
        //Azimuth_Motor.configStatorCurrentLimit(MODULE.AZIMUTH_CURRENT_LIMIT);
        Azimuth_Motor.setSelectedSensorPosition(First_Encoder_Position);
        //Azimuth_Motor.config_kP(0, MODULE.AZIMUTH_KP);
        //Azimuth_Motor.config_kI(0, MODULE.AZIMUTH_KI);
        //Azimuth_Motor.config_kD(0, MODULE.AZIMUTH_KD);
        //Azimuth_Motor.config_kF(0, MODULE.AZIMUTH_KF);
        //Driving_Motor.configMotionSCurveStrength(MODULE.AZIMUTH_S_CURVE_STRENGTH, 0);

    }

    /** A vector wrapper class built to perform simple vector math for the SwerveModule class. */
    public class ModuleVector
    {
        /** Vector's X(Adjacent) side length relative to angle */
        protected double Length_X;
        /** Vector's Y(Opposite) side length relative to angle */
        protected double Length_Y;

        /** Constructor. */
        public ModuleVector()
        {
            this.Length_X = 0.0;
            this.Length_Y = 0.0;
        }
        /** 
         * Constructor. 
         * @param x - The vector's X(Adjacent) side length
         * @param y - The vector's Y(Opposite) side length
         * */
        public ModuleVector(double X,double Y)
        {
            this.Length_X = X;
            this.Length_Y = Y;
        }
    }

}
