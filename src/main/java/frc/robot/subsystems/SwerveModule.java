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
import frc.Constants.MODULE;

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
    private Double Module_Target_Rotation;
    private Double Module_Target_Velocity;
    private Double Module_Real_Rotation;
    private Double Module_Real_Velocity;

    /**
     * Swerve module constructor
     * @param Azimuth_Motor_Index - Index of the module's azimuth motor
     * @param Driving_Motor_Index - Index of the module's driving motor
     * @param CANCoder_Index - Index of the module's cancoder
     * @param CANCoder_Offset - Offset of the module's cancoder
     */
    public SwerveModule(Integer Driving_Motor_Index, Integer Azimuth_Motor_Index, Integer CANCoder_Index, Double CANCoder_Offset) 
    {
        //Hardware Instancization 
        Driving_Motor = new WPI_TalonFX(0);
        Azimuth_Motor = new WPI_TalonFX(Azimuth_Motor_Index);
        Module_Encoder = new CANCoder(CANCoder_Index);

        //Module Data
        Module_Target_Rotation = 0.0;
        Module_Target_Velocity = 0.0;
        Module_Real_Rotation = 0.0;
        Module_Real_Velocity = 0.0;

        //Encoder Configurations
        Module_Encoder.configFactoryDefault();
        Module_Encoder.configMagnetOffset(CANCoder_Offset);
        Module_Encoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
        Module_Encoder.setPositionToAbsolute();

        //Azimuth Motor Configurations
        Azimuth_Motor.configFactoryDefault();
        Azimuth_Motor.setInverted(TalonFXInvertType.CounterClockwise);
        Azimuth_Motor.setNeutralMode(NeutralMode.Brake);
        Azimuth_Motor.configRemoteFeedbackFilter(Module_Encoder, 0);
        Azimuth_Motor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
        Azimuth_Motor.configStatorCurrentLimit(MODULE.AZIMUTH_CURRENT_LIMIT);
        //TODO: --> Azimuth_Motor.setSelectedSensorPosition(FL_Position.getAbsolutePosition());
        Azimuth_Motor.config_kP(0, MODULE.AZIMUTH_KP);
        Azimuth_Motor.config_kI(0, MODULE.AZIMUTH_KI);
        Azimuth_Motor.config_kD(0, MODULE.AZIMUTH_KD);
        Azimuth_Motor.config_kF(0, MODULE.AZIMUTH_KF);

        //Drive Motor Configurations
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
    public void periodic()
    {

    }

    //MODULE MANIPULATION

    /**
     * Bring module to given velocity
     * @param Velocity - Speed for Module
     */
    public void toVelocity(Double Velocity) 
    {

    }

    /**
     * Bring module to given angle
     * @param Angle - Angle for Module
     */
    public void toAngle(Double Angle) 
    {

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

}
