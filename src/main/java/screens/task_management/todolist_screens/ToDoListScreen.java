package screens.task_management.todolist_screens;

import screens.task_management.task_edit_delete_screens.TaskDeletionController;
import screens.task_management.task_edit_delete_screens.TaskDeletionResponseFormatter;
import screens.task_management.task_edit_delete_screens.TaskEditResponseFormatter;
import screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens.AssignmentEditController;
import screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens.AssignmentEditDeleteScreen;
import screens.task_management.task_edit_delete_screens.assignment_edit_delete_screens.AssignmentInfoRetriever;
import screens.task_management.task_edit_delete_screens.event_edit_delete_screens.EventEditController;
import screens.task_management.task_edit_delete_screens.event_edit_delete_screens.EventEditDeleteScreen;
import screens.task_management.task_edit_delete_screens.event_edit_delete_screens.EventInfoRetriever;
import screens.task_management.task_edit_delete_screens.test_edit_delete_screens.TestEditController;
import screens.task_management.task_edit_delete_screens.test_edit_delete_screens.TestEditDeleteScreen;
import screens.task_management.task_edit_delete_screens.test_edit_delete_screens.TestInfoRetriever;
import screens.task_management.FileTaskMap;
import use_cases.task_management.read_write.TaskMapGateway;
import use_cases.task_management.task_deletion_use_case.TaskDeletionInputBoundary;
import use_cases.task_management.task_deletion_use_case.TaskDeletionInteractor;
import use_cases.task_management.task_deletion_use_case.TaskDeletionPresenter;
import use_cases.task_management.task_edit_use_case.*;
import use_cases.task_management.todolist_use_case.ToDoListResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The View for the ToDoList Use Case
 */

public class ToDoListScreen extends JPanel implements ActionListener {

    ToDoListPresenter presenter;

    JButton back;

    JPanel screens;

    CardLayout screenLayout;

    public ToDoListScreen(ToDoListPresenter presenter, JPanel screens, CardLayout screenLayout) {
        this.presenter = presenter;
        this.screens = screens;
        this.screenLayout = screenLayout;

        JLabel title = new JLabel("ToDo List");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        back = new JButton("Return");
        back.addActionListener(this);

        JPanel toDoList = new JPanel();
        toDoList.setLayout(new BoxLayout(toDoList, BoxLayout.PAGE_AXIS));

        try {
            ToDoListResponseModel rsp = presenter.getToDoList();
            ArrayList<ArrayList<String>> tasks = rsp.getToDoListView();

            for (ArrayList<String> task: tasks) {
                JButton editDelete = new JButton("Edit/Delete");
                editDelete.putClientProperty("taskName", task.get(0));
                editDelete.putClientProperty("taskId", task.get(1));
                editDelete.putClientProperty("taskType", task.get(2));

                editDelete.addActionListener(this);
                JLabel taskName = new JLabel(task.get(0));

                LabelButtonPanel listItem = new LabelButtonPanel(taskName, editDelete);
                listItem.setPreferredSize(new Dimension(250, 40));
                listItem.setAlignmentX(LabelButtonPanel.CENTER_ALIGNMENT);

                toDoList.add(listItem);
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        JScrollPane scrollableToDoList = new JScrollPane(toDoList);
        scrollableToDoList.setPreferredSize(new Dimension(300, 200));
        scrollableToDoList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableToDoList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(title);
        this.add(scrollableToDoList);
        this.add(back);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getSource() == back) {
            screenLayout.show(screens, "StudentMain");
        } else {
            JButton taskButton = (JButton) evt.getSource();
            //String taskName = (String) taskButton.getClientProperty("taskName");
            String taskId = (String) taskButton.getClientProperty("taskId");
            String taskType = (String) taskButton.getClientProperty("taskType");

            // create use case components
            TaskEditPresenter taskEditPresenter = new TaskEditResponseFormatter();
            TaskMapGateway taskMapGateway = new FileTaskMap("src/main/java/data/TaskMap.txt");
            TaskEditInputBoundary taskEditInteractor = new TaskEditInteractor(taskMapGateway, taskEditPresenter);
            EventEditController eventEditController = new EventEditController(taskEditInteractor);
            AssignmentEditController assignmentEditController = new AssignmentEditController(taskEditInteractor);
            TestEditController testEditController = new TestEditController(taskEditInteractor);

            TaskDeletionPresenter taskDeletionPresenter = new TaskDeletionResponseFormatter();
            TaskDeletionInputBoundary taskDeletionInteractor = new TaskDeletionInteractor(taskMapGateway, taskDeletionPresenter);
            TaskDeletionController taskDeletionController = new TaskDeletionController(taskDeletionInteractor);

            //change card to corresponding task type edit/delete screen
            if (taskType.equals("Assignment")) {
                AssignmentDisplayer assignmentInfo = new AssignmentInfoRetriever(taskId);
                AssignmentEditDeleteScreen assignmentEditDeleteScreen = new AssignmentEditDeleteScreen(
                        assignmentEditController, taskDeletionController, screens, screenLayout, assignmentInfo);
                screens.add("assignmentEdit", assignmentEditDeleteScreen);
                screenLayout.show(screens, "assignmentEdit");
            } else if (taskType.equals("Test")) {
                TestDisplayer testInfo = new TestInfoRetriever(taskId);
                TestEditDeleteScreen testEditDeleteScreen = new TestEditDeleteScreen(
                        testEditController, taskDeletionController, screens, screenLayout, testInfo);
                screens.add("testEdit", testEditDeleteScreen);
                screenLayout.show(screens, "testEdit");
            } else {
                EventDisplayer eventInfo = new EventInfoRetriever(taskId);
                EventEditDeleteScreen eventEditDeleteScreen = new EventEditDeleteScreen(
                        eventEditController, taskDeletionController, screens, screenLayout, eventInfo);
                screens.add("eventEdit", eventEditDeleteScreen);
                screenLayout.show(screens, "eventEdit");
            }
        }
    }

}