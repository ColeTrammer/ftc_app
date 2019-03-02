package org.firstinspires.ftc.teamcode.team.full_auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Driver {
    private static final float FIELD_SIZE = 144;
    private static final float FIELD_TILE_SIZE = FIELD_SIZE / 6f;

    private LinearOpMode opMode;
    @SuppressWarnings("unused")
    private Telemetry telemetry;
    private State state;
    private Robot robot;

    public Driver(LinearOpMode opMode, Telemetry telemetry, HardwareMap hardwareMap) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        this.state = State.SEARCHING;
        this.robot = new Robot(opMode, telemetry, hardwareMap, FIELD_SIZE - 2 * FIELD_TILE_SIZE - Robot.ROBOT_RADIUS, 2 * FIELD_TILE_SIZE, 0);
    }

    void start() {
        robot.init();
        while (opMode.opModeIsActive()) {
            switch (state) {
                case SEARCHING:
                    for (int i = 0; opMode.opModeIsActive() && i < 50000; i++) {
                        Position mineralPos = robot.findMineral();
                        if (mineralPos != null && Math.abs(mineralPos.getX() - robot.getCenter()) < 50f) {
                            state = State.PUSHING;
                            break;
                        }
                    }
                    robot.right(0.7f);
                    break;
                case PUSHING:
                    robot.pivotRight(robot.getOrientation());
                    robot.forward(FIELD_SIZE - FIELD_TILE_SIZE + Robot.ROBOT_RADIUS - robot.getPosition().getX());
                    robot.forward(-6);
                    robot.right(-2 * Robot.ROBOT_RADIUS);
                    robot.forward(6 + 2 * Robot.ROBOT_RADIUS);
                    robot.right(robot.getPosition().getY() - FIELD_TILE_SIZE / 2f);
                    state = State.IDLE;
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
