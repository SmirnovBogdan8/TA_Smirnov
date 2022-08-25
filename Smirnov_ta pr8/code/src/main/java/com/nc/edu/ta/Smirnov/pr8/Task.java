package com.nc.edu.ta.Smirnov.pr8;

import javax.xml.bind.annotation.*;
import java.util.Objects;
@XmlType(propOrder = {"title", "time", "startTime", "endTime", "repeatInterval", "Active", "isRepeat"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Task")
public class Task implements Cloneable
{
    private static String titleBegin = "[EDUCTR][TA]";
    /**
     * title of the scheduled event
     */
    @XmlElement
    private String title;
    /**
     * time of the scheduled event
     */
    @XmlElement
    private int time;
    /**
     * scheduled event start time
     */
    @XmlElement
    private int  startTime;
    /**
     * scheduled event end time
     */
    @XmlElement
    private int endTime;
    /**
     * repetition of an event for a specified time
     */
    @XmlElement
    private int repeatInterval;
    /**
     * event activity check
     */
    @XmlElement
    private boolean Active;
    /**
     * event repetition check
     */
    @XmlElement
    private boolean isRepeat;

    Task(){}

    /**
     * constructor for one-time task
     * @param title (name) of the task
     * @param time  is the notification time task
     */
    public Task(String title, int time) {
        if(title == null || title.equals("") || title.equals("OK"))
            throw new RuntimeException("Data entered incorrectly");
        if(time < 0)
            throw new RuntimeException("time was passed as a negative number");
        this.title = title;
        this.time = time;
    }

    /**
     *  constructor for recurring tasks
     * @param title (name) of the task
     * @param start start time
     * @param end end time
     * @param repeat repeat time
     */
    public Task(String title, int start, int end, int repeat) {
        if(title == null || title.equals("") || title.equals("OK") || end < start)
            throw new RuntimeException("Data entered incorrectly");
        if(start < 0 || repeat <= 0)
            throw new RuntimeException("time was passed as a negative number");
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = repeat;
        isRepeat = true;
    }

    /**
     * getting and setting a task
     * @return title (name) of the task
     */
    public String getTitle() {return title;}
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * task status check
     * @return Active task activity indicator
     */
    public boolean isActive(){return  Active;}
    public void setActive(boolean active){
        this.Active = active;
    }

    /**
     *  Method that returns one-time tasks
     *  @param time is the notification time task
     */
    public void setTime(int time){

        this.time = this.endTime = this.startTime = time;
        isRepeat = false;
        if(time <= 0)
            throw new RuntimeException("Data entered incorrectly");
    }

    /**
     *  Method that returns the recurring tasks
     * @param start task notification start time
     * @param end task notification termination time
     * @param repeat time interval after which it is necessary to repeat the task notification
     */
    public void setTime(int start, int end, int repeat){

        this.time = start;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = repeat;
        isRepeat = true;
        if(start < 0 || end < start || repeat <= 0)
            throw new RuntimeException("Data entered incorrectly");
    }

    /**
     *  Method the start time of the alert (for a recurring task), or single notification time (for a one-time task)
     * @return the start time
     */
    public int getTime(){
        if(isRepeat)
            return startTime;
        return time;
    }

    /**
     *  Method the start time of the alert (for a recurring task), or single notification time (for a one-time task)
     * @return the start time
     */
    public int getStartTime(){
        if(isRepeat)
            return startTime;
        else
            return time;
    }

    /**
     *  Method the end time of the alert (for a recurring task), or single notification time (for a one-time task)
     * @return the end time
     */
    public int getEndTime(){
        if(isRepeat)
            return endTime;
        return time;
    }

    /**
     *  Method time interval after which the task notification must be repeated (for a recurring task) or 0 (for a one-time task)
     * @return the time interval
     */
    public int getRepeatInterval(){
        if(!isRepeat)
            return 0;
        else
            return repeatInterval;
    }

    /**
     *  Method, information about whether the task is repeated
     * @return task repetition boolean expression
     */
    public boolean isRepeat(){return isRepeat;}

    /**
     *  Method that returns the description of this task
     * @return string with information
     */
    @Override
    public String toString() {
        if(Active) {
            if (isRepeat)
                return "Task \"some\" from " + getStartTime() + " to " + getEndTime() + " every " + getRepeatInterval() + " seconds";
            else
                return "Task \"some\" at " + getTime();
        }
        else
            return "Task \"some\" is inactive";
    }

    /**
     * The method returning the next warning time
     * @param time is the notification time task
     * @return nextTimeAfter
     */
    public int nextTimeAfter(int time){
        if(time < 0)
            throw new RuntimeException("Data entered incorrectly");
        if(isActive())
        {
            if(isRepeat)
            {
                if(endTime - getRepeatInterval() > time)
                    for(int i = startTime; i <=endTime; i+=getRepeatInterval())
                        if(time <i)
                            return i;
            }
            else
                if(time < this.getTime())
                    return getTime();
        }
        return -1;
    }

    /**
     * creating a copy of a Task
     * @return copy Task
     */
    @Override
    public Task clone() throws CloneNotSupportedException{
        return (Task) super.clone();
    }

    /**
     * task comparison
     * @param obj The Object class is the root of the class hierarchy.
     * @return When comparing objects, the “==” operation will return true only in one case - when the links point to the same object
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if (obj == null || hashCode() != obj.hashCode() || getClass() != obj.getClass())
            return false;
        Task task = (Task) obj;
        if (isRepeat()) {
            return
                    Objects.equals(this.isActive(), task.isActive())
                    && Objects.equals(this.getTitle(), task.getTitle())
                    && Objects.equals(this.getTime(), task.getTime())
                    && Objects.equals(this.getStartTime(),task.getStartTime())
                    && Objects.equals(this.getEndTime(),task.getEndTime())
                    && Objects.equals(this.getRepeatInterval(), task.getRepeatInterval());
            }
        else {
            return
                    Objects.equals(this.isActive(),task.isActive())
                    && Objects.equals(this.getTime(),task.getTime())
                    && Objects.equals(this.getTitle(), task.getTitle());
            }

    }

    /**
     * using a number for a task
     * @return number
     */
    @Override
    public int hashCode(){
        return title.hashCode();
    }
}