package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CommonHooks {
    @Before("@signup and not @otroescenario")
    //@Before("@signup and @otroescenario")
    //@Before("@signup or @otroescenario")
    //@Before("(@signup or @otroescenario1) and (not @foo)")
    public void clearDataBase(){
        System.out.println("*********Me ejecuto antes del escenario y antes de background*********");
    }

    /*
    @After("@signup")
    public void afterHook(){
        System.out.println("*********Me ejecuto despues del escenario*********");
    }
    */
}
