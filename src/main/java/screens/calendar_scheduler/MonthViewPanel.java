package screens.calendar_scheduler;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class MonthViewPanel extends JPanel {

    public MonthViewPanel(LocalDate date, ArrayList<ArrayList<String>> allTasks) {

        // Get date details
        int month = date.getMonthValue() - 1;
        int year = date.getYear();

        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel(date.getMonth() + " " + year);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Create date panel
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(0, 7));

        Calendar currDate = Calendar.getInstance();
        int currMonth = currDate.get(Calendar.MONTH);
        int currYear = currDate.get(Calendar.YEAR);
        int currDay = currDate.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Calendar iterator = (Calendar) calendar.clone();
        iterator.add(Calendar.DAY_OF_MONTH, -(iterator.get(Calendar.DAY_OF_WEEK) - 1));
        Calendar maximum = (Calendar) calendar.clone();
        maximum.add(Calendar.MONTH, +1);

        // Create day of week panels
        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String weekday : weekdays) {
            JPanel weekdayPanel = new JPanel();
            weekdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel weekdayLabel = new JLabel(weekday);
            weekdayPanel.add(weekdayLabel);
            datePanel.add(weekdayPanel);
        }

        // Create day panels
        int count = 0;
        int limit = 42;

        while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
            int iterMonth = iterator.get(Calendar.MONTH);
            int iterYear = iterator.get(Calendar.YEAR);

            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BorderLayout());
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();

            int iterDay = iterator.get(Calendar.DAY_OF_MONTH);
            dayLabel.setText(Integer.toString(iterDay));

            if ((iterMonth == month) && (iterYear == year)) {
                JPanel taskPanel = new JPanel();

                int assignmentCount = 0;
                int testCount = 0;
                int eventCount = 0;

                for (ArrayList<String> task : allTasks) {
                    if (Objects.equals(task.get(1), "Assignment")) {
                        assignmentCount += 1;
                    }
                    if (Objects.equals(task.get(1), "Test")) {
                        testCount += 1;
                    }
                    if (Objects.equals(task.get(1), "Event")) {
                        eventCount += 1;
                    }
                }

                JLabel assignmentLabel = new JLabel("Assignments: " + assignmentCount);
                JLabel testLabel = new JLabel("Tests: " + testCount);
                JLabel eventLabel = new JLabel("Events: " + eventCount);

                taskPanel.add(assignmentLabel);
                taskPanel.add(testLabel);
                taskPanel.add(eventLabel);

                taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
                taskPanel.setBackground(Color.LIGHT_GRAY);

                dayPanel.add(taskPanel, BorderLayout.SOUTH);

                if ((currMonth == month) && (currYear == year) && (currDay == iterDay)) {
                    dayPanel.setBackground(Color.ORANGE);
                } else {
                    dayPanel.setBackground(Color.WHITE);
                }
            } else {
                dayPanel.setBackground(Color.GRAY);
            }

            dayPanel.add(dayLabel, BorderLayout.NORTH);
            datePanel.add(dayPanel);
            iterator.add(Calendar.DAY_OF_YEAR, +1);
            count++;
        }

        for (int i = count; i < limit; i++) {
            JPanel fillPanel = new JPanel();
            fillPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel fillLabel = new JLabel();
            fillLabel.setText(Integer.toString(i - 31));
            fillPanel.add(fillLabel);
            fillPanel.setBackground(Color.GRAY);
            datePanel.add(fillPanel);
        }

        // Create main panel
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(datePanel, BorderLayout.SOUTH);

    }
}
