
/*

   BasketGun
   Current version: 0.1
   Created by 7eventeen

 */

import menu.MainMenu;

public class Launcher {

    private static final String title = "Basket Gun";

    private static final int width = 1280;
    private static final int height = 720;

    public static void main(String... args) {

        MainMenu ui = new MainMenu(title, width, height);
        ui.initUi();

    }
}


