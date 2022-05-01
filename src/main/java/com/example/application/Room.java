package com.example.application;

import com.vaadin.flow.component.button.Button;
import java.util.ArrayList;
import java.util.List;

public class Room {
  private final int num;
  private final Button button;
  private final List<Table> tables;

  public Room(int num) {
    this.num = num;
    this.button = new Button(String.valueOf(num));
    this.button.setClassName("big-button");
    this.tables = new ArrayList<>();
  }

  public int getNum() {
    return num;
  }

  public Button getButton() {
    return button;
  }

  public List<Table> getTables() {
    return tables;
  }
}
