package scheduling_ct_use_case;

import entities.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import screens.collaborative_task_scheduling.ScheduleCTPresenter;
import screens.collaborative_task_scheduling.ScheduleCTView;
import screens.collaborative_task_scheduling.ScheduleCTViewInterface;
import screens.task_management.FileTaskMap;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

class ScheduleCTInteractorUnitTest {
    CardLayout cardLayout = new CardLayout();
    JPanel screens = new JPanel();
    ScheduleCTViewInterface view = new ScheduleCTView(cardLayout, screens);
    ScheduleCTOutputBoundary presenter = new ScheduleCTPresenter(view);

    ScheduleCTDSGateway gateway = new FileTaskMap("src/main/java/data/taskmap.ser");
    ScheduleCTInteractor interactor = new ScheduleCTInteractor(presenter, gateway);

    @Test
    public void convertLocalDateTimeToStringTest() {

        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 6, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 7, 0);

        String expected = "2022-11-26 06:00 to 2022-11-26 07:00";
        String actual = interactor.convertLocalDateTimeToString(start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void convertStringToLocalDateTimeTest() {

        String stringDateTime = "2022-11-26 06:00";

        LocalDateTime expected = LocalDateTime.of(2022, 11, 26, 6, 0);
        LocalDateTime actual = interactor.convertStringToLocalDateTime(stringDateTime);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTaskObjectFromNameTest() {
        // test for when the task exists in the hash map given

        String taskName = "CSC207 lecture";
        HashMap<String, Task> hashMap = new HashMap<>();

        StudentUser user = new StudentUser("Alyson", "penguinsAndDucks");

        CollaborativeTask collaborativeTask1 = new CollaborativeTask("CSC207 lecture", "207User",
                1, false, "", LocalDateTime.of(2022, 11, 26, 2, 0),
                LocalDateTime.of(2022, 11, 26, 3, 0),
                LocalDateTime.of(2022, 11, 26, 8, 0),
                user);
        Event event2 = new Event("Hackathon", "HackathonUser", 1,
                LocalDateTime.of(2022, 11, 27, 2, 0),
                LocalDateTime.of(2022, 11, 27, 3, 0),
                false, "");

        hashMap.put("207User", collaborativeTask1);
        hashMap.put("HackathonUser", event2);

        Task actual = interactor.getTaskObjectFromName(taskName, hashMap);

        Assertions.assertEquals(collaborativeTask1, actual);
    }

    @Test
    public void getAllTaskFromIdExceptOneTest() {
        // test getAllTaskFromIdExceptOne

        HashMap<String, Task> hashMap = new HashMap<>();

        StudentUser user = new StudentUser("Alyson", "penguinsAndDucks");
        ArrayList<String> toDoList = new ArrayList<>();
        toDoList.add("207User");
        toDoList.add("HackathonUser");
        toDoList.add("LunchUser");

        user.setToDoList(toDoList);

        CollaborativeTask collaborativeTask1 = new CollaborativeTask("CSC207 lecture", "207User",
                1, false, "", LocalDateTime.of(2022, 11, 26, 2, 0),
                LocalDateTime.of(2022, 11, 26, 3, 0),
                LocalDateTime.of(2022, 11, 26, 8, 0),
                user);
        Event event1 = new Event("Hackathon", "HackathonUser", 1,
                LocalDateTime.of(2022, 11, 27, 2, 0),
                LocalDateTime.of(2022, 11, 27, 3, 0),
                false, "");
        Event event2 = new Event("Eat lunch", "LunchUser", 1,
                LocalDateTime.of(2022, 11, 28, 12, 0),
                LocalDateTime.of(2022, 11, 28, 13, 0),
                false, "");

        hashMap.put("207User", collaborativeTask1);
        hashMap.put("LunchUser", event2);
        hashMap.put("HackathonUser", event1);

        ArrayList<Task> expected = new ArrayList<>();
        expected.add(event1);
        expected.add(event2);

        ArrayList<Task> actual = interactor.getAllTaskFromIdExceptOne(collaborativeTask1, user, hashMap);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeNoConflictTest() {

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 1, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 3, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 25, 6, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 25, 7, 0);

        boolean expected = true;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeCaseOneTest() {
        // Testing the first case where the time block is within start and end
        // output should be false since conflicting

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 4, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 5, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 3, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 6, 0);

        boolean expected = false;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeCaseTwoTest() {
        // Testing the second case where the time block covers start and end
        // output should be false since conflicting

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 5, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 8, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 6, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 7, 0);

        boolean expected = false;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeCaseThreeTest() {
        // Testing the third case where the starts before START, and the end of the time block is between START and END

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 5, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 6, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 4, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 5, 30);

        boolean expected = false;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeCaseFourTest() {
        // Testing the fourth case where the time block starts at the same time as START

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 5, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 6, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 5, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 7, 0);

        boolean expected = false;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeCaseFiveTest() {
        // Testing the fifth case where the time block ends at the same time as END

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 5, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 6, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 4, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 6, 0);

        boolean expected = false;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void givenTimeCaseSixTest() {
        // Testing the sixth case where the time block starts within START and END, and ends after END

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 5, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 9, 0);

        // creating time block of existing task
        LocalDateTime start = LocalDateTime.of(2022, 11, 26, 4, 0);
        LocalDateTime end = LocalDateTime.of(2022, 11, 26, 7, 0);

        boolean expected = false;
        boolean actual = interactor.givenTime(timeBlockStart, timeBlockEnd, start, end);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void workingHoursFreeNoConflictTest() {
        // Testing that workingHoursFree should return true when no conflict between time blocks

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 6, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 7, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(2, 0);
        LocalTime workingHoursEnd = LocalTime.of(3, 0);

        boolean expected = true;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void workingHoursCaseOneTest() {
        // Testing first case where the time block is within working hours
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 3, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 4, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(2, 0);
        LocalTime workingHoursEnd = LocalTime.of(5, 0);

        boolean expected = false;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void workingHoursCaseTwoTest() {
        // Testing second case where the time block covers all working hours
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 2, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 5, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(3, 0);
        LocalTime workingHoursEnd = LocalTime.of(4, 0);

        boolean expected = false;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void workingHoursCaseThreeTest() {
        // Testing third case where the time block starts before working hours, and ends between working hours
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 1, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 4, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(2, 0);
        LocalTime workingHoursEnd = LocalTime.of(5, 0);

        boolean expected = false;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void workingHoursCaseFourTest() {
        // Testing fourth case where the time block starts between working hours, and ends after working hours
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 3, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 8, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(2, 0);
        LocalTime workingHoursEnd = LocalTime.of(5, 0);

        boolean expected = false;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void workingHoursCaseFiveTest() {
        // Testing fifth case where the end of the time block is equal to the end of working hours
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 3, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 4, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(2, 0);
        LocalTime workingHoursEnd = LocalTime.of(4, 0);

        boolean expected = false;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void workingHoursCaseSixTest() {
        // Testing sixth case where the start of the time block is equal to the start of working hours
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 3, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 4, 0);

        // creating time of working hours
        LocalTime workingHoursStart = LocalTime.of(3, 0);
        LocalTime workingHoursEnd = LocalTime.of(5, 0);

        boolean expected = false;
        boolean actual = interactor.workingHoursFree(timeBlockStart, timeBlockEnd, workingHoursStart, workingHoursEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void collaborativeTaskFreeNoConflictTest() {
        // Testing collaborativeTaskFree where given a collaborative task and a start and end date and time
        // Should return true since there is no conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 26, 3, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 26, 4, 0);

        // creating student user
        StudentUser user = new StudentUser("alyson", "catsAndDog");

        // creating a collaborative task
        CollaborativeTask collaborativeTask = new CollaborativeTask("Eating", "EatingUser", 1,
                true, "daily",
                LocalDateTime.of(2022, 11, 26, 7, 0),
                LocalDateTime.of(2022, 11, 26, 8, 0),
                LocalDateTime.of(2022, 11, 28, 9, 0),
                user);
        // adding scheduled times to collaborative task
        ArrayList<ArrayList<LocalDateTime>> scheduledDates = new ArrayList<>();

        ArrayList<LocalDateTime> date1 = new ArrayList<>();
        date1.add(LocalDateTime.of(2022, 11, 26, 7, 0));
        date1.add(LocalDateTime.of(2022, 11, 26, 8, 0));

        ArrayList<LocalDateTime> date2 = new ArrayList<>();
        date2.add(LocalDateTime.of(2022, 11, 27, 7, 0));
        date2.add(LocalDateTime.of(2022, 11, 27, 8, 0));

        ArrayList<LocalDateTime> date3 = new ArrayList<>();
        date3.add(LocalDateTime.of(2022, 11, 28, 7, 0));
        date3.add(LocalDateTime.of(2022, 11, 29, 8, 0));

        scheduledDates.add(date1);
        scheduledDates.add(date2);
        scheduledDates.add(date3);

        collaborativeTask.setTimeBlocks(scheduledDates);

        boolean expected = true;
        boolean actual = interactor.collaborativeTaskFree(collaborativeTask, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void collaborativeTaskFreeConflictTest() {
        // Testing collaborativeTaskFree where given a collaborative task and a start and end date and time
        // Should return false since there is a conflict

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 27, 7, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 27, 8, 0);

        // creating student user
        StudentUser user = new StudentUser("alyson", "catsAndDog");

        // creating a collaborative task
        CollaborativeTask collaborativeTask = new CollaborativeTask("Eating", "EatingUser", 1,
                true, "daily",
                LocalDateTime.of(2022, 11, 26, 7, 0),
                LocalDateTime.of(2022, 11, 26, 8, 0),
                LocalDateTime.of(2022, 11, 28, 9, 0),
                user);
        // adding scheduled times to collaborative task
        ArrayList<ArrayList<LocalDateTime>> scheduledDates = new ArrayList<>();

        ArrayList<LocalDateTime> date1 = new ArrayList<>();
        date1.add(LocalDateTime.of(2022, 11, 26, 7, 0));
        date1.add(LocalDateTime.of(2022, 11, 26, 8, 0));

        ArrayList<LocalDateTime> date2 = new ArrayList<>();
        date2.add(LocalDateTime.of(2022, 11, 27, 7, 0));
        date2.add(LocalDateTime.of(2022, 11, 27, 8, 0));

        ArrayList<LocalDateTime> date3 = new ArrayList<>();
        date3.add(LocalDateTime.of(2022, 11, 28, 7, 0));
        date3.add(LocalDateTime.of(2022, 11, 29, 8, 0));

        scheduledDates.add(date1);
        scheduledDates.add(date2);
        scheduledDates.add(date3);

        collaborativeTask.setTimeBlocks(scheduledDates);

        boolean expected = false;
        boolean actual = interactor.collaborativeTaskFree(collaborativeTask, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void assignmentFreeNoConflictTest() {
        // Testing assignmentFree given an assignment and a start and end time
        // Should return true since there is no conflict between the assignment and the time block given

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 27, 5, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 27, 6, 0);

        // creating assignment
        Assignment assignment = new Assignment("Assignment", "AssignmentUser",
                LocalDateTime.of(2022, 11, 27, 22, 0), 5);

        // adding scheduled times to assignment
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 26, 7, 0));
        prep1.add(LocalDateTime.of(2022, 11, 26, 8, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 7, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 8, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        assignment.setPrepTimeScheduled(prepTime);

        boolean expected = true;
        boolean actual = interactor.assignmentFree(assignment, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void assignmentFreeConflictTest() {
        // Testing assignmentFree given an assignment and a start and end time
        // Should return false since there is a conflict between the assignment and the time block given

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 27, 7, 30);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 27, 8, 0);

        // creating assignment
        Assignment assignment = new Assignment("Assignment", "AssignmentUser",
                LocalDateTime.of(2022, 11, 27, 22, 0), 5);

        // adding scheduled times to assignment
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 26, 7, 0));
        prep1.add(LocalDateTime.of(2022, 11, 26, 8, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 7, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 8, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        assignment.setPrepTimeScheduled(prepTime);

        boolean expected = false;
        boolean actual = interactor.assignmentFree(assignment, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFreeNoConflictTest() {
        // Testing testFree given a test and a start and end time
        // Should return true since there is no conflict between the test and the time block given

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 27, 4, 30);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 27, 5, 0);

        // creating test
        entities.Test test = new entities.Test("Test", "TestUser",
                LocalDateTime.of(2022, 11, 27, 14, 0),
                LocalDateTime.of(2022, 11, 27, 16, 0), 15);

        // adding scheduled times to test
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 26, 7, 0));
        prep1.add(LocalDateTime.of(2022, 11, 26, 8, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 7, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 8, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        test.setPrepTimeScheduled(prepTime);

        boolean expected = true;
        boolean actual = interactor.testFree(test, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFreeConflictTest() {
        // Testing testFree given a test and a start and end date and time
        // Should return false since there is a conflict between the test and the time block given

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 27, 15, 30);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 27, 16, 0);

        // creating test
        entities.Test test = new entities.Test("Test", "TestUser",
                LocalDateTime.of(2022, 11, 27, 14, 0),
                LocalDateTime.of(2022, 11, 27, 16, 0), 15);

        // adding scheduled times to test
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 26, 7, 0));
        prep1.add(LocalDateTime.of(2022, 11, 26, 8, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 7, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 8, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        test.setPrepTimeScheduled(prepTime);

        boolean expected = false;
        boolean actual = interactor.testFree(test, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void eventFreeNoConflictTest() {
        // Testing eventFree given an event and a start and end date and time
        // Should return true since there is no conflict between the event and the time block given

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 27, 4, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 27, 5, 0);

        // creating event
        Event event = new Event("Event", "EventUser", 1,
                LocalDateTime.of(2022, 11, 28, 13, 0),
                LocalDateTime.of(2022, 11, 28, 15, 0),
                false, "");

        boolean expected = true;
        boolean actual = interactor.eventFree(event, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void eventFreeConflictTest() {
        // Testing eventFree given an event and a start and end date and time
        // Should return false since there is a conflict between the event and the time block given

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 28, 14, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 28, 15, 0);

        // creating event
        Event event = new Event("Event", "EventUser", 1,
                LocalDateTime.of(2022, 11, 28, 13, 0),
                LocalDateTime.of(2022, 11, 28, 15, 0),
                false, "");

        boolean expected = false;
        boolean actual = interactor.eventFree(event, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void isUserAvailableAtDateTimeNoConflictTest() {
        // Testing isUserAvailableAtDateTime
        // Should return true since there is no conflict

        ArrayList<Task> tasks = new ArrayList<>();
        // creating event
        Event event = new Event("Event", "EventUser", 1,
                LocalDateTime.of(2022, 11, 27, 4, 0),
                LocalDateTime.of(2022, 11, 27, 5, 0),
                false, "");

        // creating test
        entities.Test test = new entities.Test("Test", "TestUser",
                LocalDateTime.of(2022, 11, 27, 5, 0),
                LocalDateTime.of(2022, 11, 27, 6, 0), 15);

        // adding scheduled times to test
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 27, 8, 0));
        prep1.add(LocalDateTime.of(2022, 11, 27, 9, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 9, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 10, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        test.setPrepTimeScheduled(prepTime);

        // creating assignment
        Assignment assignment = new Assignment("Assignment", "AssignmentUser",
                LocalDateTime.of(2022, 12, 5, 22, 0), 5);

        // adding scheduled times to assignment
        ArrayList<ArrayList<LocalDateTime>> assignmentPrepTime = new ArrayList<>();

        ArrayList<LocalDateTime> assignmentPrep1 = new ArrayList<>();
        assignmentPrep1.add(LocalDateTime.of(2022, 11, 28, 7, 0));
        assignmentPrep1.add(LocalDateTime.of(2022, 11, 28, 8, 0));

        ArrayList<LocalDateTime> assignmentPrep2 = new ArrayList<>();
        assignmentPrep2.add(LocalDateTime.of(2022, 11, 28, 9, 0));
        assignmentPrep2.add(LocalDateTime.of(2022, 11, 28, 10, 0));

        assignmentPrepTime.add(assignmentPrep1);
        assignmentPrepTime.add(assignmentPrep2);

        assignment.setPrepTimeScheduled(assignmentPrepTime);

        tasks.add(event);
        tasks.add(test);
        tasks.add(assignment);

        // creating time of working hours
        ArrayList<LocalTime> workingHours = new ArrayList<>();
        LocalTime workingHoursStart = LocalTime.of(1, 0);
        LocalTime workingHoursEnd = LocalTime.of(2, 0);
        workingHours.add(workingHoursStart);
        workingHours.add(workingHoursEnd);

        // creating time block of collaborative task user wants to schedule
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 29, 4, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 29, 5, 0);

        // creating student user
        StudentUser user = new StudentUser("alyson", "veryTiredStudent");
        user.setWorkingHours(workingHours);

        boolean expected = true;
        boolean actual = interactor.isUserAvailableAtDateTime(user, tasks, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void isUserAvailableAtDateTimeConflictWithWorkingHoursTest() {
        // Testing isUserAvailableAtDateTime
        // Should return false since there is a conflict

        ArrayList<Task> tasks = new ArrayList<>();
        // creating event
        Event event = new Event("Event", "EventUser", 1,
                LocalDateTime.of(2022, 11, 27, 4, 0),
                LocalDateTime.of(2022, 11, 27, 5, 0),
                false, "");

        // creating test
        entities.Test test = new entities.Test("Test", "TestUser",
                LocalDateTime.of(2022, 11, 27, 5, 0),
                LocalDateTime.of(2022, 11, 27, 6, 0), 15);

        // adding scheduled times to test
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 27, 8, 0));
        prep1.add(LocalDateTime.of(2022, 11, 27, 9, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 9, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 10, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        test.setPrepTimeScheduled(prepTime);

        // creating assignment
        Assignment assignment = new Assignment("Assignment", "AssignmentUser",
                LocalDateTime.of(2022, 12, 5, 22, 0), 5);

        // adding scheduled times to assignment
        ArrayList<ArrayList<LocalDateTime>> assignmentPrepTime = new ArrayList<>();

        ArrayList<LocalDateTime> assignmentPrep1 = new ArrayList<>();
        assignmentPrep1.add(LocalDateTime.of(2022, 11, 28, 7, 0));
        assignmentPrep1.add(LocalDateTime.of(2022, 11, 28, 8, 0));

        ArrayList<LocalDateTime> assignmentPrep2 = new ArrayList<>();
        assignmentPrep2.add(LocalDateTime.of(2022, 11, 28, 9, 0));
        assignmentPrep2.add(LocalDateTime.of(2022, 11, 28, 10, 0));

        assignmentPrepTime.add(assignmentPrep1);
        assignmentPrepTime.add(assignmentPrep2);

        assignment.setPrepTimeScheduled(assignmentPrepTime);

        tasks.add(event);
        tasks.add(test);
        tasks.add(assignment);

        // creating time of working hours
        ArrayList<LocalTime> workingHours = new ArrayList<>();
        LocalTime workingHoursStart = LocalTime.of(1, 0);
        LocalTime workingHoursEnd = LocalTime.of(2, 0);
        workingHours.add(workingHoursStart);
        workingHours.add(workingHoursEnd);

        // creating time block of collaborative task user wants to schedule
        // conflicts with workingHours
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 29, 1, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 29, 3, 0);

        // creating student user
        StudentUser user = new StudentUser("alyson", "veryTiredStudent");
        user.setWorkingHours(workingHours);

        boolean expected = false;
        boolean actual = interactor.isUserAvailableAtDateTime(user, tasks, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void isUserAvailableAtDateTimeConflictWithTaskTest() {
        // Testing isUserAvailableAtDateTime
        // Should return false since there is a conflict

        ArrayList<Task> tasks = new ArrayList<>();
        // creating event
        Event event = new Event("Event", "EventUser", 1,
                LocalDateTime.of(2022, 11, 27, 4, 0),
                LocalDateTime.of(2022, 11, 27, 5, 0),
                false, "");

        // creating test
        entities.Test test = new entities.Test("Test", "TestUser",
                LocalDateTime.of(2022, 11, 27, 5, 0),
                LocalDateTime.of(2022, 11, 27, 6, 0), 15);

        // adding scheduled times to test
        ArrayList<ArrayList<LocalDateTime>> prepTime = new ArrayList<>();

        ArrayList<LocalDateTime> prep1 = new ArrayList<>();
        prep1.add(LocalDateTime.of(2022, 11, 27, 8, 0));
        prep1.add(LocalDateTime.of(2022, 11, 27, 9, 0));

        ArrayList<LocalDateTime> prep2 = new ArrayList<>();
        prep2.add(LocalDateTime.of(2022, 11, 27, 9, 0));
        prep2.add(LocalDateTime.of(2022, 11, 27, 10, 0));

        prepTime.add(prep1);
        prepTime.add(prep2);

        test.setPrepTimeScheduled(prepTime);

        // creating assignment
        Assignment assignment = new Assignment("Assignment", "AssignmentUser",
                LocalDateTime.of(2022, 12, 5, 22, 0), 5);

        // adding scheduled times to assignment
        ArrayList<ArrayList<LocalDateTime>> assignmentPrepTime = new ArrayList<>();

        ArrayList<LocalDateTime> assignmentPrep1 = new ArrayList<>();
        assignmentPrep1.add(LocalDateTime.of(2022, 11, 28, 7, 0));
        assignmentPrep1.add(LocalDateTime.of(2022, 11, 28, 8, 0));

        ArrayList<LocalDateTime> assignmentPrep2 = new ArrayList<>();
        assignmentPrep2.add(LocalDateTime.of(2022, 11, 28, 9, 0));
        assignmentPrep2.add(LocalDateTime.of(2022, 11, 28, 10, 0));

        assignmentPrepTime.add(assignmentPrep1);
        assignmentPrepTime.add(assignmentPrep2);

        assignment.setPrepTimeScheduled(assignmentPrepTime);

        tasks.add(event);
        tasks.add(test);
        tasks.add(assignment);

        // creating time of working hours
        ArrayList<LocalTime> workingHours = new ArrayList<>();
        LocalTime workingHoursStart = LocalTime.of(1, 0);
        LocalTime workingHoursEnd = LocalTime.of(2, 0);
        workingHours.add(workingHoursStart);
        workingHours.add(workingHoursEnd);

        // creating time block of collaborative task user wants to schedule
        // conflicts with workingHours
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 28, 10, 0);

        // creating student user
        StudentUser user = new StudentUser("alyson", "veryTiredStudent");
        user.setWorkingHours(workingHours);

        boolean expected = false;
        boolean actual = interactor.isUserAvailableAtDateTime(user, tasks, timeBlockStart, timeBlockEnd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getDatesDailyTest() {
        // Tests getDates with a frequency of "daily"
        // Should return an ArrayList<ArrayList<LocalDateTime>> of times until the deadline

        String frequency = "daily";

        // creating time block of date and time of when the user wants to start scheduling
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 28, 10, 0);

        // creating deadline
        LocalDateTime deadline = LocalDateTime.of(2022, 11, 30, 12, 0);

        // creating expected output
        ArrayList<ArrayList<LocalDateTime>> dates = new ArrayList<>();

        ArrayList<LocalDateTime> date1 = new ArrayList<>();
        LocalDateTime date1Start = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime date1End = LocalDateTime.of(2022, 11, 28, 10, 0);
        date1.add(date1Start);
        date1.add(date1End);

        ArrayList<LocalDateTime> date2 = new ArrayList<>();
        LocalDateTime date2Start = LocalDateTime.of(2022, 11, 29, 9, 0);
        LocalDateTime date2End = LocalDateTime.of(2022, 11, 29, 10, 0);
        date2.add(date2Start);
        date2.add(date2End);

        ArrayList<LocalDateTime> date3 = new ArrayList<>();
        LocalDateTime date3Start = LocalDateTime.of(2022, 11, 30, 9, 0);
        LocalDateTime date3End = LocalDateTime.of(2022, 11, 30, 10, 0);
        date3.add(date3Start);
        date3.add(date3End);

        dates.add(date1);
        dates.add(date2);
        dates.add(date3);

        ArrayList<ArrayList<LocalDateTime>> actual = interactor.getDates(frequency, timeBlockStart, timeBlockEnd, deadline);
        Assertions.assertEquals(dates, actual);
    }

    @Test
    public void getDatesWeeklyTest() {
        // Tests getDates with a frequency of "weekly"
        // Should return an ArrayList<ArrayList<LocalDateTime>> of times until the deadline

        String frequency = "weekly";

        // creating time block of date and time of when the user wants to start scheduling
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 28, 10, 0);

        // creating deadline
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 5, 12, 0);

        // creating expected output
        ArrayList<ArrayList<LocalDateTime>> dates = new ArrayList<>();

        ArrayList<LocalDateTime> date1 = new ArrayList<>();
        LocalDateTime date1Start = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime date1End = LocalDateTime.of(2022, 11, 28, 10, 0);
        date1.add(date1Start);
        date1.add(date1End);

        ArrayList<LocalDateTime> date2 = new ArrayList<>();
        LocalDateTime date2Start = LocalDateTime.of(2022, 12, 5, 9, 0);
        LocalDateTime date2End = LocalDateTime.of(2022, 12, 5, 10, 0);
        date2.add(date2Start);
        date2.add(date2End);

        dates.add(date1);
        dates.add(date2);

        ArrayList<ArrayList<LocalDateTime>> actual = interactor.getDates(frequency, timeBlockStart, timeBlockEnd, deadline);
        Assertions.assertEquals(dates, actual);
    }

    @Test
    public void getDatesMonthlyTest() {
        // Tests getDates with a frequency of "monthly"
        // Should return an ArrayList<ArrayList<LocalDateTime>> of times until the deadline

        String frequency = "monthly";

        // creating time block of date and time of when the user wants to start scheduling
        LocalDateTime timeBlockStart = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime timeBlockEnd = LocalDateTime.of(2022, 11, 28, 10, 0);

        // creating deadline
        LocalDateTime deadline = LocalDateTime.of(2023, 1, 30, 12, 0);

        // creating expected output
        ArrayList<ArrayList<LocalDateTime>> dates = new ArrayList<>();

        ArrayList<LocalDateTime> date1 = new ArrayList<>();
        LocalDateTime date1Start = LocalDateTime.of(2022, 11, 28, 9, 0);
        LocalDateTime date1End = LocalDateTime.of(2022, 11, 28, 10, 0);
        date1.add(date1Start);
        date1.add(date1End);

        ArrayList<LocalDateTime> date2 = new ArrayList<>();
        LocalDateTime date2Start = LocalDateTime.of(2022, 12, 28, 9, 0);
        LocalDateTime date2End = LocalDateTime.of(2022, 12, 28, 10, 0);
        date2.add(date2Start);
        date2.add(date2End);

        ArrayList<LocalDateTime> date3 = new ArrayList<>();
        LocalDateTime date3Start = LocalDateTime.of(2023, 1, 28, 9, 0);
        LocalDateTime date3End = LocalDateTime.of(2023, 1, 28, 10, 0);
        date3.add(date3Start);
        date3.add(date3End);

        dates.add(date1);
        dates.add(date2);
        dates.add(date3);

        ArrayList<ArrayList<LocalDateTime>> actual = interactor.getDates(frequency, timeBlockStart, timeBlockEnd, deadline);
        Assertions.assertEquals(dates, actual);
    }
}