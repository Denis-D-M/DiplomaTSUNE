package com.example.application;

import com.vaadin.flow.component.button.Button;
import java.util.ArrayList;
import java.util.List;

/**
 * Этаж
 */
public class Floor {

  /**
   * Кнопка этажа
   */
  private final Button button;
  /**
   * Номер этажа
   */
  private final int floor;
  /**
   * Список кабинетов на этаже
   */
  private final List<Room> rooms;

  public Floor(int floor) {
    this.floor = floor;
    this.button = new Button(String.valueOf(floor));
    this.button.setClassName("big-button");
    this.rooms = new ArrayList<>();
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
