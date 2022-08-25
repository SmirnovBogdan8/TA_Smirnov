package com.nc.edu.ta.Smirnov.pr8;

import javax.xml.bind.annotation.*;
import java.time.temporal.ChronoUnit;

@XmlType(propOrder = {"size", "head"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LinkedTaskList")
public class LinkedTaskList extends AbstractTaskList
{
    /**
     * singly linked list size variable
     */
    @XmlElement
    private int size = 0;

    @XmlType(propOrder = {"task"})
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "Task")
    public static class Node{
        /**
         * task variable created in a singly linked list
         */
        @XmlElement
        public Task task;

        /**
         * variable to move to the next element in the list
         */
        public Node nextNode;

        /**
         * constructor for a node in a singly linked list
         * @param task defines a task in a node
         */
        Node(Task task){
            this.task = task;
            nextNode = null;
        }
        Node(){}
    }

    /**
     * first element variable
     */
    @XmlElement
    Node head;

    /**
     * a class constructor in which, when it is called, the first element of the list is set to zero
     */
    public LinkedTaskList(){
        head = null;
    }

    /**
     * check for first empty element
     * @return first element of the list
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * adding a new element to the list
     * @param task task parameter in method add
     */
    @Override
    public void add(Task task){
        if(task == null)
            throw new RuntimeException("Task is empty!");
        Node temp = new Node(task);
        Node current = head;
        if(head == null)
            head = temp;
        else{
             while (current.nextNode != null){
                current = current.nextNode;
             }
             current.nextNode = temp;
        }
        size++;
    }

    /**
     * removing the specified element from the list
     * @param task task parameter in method remove
     */
    @Override
    public void remove(Task task){
        Node current = head;
        if(task==null) throw new RuntimeException("Task is null");
        if(isEmpty()) {
            throw new NullPointerException("List is empty!");
        }
//        else {
//            second = current;
//            current = current.nextNode;
//        }
        if(head.task.equals(task)) {
            head = head.nextNode;
            size--;
            return;
        }

        while(current.nextNode != null) {
            if(!current.nextNode.task.equals(task)) {
                current = current.nextNode;
                continue;
            }
            current.nextNode = current.nextNode.nextNode;
            size--;
        }

//        if(current == head)
//            head = head.nextNode;
//        else
//            second.nextNode = current.nextNode;
//        size--;
    }

    /**
     ** getting a task from the list by index
     * @param index accessing a list element by index
     * @return a task by index
     */
    @Override
    public Task getTask(int index){
        if(index < 0 || index > size() - 1)
            throw new IndexOutOfBoundsException("Index is out of range");
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.nextNode;
        }
        return temp.task;
    }

    /**
     * getting a task from the list by index
     * @return a task by index
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * out in Main
     */
    public void Print(){
        Node current = head;
        if(head != null)
            System.out.println(head.task);
        while (true) {
            assert current != null;
            if (current.nextNode == null) break;
            current = current.nextNode;
            System.out.println(current.task);
        }

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
        Node current = head;
//        Node second = head;
        int size = 0;
        Task[] tasks = new Task[size];
//        int index = 0;

        while(current != null) {
            Task task = current.task;
            if(!task.isActive()) {
                current = current.nextNode;
                continue;
            }
            if(!task.isRepeat() && task.getTime() > from && task.getTime() <= to ) {
                Task[] newTasks = new Task[size+1];
                System.arraycopy(tasks, 0, newTasks, 0, size);
                newTasks[size] = current.task;
                size++;
                tasks = newTasks;
            }
            if(task.isRepeat()) {
                int time = task.getStartTime();
                while(time <= task.getEndTime()) {
                    if(time > to) break;
                    if(time > from) {
                        Task[] newTasks = new Task[size+1];
                        System.arraycopy(tasks, 0, newTasks, 0, size);
                        newTasks[size] = task;
                        size++;
                        tasks = newTasks;
                        break;
                    }
                    time += task.getRepeatInterval();
                }
            }
            current = current.nextNode;
        }


//        while (current != null) {
//            size++;
//            current = current.nextNode;
//        }
//        while (head != null) {
//            tasks[index] = second.task;
//            index++;
//            second = second.nextNode;
//        }
        return tasks;
    }
}
