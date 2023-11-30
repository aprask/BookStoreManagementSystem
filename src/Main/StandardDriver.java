package Main;

import Main.Misc.Exit;
import Store.StandardStore;

import java.io.IOException;

public class StandardDriver
{
    public static void main(String[] args) throws IOException {
        StandardStore standardStore = new StandardStore();
        Exit exit = new Exit();
        exit.exitProgram();
    }
}