package com.example.application;

import com.vaadin.flow.component.button.Button;
import java.util.ArrayList;
import java.util.List;

public class Table {

  private final int num;
  private final boolean isFree;
  private final Button button;
  private final String bookedBy;
  private final List<String> equip;

  public Table(int num, boolean isFree, String bookedBy) {
    this.num = num;
    this.isFree = isFree;
    this.button = new Button(String.valueOf(num));
    this.button.setClassName("big-button");
    this.bookedBy = bookedBy;
    this.equip = new ArrayList<>();
  }

  public int getNum() {
    return num;
  }

  public boolean isFree() {
    return isFree;
  }

  public Button getButton() {
    return button;
  }

  public String getBookedBy() {
    return bookedBy;
  }

  public List<String> getEquip() {
    return equip;
  }
}
