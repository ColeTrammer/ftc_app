package org.firstinspires.ftc.teamcode.cole;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//package org.firstinspires.ftc.teamcode.cole;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
///**
// * Created by student on 1/12/19.
// */
//
@Autonomous(name="Auto11285GoDown",group="11285")
public class Auto11285GoDown extends LinearOpMode {
    //
    DcMotor lift;
    Servo claim;

    private static final float LIFT_RACK_LENGTH = 17f;
    private static final float LIFT_RACK_INITIAL_POSITION = 2.5f;
    private static final float LIFT_PINION_RADIUS = 1.25f;
    private static final int ENCODERS_IN_ONE_ROTATION = 1120;

    volatile boolean keepGoing = false;

    //
    public void runOpMode() {
        claim = hardwareMap.get(Servo.class, "claim");
        lift = hardwareMap.get(DcMotor.class, "lift");
        XOmniDrive drive = new XOmniDrive(hardwareMap);
        drive.telemetry = telemetry;
        waitForStart();

//        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        int position = (int) Math.round((LIFT_RACK_LENGTH - LIFT_RACK_INITIAL_POSITION) / 2 / LIFT_PINION_RADIUS * ENCODERS_IN_ONE_ROTATION / 2 / Math.PI);
//        lift.setTargetPosition(position);
//        lift.setPower(0.8f);
//        while(lift.isBusy())
//
//        {
//            telemetry.addData("Lift Encoder", lift.getCurrentPosition());
//            telemetry.update();
//        }
//        lift.setPower(0);
//
//        drive.forward(6);
//
//        lift.setTargetPosition(0);
//        lift.setPower(0.8f);
//        while(lift.isBusy())
//
//        {
//            telemetry.addData("Lift Encoder", lift.getCurrentPosition());
//            telemetry.update();
//        }
//        lift.setPower(0);

        lift.setPower(1);

//        try {
//            wait(2000);
//        } catch (Exception e) {
//            try {
//                wait(2000);
//            } catch (Exception ee) {}
//        }

        new Thread(new Runnable() {
            public void run()
        {
            try {
                Thread.sleep(6000);
            } catch (Exception e) {}
            keepGoing = true;
        }}).start();
        while (keepGoing) {

        }

        lift.setPower(0);

        drive.left(6);

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
