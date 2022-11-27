package screens;

import entities.StudentUser;
import entities.Task;
import entities.Timeblockable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class DayViewPanel extends JPanel {

    public DayViewPanel(LocalDate date, ArrayList<Task> allTasks, StudentUser user) {

        // Get date details
        int day = date.getDayOfMonth();
        int year = date.getYear();

        // Get working hours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse("07:00", formatter);
        LocalTime endTime = LocalTime.parse("23:00", formatter);
        if (user != null) {
            ArrayList<LocalTime> workingHours = user.getWorkingHours();
            if (workingHours.size() != 0) {
                startTime = workingHours.get(0);
                endTime = workingHours.get(1);
            }
        }

        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel(date.getMonth() + " " + year);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Create date panel
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridBagLayout());

        // Create day of week panel
        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        ArrayList<String> weekday = new ArrayList<>();
        weekday.add("");
        int weekdayInt = date.getDayOfWeek().getValue();
        weekday.add(weekdays[weekdayInt - 1]);

        for (int i = 0; i < weekday.size(); i++) {
            GridBagConstraints c = new GridBagConstraints();
            JPanel weekdayPanel = new JPanel();
            weekdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel weekdayLabel = new JLabel(weekday.get(i));
            weekdayPanel.add(weekdayLabel);
            c.gridx = i;
            c.gridy = 0;
            datePanel.add(weekdayPanel, c);
        }

        // Create day panel
        JPanel dayPanel = new JPanel();
        GridBagConstraints dayConstraint = new GridBagConstraints();
        dayPanel.setLayout(new BorderLayout());
        dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel dayLabel = new JLabel();

        dayLabel.setText(Integer.toString(day));
        dayPanel.setBackground(Color.ORANGE);

        dayPanel.add(dayLabel, BorderLayout.NORTH);
        dayConstraint.gridx = 1;
        dayConstraint.gridy = 1;
        datePanel.add(dayPanel, dayConstraint);

        // Create hour panels
        int startTimeHour = startTime.getHour();
        int endTimeHour = endTime.getHour();
        ArrayList<String> hours = new ArrayList<>();
        hours.add("");
        for (int i = startTimeHour; i <= endTimeHour; i++) {
            hours.add(Integer.toString(i));
        }
        for (int i = 0; i <= endTimeHour - startTimeHour; i++) {
            GridBagConstraints c = new GridBagConstraints();
            JPanel hourPanel = new JPanel();
            hourPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel hourLabel = new JLabel(hours.get(i));
            hourPanel.add(hourLabel);
            c.gridx = 0;
            c.gridy = i + 1;
            datePanel.add(hourPanel, c);
        }

        // Create task panels
        for (Task task : allTasks) {
            if (task instanceof Timeblockable) {

                LocalDateTime taskStartTime = ((Timeblockable) task).getTimeBlock()[0];
                LocalDateTime taskEndTime = ((Timeblockable) task).getTimeBlock()[1];

                TemporalField field = WeekFields.of(Locale.US).dayOfWeek();
                LocalDate startOfWeek = date.with(field, 1);

                if (taskStartTime.compareTo(startOfWeek.atStartOfDay()) <= 0
                        && startOfWeek.atStartOfDay().compareTo(taskStartTime.plusWeeks(1)) < 0) {
                    GridBagConstraints c = new GridBagConstraints();
                    JPanel taskPanel = new JPanel();
                    taskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    taskPanel.setBackground(Color.PINK);
                    JLabel taskLabel = new JLabel(task.getTitle());
                    taskPanel.add(taskLabel);

                    int taskStartHour = taskStartTime.getHour();
                    int taskEndHour = taskEndTime.getHour();
                    c.gridy = taskStartHour - startTimeHour;
                    c.gridheight = taskEndHour - taskStartTime.getHour();

                    int taskDayOfWeek = taskStartTime.getDayOfWeek().getValue();
                    c.gridx = taskDayOfWeek + 1;

                    datePanel.add(taskPanel, c);
                }
            }
        }

        // Create main panel
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(datePanel, BorderLayout.SOUTH);

    }
}
