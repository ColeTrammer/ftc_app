package org.firstinspires.ftc.teamcode.cole;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by student on 11/29/18.
 */

@Autonomous(name="Auto 11285")
public class Auto11285 extends LinearOpMode {
    DcMotor lift;
    @Override
    public void runOpMode() throws InterruptedException {
        lift = hardwareMap.get(DcMotor.class, "lift");
        XOmniDrive drive = new XOmniDrive(hardwareMap);
        waitForStart();

        lift.setPower(0.75f);
        wait(2000);
        lift.setPower(0);

        drive.forward(6);

        lift.setPower(-0.75f);
        wait(2000);
        lift.setPower(0);
    }
}
