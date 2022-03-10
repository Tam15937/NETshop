package launcher;



import utils.AppData;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {

        AppData appData=new AppData();
        Authorization authorization=new Authorization();
        authorization.authorizationMenu();
    }
}
