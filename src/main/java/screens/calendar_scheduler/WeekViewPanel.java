package screens.calendar_scheduler;

import entities.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class WeekViewPanel extends JPanel {

    public WeekViewPanel(LocalDate date, ArrayList<ArrayList<String>> allTasks, WorkingHoursPresenter workingHoursPresenter) {

        // Get date details
        int day = date.getDayOfMonth();
        int month = date.getMonthValue() - 1;
        int year = date.getYear();
        int dayOfWeek = date.getDayOfWeek().getValue();

        // Get working hours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime workingStartTime = LocalTime.parse("07:00", formatter);
        LocalTime workingEndTime = LocalTime.parse("23:00", formatter);
        ArrayList<LocalTime> workingHours = workingHoursPresenter.getWorkingHours();
        if (workingHours != null) {
            workingStartTime = workingHours.get(0);
            workingEndTime = workingHours.get(1);
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

        Calendar currDate = Calendar.getInstance();
        int currMonth = currDate.get(Calendar.MONTH);
        int currYear = currDate.get(Calendar.YEAR);
        int currDay = currDate.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        Calendar iterator = (Calendar) calendar.clone();
        iterator.add(Calendar.DAY_OF_MONTH, -(iterator.get(Calendar.DAY_OF_WEEK) - 1));
        Calendar maximum = (Calendar) calendar.clone();
        if (maximum.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            maximum.add(Calendar.DAY_OF_WEEK, 7-dayOfWeek);
        } else {
            maximum.add(Calendar.DAY_OF_WEEK, dayOfWeek);
        }

        // Create day of week panels
        String[] weekdays = {"", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < weekdays.length; i++) {
            GridBagConstraints c = new GridBagConstraints();
            JPanel weekdayPanel = new JPanel();
            weekdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel weekdayLabel = new JLabel(weekdays[i]);
            weekdayPanel.add(weekdayLabel);
            c.gridx = i;
            c.gridy = 0;
            datePanel.add(weekdayPanel, c);
        }

        // Create day panels
        int iter = 1;
        while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
            JPanel dayPanel = new JPanel();
            GridBagConstraints c = new GridBagConstraints();
            dayPanel.setLayout(new BorderLayout());
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();

            int iterDay = iterator.get(Calendar.DAY_OF_MONTH);
            dayLabel.setText(Integer.toString(iterDay));

            if ((currMonth == month) && (currYear == year) && (currDay == iterDay)) {
                dayPanel.setBackground(Color.ORANGE);
            } else {
                dayPanel.setBackground(Color.WHITE);
            }

            dayPanel.add(dayLabel, BorderLayout.NORTH);
            c.gridx = iter;
            c.gridy = 1;
            datePanel.add(dayPanel, c);
            iterator.add(Calendar.DAY_OF_YEAR, +1);
            iter++;
        }

        // Create hour panels
        int workingStartTimeHour = workingStartTime.getHour();
        int workingEndTimeHour = workingEndTime.getHour();
        ArrayList<String> hours = new ArrayList<>();
        hours.add("");
        for (int i = workingStartTimeHour; i <= workingEndTimeHour; i++) {
            hours.add(Integer.toString(i));
        }
        for (int i = 0; i <= workingEndTimeHour - workingStartTimeHour; i++) {
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
        for (ArrayList<String> task : allTasks) {

            // Create Timeblockable task panels
            if (!Objects.equals(task.get(1), "Assignment")) {

                ArrayList<LocalDateTime> startTimes = new ArrayList<>();
                ArrayList<LocalDateTime> endTimes = new ArrayList<>();
                LocalDateTime taskStartTime = LocalDateTime.parse(task.get(2));
                LocalDateTime taskEndTime = LocalDateTime.parse(task.get(3));
                long taskDuration = ChronoUnit.MINUTES.between(taskStartTime, taskEndTime);

                TemporalField field = WeekFields.of(Locale.US).dayOfWeek();
                LocalDate startOfWeek = date.with(field, 1);

                // Convert recurring tasks into multiple start/end times
                if (Objects.equals(task.get(4), "true")) {
                    if (Objects.equals(task.get(5), "daily")) {
                        LocalDateTime dateIterator = taskStartTime;
                        while (dateIterator.isBefore(taskStartTime.plusYears(1))) {
                            startTimes.add(dateIterator);
                            endTimes.add(dateIterator.plusMinutes(taskDuration));
                            dateIterator = dateIterator.plusDays(1);
                        }
                    } else if (Objects.equals(task.get(5), "weekly")) {
                        LocalDateTime dateIterator = taskStartTime;
                        while (dateIterator.isBefore(taskStartTime.plusYears(1))) {
                            startTimes.add(dateIterator);
                            endTimes.add(dateIterator.plusMinutes(taskDuration));
                            dateIterator = dateIterator.plusWeeks(1);
                        }
                    } else {
                        LocalDateTime dateIterator = taskStartTime;
                        while (dateIterator.isBefore(taskStartTime.plusYears(1))) {
                            startTimes.add(dateIterator);
                            endTimes.add(dateIterator.plusMinutes(taskDuration));
                            dateIterator = dateIterator.plusMonths(1);
                        }
                    }
                } else {
                    startTimes.add(taskStartTime);
                    endTimes.add(taskEndTime);
                }

                for (int i = 0; i < startTimes.size(); i++) {

                    if (startTimes.get(i).isAfter(startOfWeek.atStartOfDay())
                            && startTimes.get(i).isBefore(startOfWeek.plusWeeks(1).atStartOfDay())) {

                        GridBagConstraints c = new GridBagConstraints();
                        JPanel taskPanel = new JPanel();
                        taskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        taskPanel.setBackground(Color.PINK);
                        JLabel taskLabel = new JLabel(task.get(0));
                        taskPanel.add(taskLabel);

                        int taskStartHour = startTimes.get(i).getHour();
                        int taskEndHour = endTimes.get(i).getHour();

                        c.gridy = taskStartHour - workingStartTimeHour +2;
                        c.gridheight = taskEndHour - taskStartHour;

                        int taskDayOfWeek = startTimes.get(i).getDayOfWeek().getValue();
                        c.gridx = taskDayOfWeek + 1;

                        datePanel.add(taskPanel, c);
                    }
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

