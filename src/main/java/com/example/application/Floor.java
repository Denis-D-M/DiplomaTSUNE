package com.example.application;

import com.vaadin.flow.component.button.Button;
import java.util.List;

public class Floor {

  private final Button button;
  private final int floor;

  private List<Room> rooms;

  public Floor(int floor) {
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
