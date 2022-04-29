package com.example.application;

import com.vaadin.flow.component.button.Button;
import java.util.List;

public enum Floors {
  FIRST(1),
  SECOND(2),
  THIRD(3);

  private final Button button;
  private final int floor;

  private List<Room> rooms;

  Floors(int floor) {
    this.floor = floor;
    this.button = new Button(String.valueOf(floor));
    this.button.setClassName("big-button");
  }

  public Button getButton() {
    return button;
  }

  public int getFloor() {
    return floor;
  }

  public List<Room> getRooms() {
    return rooms;
  }
}
