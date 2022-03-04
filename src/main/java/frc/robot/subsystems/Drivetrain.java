// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants; //constant

import java.util.Map;
import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP; //motors

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


public class Drivetrain extends SubsystemBase {
  ///// Motors
  private static final VictorSP m_frontLeftMotor = new VictorSP(DriveConstants.kFrontLeftMotor);
  private static final VictorSP m_rearLeftMotor = new VictorSP(DriveConstants.kRearLeftMotor);
  private static final VictorSP m_frontRightMotor = new VictorSP(DriveConstants.kFrontRightMotor);
  private static final VictorSP m_rearRightMotor = new VictorSP(DriveConstants.kRearRightMotor);

  private static final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeftMotor, m_rearLeftMotor);
  private static final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRightMotor, m_rearRightMotor);

  private static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  /////

  public static double tempSpeed;

  private final ShuffleboardTab m_tab = Shuffleboard.getTab("Main");
  private final NetworkTableEntry m_maxSpeed;
  

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    //configure stuff here
    m_leftMotors.setInverted(false);
    m_rightMotors.setInverted(false);

    m_drive.setDeadband(.1);

    m_maxSpeed = m_tab.add("Max Speed", 1.0).withPosition(2, 2).withSize(2, 1).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
    m_maxSpeed.setDouble(DriveConstants.kDefaultSpeed);
  }

  public void tankDrive(double m_leftMotors, double m_rightMotors){
    m_drive.tankDrive(m_leftMotors, m_rightMotors);
  }

  public void setMaxSpeed(double maxSpeed) {
    m_drive.setMaxOutput(maxSpeed);
  }

  public void stopDrive(){
    m_drive.stopMotor();
  }

//drive speeds
/*
  public void slowDriveSpeed() {
    tempSpeed = m_maxSpeed.getDouble(1.0);
    m_maxSpeed.setDouble(tempSpeed * DriveConstants.kSlowDriveSpeed);
  }
*/
  /**
   * Returns the drive speed to the original speed.
   */
  public void normalDriveSpeed() {
    m_maxSpeed.setDouble(tempSpeed);
  }
 ////////
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
