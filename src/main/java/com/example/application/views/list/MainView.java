package com.example.application.views.list;

import com.example.application.Employee;
import com.example.application.Floor;
import com.example.application.Room;
import com.example.application.Table;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@CssImport("custom-styles.css")
@PageTitle("АИС Haulmont")
@Route("")
public class MainView extends VerticalLayout {

  private final HorizontalLayout menu = new HorizontalLayout();
  private final H1 choose = new H1("CHOOSE");
  private final H1 the = new H1("THE");
  private final H1 floor = new H1("FLOOR");

  private final H1 room = new H1("ROOM");
  private final H1 workplace = new H1("WORKPLACE");
  /**
   * Кнопка добавления кабинета
   */
  private final Button addRoomButton = new Button("ADD");
  /**
   * Кнопка удаления кабинета
   */
  private final Button deleteRoomButton = new Button("DELETE");
  /**
   * Кнопка добавления рабочего места
   */
  private final Button addWorkplaceButton = new Button("ADD");
  /**
   * Кнопка удаления рабочего места
   */
  private final Button deleteWorkplaceButton = new Button("DELETE");
  /**
   * Список этажей
   */
  private final List<Floor> floors = List.of(new Floor(1), new Floor(2), new Floor(3));
  /**
   * Выбранный на данный момент этаж
   */
  private int currFloor;
  /**
   * Выбранный на данный момент кабинет
   */
  private Room currRoom;
  /**
   * Указатель на то, была ли нажата кнопка удаления (был ли включен режим удаления)
   */
  private boolean isDeleteActive = false;

  public MainView() {
    configureClasses();
    configureListeners();

    // Добавление на экран начальной надписи
    menu.add(choose, the, floor);
    // Добавление на экран кнопок этажей
    menu.add(floors.get(0).getButton(), floors.get(1).getButton(), floors.get(2).getButton());
    // Добавление на главный экран основного меню
    add(menu);
    setSizeFull();
    setAlignItems(Alignment.STRETCH);
    setClassName("main");
  }

  /**
   * Присвоение css классов компонентам
   */
  private void configureClasses() {
    menu.setClassName("menu");
    choose.setClassName("choose");
    the.setClassName("the");
    floor.setClassName("floor");
    room.setClassName("room");
    workplace.setClassName("room");
    addRoomButton.setClassName("add-button");
    deleteRoomButton.setClassName("delete-button");
    addWorkplaceButton.setClassName("add-button");
    deleteWorkplaceButton.setClassName("delete-button");
  }

  /**
   * Создание обработчиков событий
   */
  private void configureListeners() {
    // Обработчик события нажатия кнопки первого этажа
    floors.get(0).getButton().addClickListener(buttonClickEvent -> {
      // Задание текущего этажа
      currFloor = 0;
      // Удаление кнопок этажей
      removeAllFloors();
      // Изменение последнего слова надписи на заднем фоне
      menu.add(room);
      // Добавление кнопок добавления и удаления кабинетов
      menu.add(addRoomButton);
      menu.add(deleteRoomButton);
      // Добавление на экран всех кабинетов текущего этажа
      showRooms();
    });

    floors.get(1).getButton().addClickListener(buttonClickEvent -> {
      // Задание текущего этажа
      currFloor = 1;
      // Удаление кнопок этажей
      removeAllFloors();
      // Изменение последнего слова надписи на заднем фоне
      menu.add(room);
      // Добавление кнопок добавления и удаления кабинетов
      menu.add(addRoomButton);
      menu.add(deleteRoomButton);
      // Добавление на экран всех кабинетов текущего этажа
      showRooms();
    });

    floors.get(2).getButton().addClickListener(buttonClickEvent -> {
      // Задание текущего этажа
      currFloor = 2;
      // Удаление кнопок этажей
      removeAllFloors();
      // Изменение последнего слова надписи на заднем фоне
      menu.add(room);
      // Добавление кнопок добавления и удаления кабинетов
      menu.add(addRoomButton);
      menu.add(deleteRoomButton);
      // Добавление на экран всех кабинетов текущего этажа
      showRooms();
    });
    // Обработчик события нажатия кнопки добавления кабинета
    addRoomButton.addClickListener(buttonClickEvent -> {
      var dialog = new Dialog();
      var formLayout = new FormLayout();
      dialog.setHeight("25%");
      var numberField = new TextField("Room number");
      formLayout.add(numberField);
      var addButton = new Button("ADD");
      addButton.setClassName("add-button");
      addButton.addClickListener(buttonClickEvent1 -> {
        floors.get(currFloor).getRooms().add(new Room(Integer.parseInt(numberField.getValue())));
        floors.get(currFloor).getRooms()
            .forEach(aRoom -> aRoom.getButton().addClickListener(aButtonEvent -> {
              currRoom = aRoom;
              if (isDeleteActive) {
                aRoom.getButton().setClassName("big-button-delete-stub");
                return;
              }
              removeAllRooms();
              menu.add(workplace);
              menu.remove(addRoomButton);
              menu.remove(deleteRoomButton);
              menu.add(addWorkplaceButton);
              menu.add(deleteWorkplaceButton);
            }));
        showRooms();
        dialog.close();
      });
      dialog.add(formLayout, addButton);
      dialog.open();
      menu.remove(floors.get(0).getButton());
      menu.remove(floors.get(1).getButton());
      menu.remove(floors.get(2).getButton());
    });

    deleteRoomButton.addClickListener(buttonClickEvent -> {
      isDeleteActive = !isDeleteActive;
      for (var iterator = floors.get(currFloor).getRooms().iterator(); iterator.hasNext(); ) {
        var aRoom = iterator.next();
        var roomButton = aRoom.getButton();
        if (isDeleteActive) {
          roomButton.setClassName("big-button-delete");
        } else {
          if ("big-button-delete-stub".equals(aRoom.getButton().getClassName())) {
            menu.remove(roomButton);
            iterator.remove();
          }
          roomButton.setClassName("big-button");
        }
      }
    });

    floors.get(currFloor).getRooms()
        .forEach(aRoom -> aRoom.getButton().addClickListener(aButtonEvent -> {
          currRoom = aRoom;
          if (isDeleteActive) {
            aRoom.getButton().setClassName("big-button-delete-stub");
            return;
          }
          removeAllRooms();
        }));

    addWorkplaceButton.addClickListener(buttonClickEvent -> {
      var table = new Table(0, false, null);
      var dialog = table.getDialog();
      var formLayout = table.getFormLayout();
      dialog.add(formLayout);
      var numberField = table.getNumberField();
      table.setBookedByField(new Select<>(new Employee("Denis Mishin", "Java Developer"),
          new Employee("Nikita Ermochenko", "Junior Java Developer"),
          new Employee("Artyom Bazanov", "Manager"),
          new Employee("Anton Yudin", "Cleaner")));
      var bookedByField = table.getBookedByField();
      bookedByField.setLabel("Booked by");
      bookedByField.setEmptySelectionAllowed(true);
      formLayout.add(numberField);
      formLayout.add(bookedByField);
      var addButton = new Button("ADD");
      addButton.setClassName("add-button");
      addButton.addClickListener(buttonClickEvent1 -> {
        var employee = bookedByField.getValue();
        table.getButton().setText(numberField.getValue());
        table.setNum(Integer.parseInt(numberField.getValue()));
        table.setFree(Objects.isNull(employee));
        table.getBookedByField().setValue(employee);
        currRoom.getTables().add(table);
        currRoom.getTables().forEach(aTable -> aTable.getButton().addClickListener(aButtonEvent -> {
          if (isDeleteActive) {
            aTable.getButton().setClassName("big-button-delete-stub");
            return;
          }
          if (!aTable.getDialog().isOpened()) {
            aTable.getDialog().open();
          }
        }));

        showTables();
        dialog.close();
        dialog.remove(addButton);
      });
      dialog.add(formLayout, addButton);
      dialog.open();
      menu.remove(currRoom.getButton());
    });

    deleteWorkplaceButton.addClickListener(buttonClickEvent -> {
      isDeleteActive = !isDeleteActive;
      if (isDeleteActive) {
        currRoom.getTables().stream().map(Table::getButton).forEach(button -> button.setClassName("big-button-delete"));
      } else {
        for (var iterator = currRoom.getTables().iterator(); iterator.hasNext(); ) {
          var aTable = iterator.next();
          var tableButton = aTable.getButton();
          if ("big-button-delete-stub".equals(aTable.getButton().getClassName())) {
            menu.remove(tableButton);
            iterator.remove();
          }
        }
        showTables();
      }
    });
  }

  private void removeAllFloors() {
    // Изменение класса у кнопок этажей для анимированного удаления
    floors.forEach(aFloor -> aFloor.getButton().setClassName("big-button-stub"));
    // Изменение класса у надписи для изменения последнего слова надписи
    floor.setClassName("floor-stub");
  }

  private void removeAllRooms() {
    // Изменение класса у кнопок кабинетов для анимированного удаления
    floors.get(currFloor).getRooms().forEach(aRoom -> aRoom.getButton().setClassName("big-button-stub"));
    // Изменение класса у надписи для изменения последнего слова надписи
    room.setClassName("floor-stub");

  }

  private void showRooms() {
    // Добавление на экран всех кабинетов текущего этажа
    floors.get(currFloor).getRooms().forEach(room1 -> menu.add(room1.getButton()));
  }

  private void showTables() {
    currRoom.getTables().sort(Comparator.comparingInt(value -> Integer.parseInt(value.getButton().getText())));
    currRoom.getTables().stream().map(Table::getButton).forEach(menu::remove);
    currRoom.getTables().forEach(table -> {
      if (table.isFree()) {
        table.getButton().setClassName("big-button");
      } else {
        table.getButton().setClassName("big-button-booked");
      }
      menu.add(table.getButton());
    });
  }

}