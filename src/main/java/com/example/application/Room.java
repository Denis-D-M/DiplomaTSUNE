package com.example.application;

import com.vaadin.flow.component.button.Button;
import java.util.ArrayList;
import java.util.List;

/**
 * Кабинет
 */
public class Room {

  /**
   * Номер кабинета
   */
  private final int num;
  /**
   * Кнопка кабинета
   */
  private final Button button;
  /**
   * Список рабочих мест кабинета
   */
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
