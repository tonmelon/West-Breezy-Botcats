
package org.usfirst.frc.team3653.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Compressor compressor;
	Talon driveright;
	Talon driveleft;
	Victor shootertop;
	Talon shootermid;
	Victor shooterlow;
	Joystick player1;
	Joystick player2;
	Solenoid escalade;
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	compressor = new Compressor ();
    	driveleft = new Talon(4);
    	driveright = new Talon(5);
    	player2 = new Joystick(1);
    	player1 = new Joystick(0);
    	shootertop = new Victor(0);
    	shootermid= new Talon(1);
    	shooterlow= new Victor(2);
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if ((player1.getRawAxis(1)<=30) && (player1.getRawAxis(1)>=-30)) {driveleft.set(0);}
    	if ((player1.getRawAxis(5)<=30) && (player1.getRawAxis(5)>=-30)){driveright.set(0);}
    	driveleft.setInverted(true);
        driveleft.set(-player1.getRawAxis(5)*.5);
        driveright.set(-player1.getRawAxis(1)*.5);
        if (player2.getRawAxis(3)>0) {shooterlow.set(.5);}
        if (player2.getRawButton(1)==true){shootertop.set(.5);}
        if (player2.getRawButton(1)==true && player2.getRawButton(5)==true) {shootermid.set(.5); shooterlow.set(.5);shootertop.set(.5);}
            /*for(int i=0;i<4;i++){
            	if (player2.getRawButton(1) == true && i>=4) 
            	{shootermid.set(.5); shooterlow.set(.5);}*/
        else if(player2.getRawButton(1)==false) {shootertop.set(0);shootermid.set(0);shooterlow.set(0);}
        }
       
        
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	//driveleft.setInverted(true);
        //driveleft.set(-player1.getRawAxis(5)*.5);
        //driveright.set(-player1.getRawAxis(1)*.5);
        
    }
    
}
