package Main.Misc;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Exit
{
    public Exit()
    {

    }
    public void exitProgram() throws IOException {
        String URL = "src/Main/Misc/sales_report.html";
        File file = new File(URL);
        Desktop.getDesktop().browse(file.toURI());
    }
}
