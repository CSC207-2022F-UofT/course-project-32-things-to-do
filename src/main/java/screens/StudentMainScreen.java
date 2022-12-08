package screens;

import screens.calendar_scheduler.*;
import screens.login_registration.*;
import screens.task_management.todolist_screens.*;
import use_cases.task_management.todolist_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class StudentMainScreen extends JPanel implements ActionListener {

    /**
     * The selectable buttons on the main screen
     */
    JButton createTask;
    JButton todoList;
    JButton calendar;
    JButton progressTracker;
    JButton courses;
    JButton createCT;
    JButton scheduleCT;

    JButton logout;

    /**
     * Objects for connecting to the other screens
     */
    CardLayout cardLayout;
    JPanel screens;

    LogoutController logoutController;

    /**
     * The window of the main screen with buttons connecting to each use case
     */
    public StudentMainScreen(JPanel screens, CardLayout cardLayout, LogoutController controller) {

        this.cardLayout = cardLayout;
        this.screens = screens;
        this.logoutController = controller;

        // Create label for title of screen
        JLabel title = new JLabel("32 Things To Do");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        createTask = new JButton("New Task");
        todoList = new JButton("To Do List");
        calendar = new JButton("Calendar");
        progressTracker = new JButton("Progress Tracker");
        courses = new JButton("Courses");
        createCT = new JButton("New Collaborative Task");
        scheduleCT = new JButton("Schedule Collaborative Task");
        logout = new JButton("Logout");

        createTask.addActionListener(this);
        todoList.addActionListener(this);
        calendar.addActionListener(this);
        progressTracker.addActionListener(this);
        courses.addActionListener(this);
        createCT.addActionListener(this);
        scheduleCT.addActionListener(this);
        logout.addActionListener(this);

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(createTask);
        buttons.add(todoList);
        buttons.add(calendar);
        buttons.add(progressTracker);
        buttons.add(courses);
        buttons.add(createCT);
        buttons.add(scheduleCT);
        buttons.add(logout);

        // Add all components to the panel
        this.add(title);
        this.add(buttons);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Trigger the corresponding use case upon button click
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == createTask) {
            cardLayout.show(screens, "studentTaskCreate");
        }
        if (evt.getSource() == todoList) {
            // create to-do list screen (so it refreshes)
            ToDoListPresenter toDoListPresenter = new ToDoListPresenter();
            ToDoListInteractor toDoListInteractor = new ToDoListInteractor(toDoListPresenter);
            toDoListPresenter.setToDoListInput(toDoListInteractor);

            ToDoListScreen toDoListScreen = new ToDoListScreen(toDoListPresenter, screens, cardLayout);
            screens.add("toDoList", toDoListScreen);

            cardLayout.show(screens, "toDoList");
        }
        if (evt.getSource() == calendar) {
            // Refresh calendar screen
            CalendarPresenter calendarPresenter = new CalendarPresenter();
            WorkingHoursPresenter workingHoursPresenter = new WorkingHoursPresenter();
            WorkingHoursController workingHoursController = new WorkingHoursController();
            ToDoListInteractor toDoListInteractor = new ToDoListInteractor(calendarPresenter);
            calendarPresenter.setToDoListInput(toDoListInteractor);

            CalendarScreen calendarScreen = new CalendarScreen(screens, cardLayout, calendarPresenter,
                    workingHoursController, workingHoursPresenter);
            screens.add("calendar", calendarScreen);

            cardLayout.show(screens, "calendar");
        }
        if (evt.getSource() == progressTracker) {
            cardLayout.show(screens, "tracker");
        }
        if (evt.getSource() == courses) {
            cardLayout.show(screens, "courseEnrol");
        }
        if (evt.getSource() == createCT) {
            cardLayout.show(screens, "createCT");
        }
        if (evt.getSource() == scheduleCT) {
            cardLayout.show(screens, "scheduleCT");
        }
        if (evt.getSource() == logout) {
            try {
                logoutController.create();
                showMessageDialog(this, "Successfully logged out");
                cardLayout.show(screens, "welcome");
            } catch (Exception e) {
                showMessageDialog(this, "Logout failed");
            }
        }

    }
}
