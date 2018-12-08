package org.firstinspires.ftc.teamcode.cole;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by student on 12/1/18.
 */

@Autonomous(name="11285 Claim Depot", group="11285")
public class Auto11285ClaimDepot extends LinearOpMode {
    private Servo claim;

    @Override
    public void runOpMode() {
        claim = hardwareMap.get(Servo.class, "claim");
        XOmniDrive drive = new XOmniDrive(hardwareMap);
        drive.telemetry = telemetry;
        waitForStart();

        drive.forward(30 * (float) Math.sqrt(2));
        claim.setPosition(1);
        for (int i = 0; i < 5; i++) {
            drive.cClockwise(3);
            drive.clockwise(3);
        }
        claim.setPosition(0);
        try {
            sleep(1000);
        } catch (Exception e) {}
    }
}
