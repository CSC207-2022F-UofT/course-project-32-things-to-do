package scheduling_ct_use_case;

import org.junit.jupiter.api.Test;
import screens.collaborative_task_scheduling.*;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ScheduleCTInteractorTest {

//     ****** IMPORTANT NOTE ******* //
//     CURRENTLY THIS ONLY WORKS IF YOU COMMENT OUT THE "save()" IN THE CONSTRUCTORS FOR THE TASK ENTITY
//     ********

//    @Test
//    void schedule() {
//
//        // CREATING DATES IT SHOULD EQUAL TO
//        // Wed, Dec 7, from 10-11 am
//        // Wed, Dec 14, from 10-11 am
//        // Wed, Dec 21, from 10-11 am
//        ArrayList<String> formattedDates = new ArrayList<>();
//        String date1 = "2022-12-07 10:00 to 2022-12-07 11:00";
//        String date2 = "2022-12-14 10:00 to 2022-12-14 11:00";
//        String date3 = "2022-12-21 10:00 to 2022-12-21 11:00";
//        formattedDates.add(date1);
//        formattedDates.add(date2);
//        formattedDates.add(date3);
//
//        CardLayout cardLayout = new CardLayout();
//        JPanel screens = new JPanel();
//        ScheduleCTViewInterface view = new ScheduleCTView(cardLayout, screens);
//
//        // This creates an anonymous implementing class for the Output Boundary.
//        ScheduleCTOutputBoundary presenter = new ScheduleCTPresenter(view) {
//            @Override
//            public ScheduleCTResponseModel prepareNoConflictView(ScheduleCTResponseModel responseModel) {
//                assertEquals(formattedDates, responseModel.getScheduledTimes());
//                return null;
//            }
//            // 4) Check that the Output Data and associated changes
//            // are correct
//            @Override
//            public ScheduleCTResponseModel prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//                return null;
//            }
//        };
//
//        ScheduleCTInteractor interactor = new ScheduleCTInteractor(presenter);
//
//        // CREATING THE STUDENT USERS
//        // USER 1: TAYLOR
//        StudentUser taylorUser = new StudentUser("Taylor", "TaylorPassword");
//        // TO DO LIST
//        ArrayList<String> taylorToDoList = new ArrayList<>();
//        taylorToDoList.add("TaylorTask1");
//        taylorToDoList.add("TaylorTask2");
//        taylorUser.setToDoList(taylorToDoList);
//        // WORKING HOURS
//        ArrayList<LocalTime> taylorWorkingHours = new ArrayList<>();
//        LocalTime taylorStartWorkingHours = LocalTime.of(1, 0);
//        LocalTime taylorEndWorkingHours = LocalTime.of(6, 0);
//        taylorWorkingHours.add(taylorStartWorkingHours);
//        taylorWorkingHours.add(taylorEndWorkingHours);
//        taylorUser.setWorkingHours(taylorWorkingHours);
//
//        // USER 2: PHOEBE
//        StudentUser phoebeUser = new StudentUser("Phoebe", "PhoebePassword");
//        // TO DO LIST
//        ArrayList<String> phoebeToDoList = new ArrayList<>();
//        phoebeToDoList.add("PhoebeTask1");
//        phoebeToDoList.add("PhoebeTask2");
//        phoebeUser.setToDoList(phoebeToDoList);
//        // WORKING HOURS
//        ArrayList<LocalTime> phoebeWorkingHours = new ArrayList<>();
//        LocalTime phoebeStartWorkingHours = LocalTime.of(1, 0);
//        LocalTime phoebeEndWorkingHours = LocalTime.of(6, 0);
//        phoebeWorkingHours.add(phoebeStartWorkingHours);
//        phoebeWorkingHours.add(phoebeEndWorkingHours);
//        phoebeUser.setWorkingHours(phoebeWorkingHours);
//
//        // CREATING USER TASKS
//        // TAYLOR TASK 1
//        entities.Event taylorTask1 = new entities.Event("Networking Event", "TaylorTask1", 1,
//                LocalDateTime.of(2022, 12, 9, 17, 0),
//                LocalDateTime.of(2022, 12, 9, 19, 0),
//                false, "");
//        // TAYLOR TASK 2
//        entities.Test taylorTask2 = new entities.Test("Biology Term Test", "TaylorTask2",
//                LocalDateTime.of(2022, 12, 14, 16, 0),
//                LocalDateTime.of(2022, 12, 14, 17, 0),
//                10);
//        // TAYLOR TASK 2 PREP TIME
//        ArrayList<ArrayList<LocalDateTime>> taylorTask2PrepTime = new ArrayList<>();
//        ArrayList<LocalDateTime> taylorPrepTime1 = new ArrayList<>();
//        LocalDateTime taylorPrepTime1Start = LocalDateTime.of(2022, 12, 12, 15, 0);
//        LocalDateTime taylorPrepTime1End = LocalDateTime.of(2022, 12, 12, 16, 0);
//        taylorPrepTime1.add(taylorPrepTime1Start);
//        taylorPrepTime1.add(taylorPrepTime1End);
//
//        ArrayList<LocalDateTime> taylorPrepTime2 = new ArrayList<>();
//        LocalDateTime taylorPrepTime2Start = LocalDateTime.of(2022, 12, 13, 15, 0);
//        LocalDateTime taylorPrepTime2End = LocalDateTime.of(2022, 12, 13, 16, 0);
//        taylorPrepTime2.add(taylorPrepTime2Start);
//        taylorPrepTime2.add(taylorPrepTime2End);
//
//        taylorTask2PrepTime.add(taylorPrepTime1);
//        taylorTask2PrepTime.add(taylorPrepTime2);
//        taylorTask2.setPrepTimeScheduled(taylorTask2PrepTime);
//
//        // PHOEBE TASK 1
//        entities.Assignment phoebeTask1 = new entities.Assignment("Math Problem Set", "PhoebeTask1",
//                LocalDateTime.of(2022, 12, 9, 20, 0),
//                5);
//        // PHOEBE TASK 2 PREP TIME
//        ArrayList<ArrayList<LocalDateTime>> phoebeTask1PrepTime = new ArrayList<>();
//        ArrayList<LocalDateTime> phoebePrepTime1 = new ArrayList<>();
//        LocalDateTime phoebePrepTime1Start = LocalDateTime.of(2022, 12, 12, 15, 0);
//        LocalDateTime phoebePrepTime1End = LocalDateTime.of(2022, 12, 12, 16, 0);
//        phoebePrepTime1.add(phoebePrepTime1Start);
//        phoebePrepTime1.add(phoebePrepTime1End);
//
//        ArrayList<LocalDateTime> phoebePrepTime2 = new ArrayList<>();
//        LocalDateTime phoebePrepTime2Start = LocalDateTime.of(2022, 12, 13, 15, 0);
//        LocalDateTime phoebePrepTime2End = LocalDateTime.of(2022, 12, 13, 16, 0);
//        phoebePrepTime2.add(phoebePrepTime2Start);
//        phoebePrepTime2.add(phoebePrepTime2End);
//
//        phoebeTask1PrepTime.add(phoebePrepTime1);
//        phoebeTask1PrepTime.add(phoebePrepTime2);
//        phoebeTask1.setPrepTimeScheduled(phoebeTask1PrepTime);
//
//        // PHOEBE TASK 2
//        entities.Event phoebeTask2 = new entities.Event("Meet with friends", "PhoebeTask2", 1,
//                LocalDateTime.of(2022, 12, 15, 18, 0),
//                LocalDateTime.of(2022, 12, 15, 19, 0),
//                false, "");
//
//        // CREATING COLLABORATIVE TASK
//        CollaborativeTask collaborativeTask = new CollaborativeTask("Work on project", "CollaborativeTask1",
//                1, true, "weekly",
//                LocalDateTime.of(2022, 12, 7, 10, 0),
//                LocalDateTime.of(2022, 12, 7, 11, 0),
//                LocalDateTime.of(2022, 12, 22, 12, 0),
//                taylorUser);
//        // SETTING TEAMMATES
//        ArrayList<StudentUser> teammates = new ArrayList<>();
//        teammates.add(phoebeUser);
//        collaborativeTask.setTeammates(teammates);
//
//        // CREATING HASH MAP
//        HashMap<String, Task> hashMap = new HashMap<>();
//        hashMap.put("TaylorTask1", taylorTask1);
//        hashMap.put("TaylorTask2", taylorTask2);
//        hashMap.put("PhoebeTask1", phoebeTask1);
//        hashMap.put("PhoebeTask2", phoebeTask2);
//        hashMap.put("CollaborativeTask1", collaborativeTask);
//
//
//        // MAKING INPUT DATA
//        // INPUT INTO THE USER INTERFACE SCREEN
//        String taskName = "Work on project";
//        String startTime = "2022-12-07 10:00";
//        String endTime = "2022-12-07 11:00";
//        ScheduleCTRequestModel inputData = new ScheduleCTRequestModel(taskName, startTime, endTime, taylorUser);
//
//        // RUNNING THE TEST
//        interactor.schedule(inputData, hashMap);
//
//    }
}