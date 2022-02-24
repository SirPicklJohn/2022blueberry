// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends CommandBase {
  private final DoubleSupplier m_rightSpeed;
  private final DoubleSupplier m_leftSpeed;
  private final Drivetrain m_drive;

  /** Creates a new TankDrive. */
  public TankDrive(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, Drivetrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.tankDrive(m_leftSpeed.getAsDouble(), m_rightSpeed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
