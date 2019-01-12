package org.firstinspires.ftc.teamcode.nathan;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TeleOp5135_V3", group="Iterative Opmode")
//@Disabled
public class TeleOp5135_V3 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor left = null;
    private DcMotor right = null;
    private DcMotor lift = null;
    private CRServo wrist = null;
    //private DcMotor fBucket = null;
    private CRServo collection = null;
    private CRServo bucket = null;
    private DcMotor extension = null;
    /* extension gamepad 1
    wrist on triggers return double
    extension on bumpers
    bucket gamepad 2

    */

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        wrist = hardwareMap.crservo.get("wrist");
        extension = hardwareMap.get(DcMotor.class, "extension");
        lift = hardwareMap.get(DcMotor.class, "lift");


        //lift = hardwareMap.get(DcMotor.class, "lift");
        bucket = hardwareMap.crservo.get("bucket");
        //fBucket = hardwareMap.get(DcMotor.class, "fBucket");
        //fBucket.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        extension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collection = hardwareMap.crservo.get("collection");
        //lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);
        //lift.setDirection(DcMotor.Direction.FORWARD);
       // fBucket.setDirection(DcMotor.Direction.FORWARD);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.addData("left", left.getPower());
        telemetry.addData("right", right.getPower());
        //telemetry.addData("lift", lift.getPower());
        telemetry.addData("collection", collection.getPower());
        //telemetry.addData("fBucket", fBucket.getPower());
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        //double leftPower;
        //double rightPower;

        // Choose to drive using either Tank Mode, or POV Mode
        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.
        /*double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        frontleft.setPower(v1);
        frontright.setPower(v2);
        backleft.setPower(v3);
        backright.setPower(v4);
        */

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double forward =  gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        double collect = gamepad2.left_trigger - gamepad2.right_trigger;
        double fBPower = gamepad2.right_stick_y;
        double wristPower = gamepad1.left_trigger - gamepad1.right_trigger;
        left.setPower(Range.clip(forward - turn, -1.0, 1.0));
        right.setPower(Range.clip(forward + turn, -1.0, 1.0));
        collection.setPower(0.7*(Range.clip(collect, -1.0, 1.0)));
      //  fBucket.setPower(0.5*(Range.clip(fBPower, -1.0, 1.0)))
        wrist.setPower(0.8*(Range.clip(wristPower, -1, 1)));


        if(gamepad2.left_stick_y >0.2)
            lift.setPower(1);
        else if(gamepad2.left_stick_y<-0.2)
            lift.setPower(-1);
        else
            lift.setPower(0);


//        if (gamepad2.a)
//            bucket.setPosition(2);
//        if (gamepad2.b)
//            bucket.setPosition(0);

        if (gamepad2.dpad_up)
            bucket.setPower(-1);
        else if (gamepad2.dpad_down)
            bucket.setPower(1);
        else
            bucket.setPower(0);

        if(gamepad1.right_bumper)
        {
            extension.setPower(.9);
        }
        else if(gamepad1.left_bumper)
        {
            extension.setPower(-.9);
        }
        else extension.setPower(0);


        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.
        // leftPower  = -gamepad1.left_stick_y ;
        // rightPower = -gamepad1.right_stick_y ;


        // Show the elapsed game time and wheel power.
//        telemetry.addData("Status", "Run Time: " + runtime.toString());
//        telemetry.addData("Motors", "left (%.2f), right (%.2f)");
     //   telemetry.addData("Lift Encoder", lift.getCurrentPosition());
        telemetry.update();
    }

    /*;
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}