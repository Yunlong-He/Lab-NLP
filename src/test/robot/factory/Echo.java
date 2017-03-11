package test.robot.factory;

import test.robot.Robot;

/**
 * Created by heyunlong on 2017/3/11.
 */
public class Echo implements Robot {

    public void powerOn(){}

    public void powerDown(){}

    public String handleCommand(String cmd) {
        return cmd;
    }

}
