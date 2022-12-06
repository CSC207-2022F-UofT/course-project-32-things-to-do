package screens.calendar_scheduler;

import entities.CurrentUser;
import entities.StudentUser;
import entities.Task;
import entities.TaskMap;
import screens.task_management.todolist_screens.ToDoListPresenter;
import use_cases.task_management.todolist_use_case.ToDoListResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class CalendarScreen extends JPanel implements ActionListener {

    /**
     * The components of the screen
     */
    JButton settings;
    JComboBox<String> viewToggle;
    JButton exitToMain;
    CardLayout cardLayout;
    JPanel viewPanel;

    /**
     * Objects for connecting to the other screens
     */
    JPanel screens;
    CardLayout screenLayout;

    CalendarPresenter presenter;

    /**
     * The window of the screen for the Calendar view
     */
    public CalendarScreen(JPanel screens, CardLayout screenLayout, CalendarPresenter presenter) {

        this.presenter = presenter;
        this.screens = screens;
        this.screenLayout = screenLayout;

        ToDoListResponseModel responseModel = presenter.getToDoList();
        ArrayList<ArrayList<String>> taskList = responseModel.getToDoListView();

        // Create label for title of screen
        JLabel title = new JLabel("Calendar");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create button for settings
        settings = new JButton("Settings");
        settings.setActionCommand("Settings");
        settings.addActionListener(this);

        // Create button to return to the dashboard
        exitToMain = new JButton("Return");
        exitToMain.setActionCommand("Dashboard");
        exitToMain.addActionListener(this);

        // Create view panels
        LocalDate currDate = LocalDate.now();
        cardLayout = new CardLayout();
        viewPanel = new JPanel(cardLayout);
        DayViewPanel dayViewPanel = new DayViewPanel(currDate, taskList);
        viewPanel.add("day", dayViewPanel);
        WeekViewPanel weekViewPanel = new WeekViewPanel(currDate, taskList);
        viewPanel.add("week", weekViewPanel);
        MonthViewPanel monthViewPanel = new MonthViewPanel(currDate, taskList);
        viewPanel.add("month", monthViewPanel);


        // Create dropdown button for daily/weekly/monthly view toggle
        String[] viewTypes = { "Day", "Week", "Month"};
        viewToggle = new JComboBox<>(viewTypes);
        viewToggle.setSelectedIndex(1);
        viewToggle.addActionListener(this);
        cardLayout.show(viewPanel, "week");

        // Create panel for buttons
        JPanel buttons = new JPanel();
        buttons.add(settings);
        buttons.add(viewToggle);
        buttons.add(exitToMain);

        // Create main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(viewPanel);

    }

    /**
     * Trigger the corresponding use case upon button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Trigger button for changing user's settings
        if (e.getSource() == settings) {
            JPanel settingsPanel = new JPanel();
            settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

            // Get current working hours
            LocalTime currStartTime;
            LocalTime currEndTime;
            ArrayList<LocalTime> currWorkingHours = user.getWorkingHours();
            if (currWorkingHours.size() == 0) {
                currStartTime = LocalTime.now();
                currEndTime = LocalTime.now();
            } else {
                currStartTime = currWorkingHours.get(0);
                currEndTime = currWorkingHours.get(1);
            }

            // Create spinner for the start time hour selection
            SpinnerNumberModel startHourModel = new SpinnerNumberModel(currStartTime.getHour(), 0, 23, 1);
            JSpinner startHourSpinner = new JSpinner(startHourModel);

            // Create spinner for the start time minute selection
            SpinnerNumberModel startMinuteModel = new SpinnerNumberModel(currStartTime.getMinute(), 0, 59, 1);
            JSpinner startMinuteSpinner = new JSpinner(startMinuteModel);
            startMinuteSpinner.setEditor(new JSpinner.NumberEditor(startMinuteSpinner, "00"));

            // Create spinner for the end time hour selection
            SpinnerNumberModel endHourModel = new SpinnerNumberModel(currEndTime.getHour(), 0, 23, 1);
            JSpinner endHourSpinner = new JSpinner(endHourModel);

            // Create spinner for the end time minute selection
            SpinnerNumberModel endMinuteModel = new SpinnerNumberModel(currEndTime.getMinute(), 0, 59, 1);
            JSpinner endMinuteSpinner = new JSpinner(endMinuteModel);
            endMinuteSpinner.setEditor(new JSpinner.NumberEditor(endMinuteSpinner, "00"));

            // Create labels for working hour selection
            JLabel startTimeLabel = new JLabel("Start of working hours:");
            JLabel endTimeLabel = new JLabel("End of working hours:");

            // Create panel for the working hours selection
            JPanel workingHoursPanel = new JPanel();
            JLabel workingHoursLabel = new JLabel("Working hours");

            JPanel workingStartTime = new JPanel();
            workingStartTime.add(startTimeLabel);
            workingStartTime.add(startHourSpinner);
            workingStartTime.add(startMinuteSpinner);

            JPanel workingEndTime = new JPanel();
            workingEndTime.add(endTimeLabel);
            workingEndTime.add(endHourSpinner);
            workingEndTime.add(endMinuteSpinner);

            workingHoursPanel.add(workingHoursLabel);
            workingHoursPanel.add(workingStartTime);
            workingHoursPanel.add(workingEndTime);
            workingHoursPanel.setLayout(new BoxLayout(workingHoursPanel, BoxLayout.Y_AXIS));
            settingsPanel.add(workingHoursPanel);

            JOptionPane.showMessageDialog(null, settingsPanel);

            // Getting working hour values from user selection
            int startHour = (int) startHourSpinner.getValue();
            int startMinute = (int) startMinuteSpinner.getValue();
            int endHour = (int) endHourSpinner.getValue();
            int endMinute = (int) endMinuteSpinner.getValue();

            // Updating user's working hours
            ArrayList<LocalTime> workingHours = new ArrayList<>();
            workingHours.add(LocalTime.of(startHour, startMinute));
            workingHours.add(LocalTime.of(endHour, endMinute));
            this.user.setWorkingHours(workingHours);
        }

        // Trigger button to return to main dashboard
        if (e.getSource() == exitToMain) {
            screenLayout.show(screens, "StudentMain");
        }

        // Trigger button for changing user's view panel
        if (e.getSource() == viewToggle) {
            // Get view panel selection
            String viewType = (String) viewToggle.getSelectedItem();

            // Display selected view panel
            switch (Objects.requireNonNull(viewType)) {
                case "Day":
                    cardLayout.show(viewPanel, "day");
                    break;
                case "Week":
                    cardLayout.show(viewPanel, "week");
                    break;
                case "Month":
                    cardLayout.show(viewPanel, "month");
                    break;
            }
        }

    }
}
