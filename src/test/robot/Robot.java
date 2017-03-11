package test.robot;

/**
 * Created by heyunlong on 2017/3/11.
 */
public interface Robot {

    void powerOn();

    void powerDown();

    String handleCommand(String cmd);

}
