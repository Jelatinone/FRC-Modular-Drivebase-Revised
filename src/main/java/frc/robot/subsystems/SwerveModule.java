//Root Package
package frc.robot.subsystems;

//Libraries
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MODULE;

import java.util.Objects;

/**
 * Swerve drive module class
 */
public class SwerveModule extends SubsystemBase
{
    //Module hardware
    private WPI_TalonFX Driving_Motor;
    private WPI_TalonFX Azimuth_Motor;
    private CANCoder Module_Encoder;

    //Module data
    private Integer Module_Number;
    private Double Module_Target_Rotation;
    private Double Module_Target_Velocity;
    private Double Module_Real_Rotation;
    private Double Module_Real_Velocity;

    //Class data
    private static Integer Module_Count = 0;
    private static CANCoder First_Module_Encoder;

    /**
     * Swerve module constructor
     * @param Azimuth_Motor_Index - Index of the module's azimuth motor
     * @param Driving_Motor_Index - Index of the module's driving motor
     * @param CANCoder_Index - Index of the module's cancoder
     * @param CANCoder_Offset - Offset of the module's cancoder
     */
    public SwerveModule(int Driving_Motor_Index, int Azimuth_Motor_Index, int CANCoder_Index, double CANCoder_Offset) 
    {
        //Hardware instancization 
        Driving_Motor = new WPI_TalonFX(Driving_Motor_Index);
        Azimuth_Motor = new WPI_TalonFX(Azimuth_Motor_Index);
        Module_Encoder = new CANCoder(CANCoder_Index);

        //Module data
        Module_Number = Module_Count;
        Module_Target_Rotation = 0.0;
        Module_Target_Velocity = 0.0;
        Module_Real_Rotation = 0.0;
        Module_Real_Velocity = 0.0;

        //Class data
        Module_Count++;
        if(Objects.equals(Module_Count,0)) {First_Module_Encoder = Module_Encoder;}

        //Encoder configurations
        Module_Encoder.configFactoryDefault();
        Module_Encoder.configMagnetOffset(CANCoder_Offset);
        Module_Encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
        Module_Encoder.setPositionToAbsolute();

        //Azimuth motor configurations
        Azimuth_Motor.configFactoryDefault();
        Azimuth_Motor.setInverted(TalonFXInvertType.CounterClockwise);
        Azimuth_Motor.setNeutralMode(NeutralMode.Brake);
        Azimuth_Motor.configRemoteFeedbackFilter(Module_Encoder, 0);
        Azimuth_Motor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
        Azimuth_Motor.configStatorCurrentLimit(MODULE.AZIMUTH_CURRENT_LIMIT);
        Azimuth_Motor.setSelectedSensorPosition(First_Module_Encoder.getAbsolutePosition());
        Azimuth_Motor.config_kP(0, MODULE.AZIMUTH_KP);
        Azimuth_Motor.config_kI(0, MODULE.AZIMUTH_KI);
        Azimuth_Motor.config_kD(0, MODULE.AZIMUTH_KD);
        Azimuth_Motor.config_kF(0, MODULE.AZIMUTH_KF);

        //Drive motor configurations
        Driving_Motor.configFactoryDefault();
        Driving_Motor.setInverted(TalonFXInvertType.CounterClockwise);
        Driving_Motor.setNeutralMode(NeutralMode.Brake);
        Driving_Motor.configStatorCurrentLimit(MODULE.DRIVE_CURRENT_LIMIT);
        Driving_Motor.config_kP(0, MODULE.AZIMUTH_KP);
        Driving_Motor.config_kI(0, MODULE.AZIMUTH_KI);
        Driving_Motor.config_kD(0, MODULE.AZIMUTH_KD);
        Driving_Motor.config_kF(0, MODULE.AZIMUTH_KF);
    }

    /**
     * Swerve module periodic operations
     */
    @Override
    public void periodic(){}

    //MODULE MANIPULATION

    /**
     * Bring module to given velocity
     * @param Velocity - Speed for Module
     */
    public void toVelocity(Double Velocity) 
    {
        //Update module azimuth data
        Module_Real_Velocity = 2.0*(((Driving_Motor.getSelectedSensorVelocity() / 4096) * 10) / MODULE.DRIVE_GEAR_RATIO) * Math.PI * MODULE.WHEEL_DIAMETER;
        Module_Target_Velocity = Velocity;

        //Update motor
        Driving_Motor.set(ControlMode.Velocity, (Module_Real_Velocity*MODULE.DRIVE_GEAR_RATIO/(Math.PI * MODULE.WHEEL_DIAMETER)*4096)/10);
    }

    /**
     * Bring module to given angle
     * @param Angle - Angle for Module
     */
    public void toAngle(Double Angle) 
    {
        //Update module driving data
        Module_Real_Rotation = ((Azimuth_Motor.getSelectedSensorPosition() / 4096) * 360) % 360;
        Module_Target_Rotation = optimizeAzimuthRotation(Angle, Module_Real_Rotation);

        //Update motor
        Azimuth_Motor.set(ControlMode.Position, ((Module_Target_Rotation + (Module_Real_Rotation - (Module_Real_Rotation % 360))) / 360) * 4096);
    }

    /**
     * Bring module to given magnitude
     * @param Velocity - Speed for Module
     * @param Angle - Angle for Module
     */
    public void toMagnitude(Double Velocity, Double Angle)
    {
        //Update module data
        Module_Real_Rotation = ((Azimuth_Motor.getSelectedSensorPosition() / 4096) * 360) % 360;
        Module_Target_Rotation = optimizeAzimuthRotation(Angle, Module_Real_Rotation);
        Module_Real_Velocity = 2.0*(((Driving_Motor.getSelectedSensorVelocity() / 4096) * 10) / MODULE.DRIVE_GEAR_RATIO) * Math.PI * MODULE.WHEEL_DIAMETER;
        Module_Target_Velocity = Velocity;

        //Update motors
        Driving_Motor.set(ControlMode.Velocity, (Module_Real_Velocity*MODULE.DRIVE_GEAR_RATIO/(Math.PI * MODULE.WHEEL_DIAMETER)*4096)/10);
        Azimuth_Motor.set(ControlMode.Position, ((Module_Target_Rotation + (Module_Real_Rotation - (Module_Real_Rotation % 360))) / 360) * 4096);
    }

    /**
     * Bring module to exact given velocity
     * @param Velocity - Speed for Module
     */
    public void directToVelocity(Double Velocity) {Driving_Motor.set(ControlMode.Velocity,Velocity);}

    /**
     * Bring module to exact given angle
     * @param Angle - Angle for Module
     */
    public void directToAngle(Double Angle) {Azimuth_Motor.set(ControlMode.Position,Angle);}

    /** Finds the closest equivalent position to the target 
     * @param Target -Tthe target direction of the module (deg)
     * @param Real - The current direction of the module (deg)
     * @return The corrected target position of the motor
     */
    private double optimizeAzimuthRotation(double Target, double Real) 
    {
        if (Math.min(Math.min(Math.abs(Target - Real), Math.abs((Target + 360) - Real)), Math.abs((Target - 360) - Real)) == Math.abs((Target + 360) - Real)) Target += 360;
        if (Math.min(Math.min(Math.abs(Target - Real), Math.abs((Target + 360) - Real)), Math.abs((Target - 360) - Real)) == Math.abs((Target - 360) - Real)) Target -= 360;
        return Target;
    }

    //ACCESSORS

    /**
     * @return the swerve module's encoder
     */
    public CANCoder getEncoder() {return Module_Encoder;}

    /**
     * @return the swerve module's driving motor
     */
    public WPI_TalonFX getDrive() {return Driving_Motor;}

    /**
     * @return the swerve module's azimuth motor
     */
    public WPI_TalonFX getAzimuth() {return Azimuth_Motor;}

    /**
     * @return the swerve module's current target rotation(Position)
     */
    public Integer getModuleNumber() {return Module_Number;}

    /**
     * @return the swerve module's current target rotation(Position)
     */
    public Double getTargetRotation() {return Module_Target_Rotation;}

    /**
     * @return the swerve module's current target velocity
     */
    public Double getTargetVelocity() {return Module_Target_Velocity;}

    /**
     * @return the swerve module's current actual rotation(Position)
     */
    public Double getRealRotation() {return Module_Real_Rotation;}

    /**
     * @return the swerve module's current actual velocity
     */
    public Double getRealVelocity() {return Module_Real_Velocity;}

    /**
     * @return the class's instance count
     */
    public Integer getModuleCount() {return Module_Count;}


}
