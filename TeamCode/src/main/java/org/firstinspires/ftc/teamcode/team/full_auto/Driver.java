package org.firstinspires.ftc.teamcode.team.full_auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Driver {
    private LinearOpMode opMode;
    private State state;
    private Robot robot;

    public Driver(LinearOpMode opMode, Telemetry telemetry, HardwareMap hardwareMap) {
        this.opMode = opMode;
        this.state = State.SEARCHING;
        this.robot = new Robot(opMode, telemetry, hardwareMap);
    }

    void start() {
        while (opMode.opModeIsActive()) {
            switch (state) {
                case SEARCHING:
                    robot.findMineral();
                    state = State.IDLE;
                    break;
                case PUSHING:
                    break;
                case ENDGAME:
                    break;
                case HANG:
                    break;
                case IDLE:
                    robot.stop();
                    break;
            }
        }
    }

    private enum State {
        SEARCHING, PUSHING, ENDGAME, HANG, IDLE
    }
}
