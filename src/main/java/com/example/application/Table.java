package com.example.application;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class Table {

  private int num;
  private boolean isFree;
  private final Button button;
  private String bookedBy;

  private Dialog dialog = new Dialog();

  private FormLayout formLayout = new FormLayout();

  private TextField numberField = new TextField("Workplace number");

  private Select<Employee> bookedByField;

  public Table(int num, boolean isFree, String bookedBy) {
    this.num = num;
    this.isFree = isFree;
    this.button = new Button(String.valueOf(num));
    this.button.setClassName("big-button");
    this.bookedBy = bookedBy;
    this.dialog.setHeight("35%");
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

  public Dialog getDialog() {
    return dialog;
  }

  public FormLayout getFormLayout() {
    return formLayout;
  }

  public TextField getNumberField() {
    return numberField;
  }

  public Select<Employee> getBookedByField() {
    return bookedByField;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public void setFree(boolean free) {
    isFree = free;
  }

  public void setBookedBy(String bookedBy) {
    this.bookedBy = bookedBy;
  }

  public void setBookedByField(Select<Employee> bookedByField) {
    this.bookedByField = bookedByField;
  }
}
