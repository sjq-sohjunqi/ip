package duke;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;
    
    /**
    * Constructor for the TaskList class.
    */
    public TaskList(){
        taskList = new ArrayList<Task>();
    }
    
    /**
    * This method returns a String containing the items inside the TaskList.
    * @return List of items in list
    */
    public String printList() {
        String res = "";
        for (int i = 0; i < taskList.size(); i++) {
            res += (i+1)+". "+taskList.get(i)+"\n";
        }
        return res;
    }
    
    /**
    * This method adds a Task object into the TaskList.
    * @param t Task to be added into TaskList
    */
    public void add(Task t) {
        taskList.add(t);
    }
    
    /**
    * This method removes a Task object from the TaskList based on the item number and returns it.
    * @param i Index of task to be removed
    * @return Task object removed from the list
    */
    public Task remove(int i) throws DukeException{
        if (i <= 0 || i > taskList.size()) {
            throw new DukeException("OOPS!!! There is no item at that position.");
        }
        
        Task task = taskList.remove(i-1);
        
        return task;
    }
    
    /**
    * This method marks a Task object from the TaskList, based on the item number, as done and returns it.
    * @param i Index of task to be marked done
    * @return Task object marked done
    */
    public Task markDone(int i) throws DukeException{
        if (i <= 0 || i > taskList.size()) {
            throw new DukeException("OOPS!!! There is no item at that position.");
        }
        
        Task task = taskList.get(i-1);
        task.mark();
        
        return task;
    }
    
    /**
    * Returns the size of the TaskList.
    * @return Size of TaskList
    */
    public int count() {
        return taskList.size();
    }
    
    /**
    * Returns the Task at the item number.
    * @param i Item number of the Task
    * @return Task object
    */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Returns a HashMap containing the task and how close today is to the due date
     * @return HashMap object with Task and the number of days
     */
    public HashMap<Task, Integer> getDueTasks() {
        HashMap<Task, Integer> taskMap = new HashMap<>();

        LocalDate today = LocalDate.now();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            if (task.getType() != 'E' && task.getType() != 'D') {
                continue;
            }

            if (today.compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(0));
                continue;
            }

            if (today.plusDays(1).compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(1));
                continue;
            }

            if (today.plusDays(2).compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(2));
                continue;
            }

            if (today.plusDays(3).compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(3));
                continue;
            }

            if (today.minusDays(1).compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(-1));
                continue;
            }

            if (today.minusDays(2).compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(-1));
                continue;
            }

            if (today.minusDays(3).compareTo(task.getDate()) == 0) {
                taskMap.put(task, new Integer(-1));
                continue;
            }

        }

        return taskMap;
    }

    /**
    * This method checks whether the two TaskList objects are the same.
    * @param tl TaskList object to be compared
    * @return Whether the TaskList object is same
    */
    public boolean equals(TaskList tl) {
        if (this.count() == tl.count()) {
            for (int i = 0; i < this.count(); i++) {
                if (!this.taskList.get(i).equals(tl.getTask(i)))
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }

}