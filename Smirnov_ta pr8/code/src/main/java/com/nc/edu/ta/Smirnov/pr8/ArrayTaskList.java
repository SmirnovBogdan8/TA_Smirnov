package com.nc.edu.ta.Smirnov.pr8;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"beginSize", "size", "TaskT"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ArrayTaskList")
public class ArrayTaskList extends AbstractTaskList
{
    /**
     * array memory allocation
     */
    @XmlElement
    private int beginSize = 16;

    /**
     * number of completed tasks
     */
    @XmlElement
    private int size;

    /**
     * task array
     */
    @XmlElement
    private Task[] taskT = new Task[beginSize];

    /**
     * constructor, specifies the initial position of the program
     */
    public ArrayTaskList() {
        actual++;
        size = 0;
    }

    /**
     * creating a new array and copying it to the original one
     * @param task passing the task parameter from the class "Task"
     */
    @Override
    public void add(Task task){
        if(task==null)
            throw new RuntimeException("This task is empty!");
        if(size >= beginSize) {
            beginSize += 10;
        }
        Task[] newArr = new Task[beginSize];
        for (int i = 0; i < size; i++)
            newArr[i] = taskT[i];
        newArr[size] = task;
        taskT = newArr;
        size++;
    }

    /**
     *
     * @param task passing the task parameter from the class "Task"
     */
    @Override
    public void remove(Task task){
        if(task == null || size == 0) {
            throw new RuntimeException();
        }
        int index =-1;
        for(int i = 0; i < size; i++)
            if(taskT[i].equals(task)) {
                index = i;
                break;
            }
        Task[] newArr = new Task[size]; //
        for(int i = 0; i < index; i++)
            newArr[i] = taskT[i];
        for(int i = index + 1; i < size; i++) {
            newArr[i - 1] = taskT[i];
        }
        taskT = newArr;
        size--;
    }
    @Override
    public Task getTask(int index){
        if(index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException("Index is out of range");
        return taskT[index];
    }
    @Override
    public int size() {
        return size;
    }

    /**
     * creating an array in tasks for a certain period of time
     * @param from minimum time value
     * @param to maximum time value
     * @return array of tasks of this category
     */
    @Override
    public Task[] incoming(int from, int to) {
        if(from < 0 || to < from)
            throw new RuntimeException("invalid values entered");
        int sizeIncoming = 0;

        Task[] tasks = new Task[sizeIncoming];

        for(Task task : taskT) {
            if(task == null) {
                continue;
            }
            if(!task.isActive()) {
                continue;
            }

            if(!task.isRepeat() && task.isActive() && task.getTime() > from && task.getTime() <= to) {
                Task[] newTasks = new Task[sizeIncoming+1];
                System.arraycopy(tasks, 0, newTasks, 0, sizeIncoming);
                newTasks[sizeIncoming] = task;
                sizeIncoming++;
                tasks = newTasks;
            }

            if(task.isRepeat()) {
                int time = task.getStartTime();
                while(time <= task.getEndTime()) {
                    if(time > to) break;
                    if(time > from) {
                        Task[] newTasks = new Task[sizeIncoming+1];
                        System.arraycopy(tasks, 0, newTasks, 0, sizeIncoming);
                        newTasks[sizeIncoming] = task;
                        sizeIncoming++;
                        tasks = newTasks;
                        break;
                    }
                    time += task.getRepeatInterval();
                }
            }
        }
//        if(numberTask == 0) return new Task[sizeIncoming];
//        else sizeIncoming = ((numberTask/16)+1)*16;
//        Task[] tasks = new Task[sizeIncoming];
//        int index = 0;
//        for(Task task : taskT) {
//            if(index == 16) break;
//            if(task != null && task.getTime() > from && task.getTime() <= to) {
//                tasks[index] = task;
//                index++;
//            }
//        }
        return tasks;
    }
}