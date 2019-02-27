package org.firstinspires.ftc.teamcode.team.full_auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    private static final int ENCODER_VALUE_PER_ROTATION = 1120;
    private static final float WHEEL_RADIUS = 4;
    private static final float ROBOT_RADIUS = 9.5f;

    private LinearOpMode opMode;
    private Telemetry telemetry;
    private DcMotor fl, fr, br, bl;
    private DcMotor lift;
    private Servo claim;
    private ComputerVision computerVision;

    Robot(LinearOpMode opMode, Telemetry telemetry, HardwareMap hardwareMap) {
        this.opMode = opMode;
        this.telemetry = telemetry;

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        lift = hardwareMap.get(DcMotor.class, "lift");
        claim = hardwareMap.get(Servo.class, "claim");
        claim.setPosition(0);

        resetEncoders();

        bl.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.REVERSE);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        computerVision = new ComputerVision(hardwareMap);
    }

    void forward(float inches) {
        int position = (int) Math.round(ENCODER_VALUE_PER_ROTATION * Math.sqrt(2) * inches / (2 * Math.PI * WHEEL_RADIUS));
        fl.setTargetPosition(position);
        fr.setTargetPosition(position);
        br.setTargetPosition(position);
        bl.setTargetPosition(position);
        fl.setPower(0.8f);
        fr.setPower(0.8f);
        br.setPower(0.8f);
        bl.setPower(0.8f);
        while (opMode.opModeIsActive() && fl.isBusy() && fr.isBusy() && br.isBusy() && bl.isBusy()) {
            debug();
        }
        fl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        resetEncoders();
    }

    void pivotRight(float degrees) {
        int position = (int) Math.round(ENCODER_VALUE_PER_ROTATION * (degrees * Math.PI / 180) * (2 * ROBOT_RADIUS) / (2 * Math.PI * WHEEL_RADIUS));
        fl.setTargetPosition(position);
        fr.setTargetPosition(-position);
        br.setTargetPosition(-position);
        bl.setTargetPosition(position);
        fl.setPower(0.8f);
        fr.setPower(0.8f);
        br.setPower(0.8f);
        bl.setPower(0.8f);
        while (opMode.opModeIsActive() && fl.isBusy() && fr.isBusy() && br.isBusy() && bl.isBusy()) {
            debug();
        }
        fl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        resetEncoders();
    }

    void findMineral() {
        while (true) {
            computerVision.getRecognitions();
        }
    }

    void stop() {
        fl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        lift.setPower(0);
        claim.setPosition(0);
        resetEncoders();
    }

    private void debug() {
        telemetry.addData("fl", fl.getCurrentPosition());
        telemetry.addData("fr", fr.getCurrentPosition());
        telemetry.addData("br", br.getCurrentPosition());
        telemetry.addData("bl", bl.getCurrentPosition());
        telemetry.update();
    }

    private void resetEncoders() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
