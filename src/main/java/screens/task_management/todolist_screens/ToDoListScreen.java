package screens.task_management.todolist_screens;

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

    public ToDoListScreen(ToDoListPresenter presenter) {

        this.presenter = presenter;

        JLabel title = new JLabel("ToDo List");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JPanel toDoList = new JPanel();
        toDoList.setLayout(new BoxLayout(toDoList, BoxLayout.PAGE_AXIS));

        ToDoListResponseModel rsp = presenter.getToDoList();
        ArrayList<ArrayList<String>> tasks =  rsp.getToDoListView();

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

        JScrollPane scrollableToDoList = new JScrollPane(toDoList);
        scrollableToDoList.setPreferredSize(new Dimension(300, 200));
        scrollableToDoList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableToDoList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(title);
        this.add(scrollableToDoList);

        //TODO return button
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        JButton taskButton = (JButton)evt.getSource();
        String taskName = (String) taskButton.getClientProperty("taskName");
        String taskId = (String) taskButton.getClientProperty("taskId");
        String taskType = (String) taskButton.getClientProperty("taskType");

        //change card to corresponding task type edit/delete screen
        if (taskType.equals("Assignment")) {
            //TODO change card layout to Assignment edit screen
        } else if (taskType.equals("Test")) {
            //TODO change card layout to Test edit screen
        } else {
            //TODO change card layout to Event edit screen
        }
    }

}
