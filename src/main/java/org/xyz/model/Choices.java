package org.xyz.model;

import java.util.*;

public enum Choices {

    HIT(1),
    STAND(2);
    final int choice;
    private Choices(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }
}
