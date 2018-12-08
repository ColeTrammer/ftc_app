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

    private static final float LIFT_RACK_LENGTH = 13f;
    private static final float LIFT_RACK_INITIAL_POSITION = 2.5f;
    private static final float LIFT_PINION_RADIUS = 1.25f;
    private static final int   ENCODERS_IN_ONE_ROTATION = 1120;

    @Override
    public void runOpMode() {
        //lift = hardwareMap.get(DcMotor.class, "lift");
//        XOmniDrive drive = new XOmniDrive(hardwareMap);
        waitForStart();
//        drive.motorTest();
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");

        try {
            fl.setPower(1);
            sleep(1000);
            fl.setPower(0);
            bl.setPower(1);
            sleep(1000);
            bl.setPower(0);
            br.setPower(1);
            sleep(1000);
            br.setPower(0);
            fr.setPower(1);
            sleep(1000);
            fr.setPower(0);
        } catch (Exception e) {}
//        lift.setPower(0.75f);
//        wait(2000);
//        lift.setPower(0);

        //lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        int position = (int) Math.round((LIFT_RACK_LENGTH - LIFT_RACK_INITIAL_POSITION) / 2 / LIFT_PINION_RADIUS * ENCODERS_IN_ONE_ROTATION / 2 / Math.PI);
//        lift.setTargetPosition(position);
//        lift.setPower(0.8f);
//        while (lift.isBusy()) {
//            telemetry.addData("Lift Encoder", lift.getCurrentPosition());
//            telemetry.update();
//        }
//        lift.setPower(0);
//
//        drive.forward(6);
//
//        lift.setTargetPosition(0);
//        lift.setPower(0.8f);
//        while (lift.isBusy()) {
//            telemetry.addData("Lift Encoder", lift.getCurrentPosition());
//            telemetry.update();
//        }
//        lift.setPower(0);

//        lift.setPower(-0.75f);
//        wait(2000);
//        lift.setPower(0);
    }
}
