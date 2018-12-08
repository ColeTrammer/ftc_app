package org.firstinspires.ftc.teamcode.cole;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by student on 11/29/18.
 */

@TeleOp(name="11285 TeleOp")
public class TeleOp11285 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        XOmniDrive drive = new XOmniDrive(hardwareMap);
        DcMotor lift = hardwareMap.get(DcMotor.class, "lift");

        //telemetry.addData("Lift Encoder", lift.getCurrentPosition());
        //telemetry.update()

        waitForStart();

        while (opModeIsActive()) {
            drive.run(gamepad1, gamepad2);
            if (gamepad1.a) {
                lift.setPower(1);
            } else if (gamepad1.b) {
                lift.setPower(-1);
            } else {
                lift.setPower(0);
            }
        }
    }
}