package org.firstinspires.ftc.teamcode.team.full_auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Full Auto", group="11285")
@SuppressWarnings("unused")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Driver driver = new Driver(this, telemetry, hardwareMap);
        waitForStart();
        driver.start();
    }
}
