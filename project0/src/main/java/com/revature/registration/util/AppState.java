package com.revature.registration.util;

public class AppState {

    private boolean appRunning;
    private ScreenRouter router;

    public void startup() {

        router.navigate("/welcome");

        while (appRunning) {
            try {
                router.getCurrentScreen().render();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
