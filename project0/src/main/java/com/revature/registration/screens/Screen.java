package com.revature.registration.screens;

import java.io.BufferedReader;

public abstract class Screen {

    protected String name;
    protected String route;
    protected BufferedReader consoleReader;

    public Screen(String name, String route, BufferedReader consoleReader) {
        this.name = name;
        this.route = route;
        this.consoleReader = consoleReader;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    // TODO implement render() for remaining screens
    public abstract void render() throws Exception;

}
