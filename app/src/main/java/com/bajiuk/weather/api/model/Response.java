
package com.bajiuk.weather.api.model;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private Main main;
    private String name;

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
