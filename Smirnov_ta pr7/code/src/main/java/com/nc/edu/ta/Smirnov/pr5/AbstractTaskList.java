package com.nc.edu.ta.Smirnov.pr5;

import javax.xml.bind.annotation.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
@XmlType(propOrder = {"actual"})
@XmlSeeAlso({ArrayTaskList.class,LinkedTaskList.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AbstractTaskList")
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable
{

    /**
     * number of active tasks
     */
    @XmlElement
    protected static int actual = 0;

    /**
     * adding task
     * @param task transferred task
     */
    public abstract void add(Task task);

    /**
     * deleting tasks
     * @param task transferred task
     */
    public abstract void remove(Task task);

    /**
     * getting a task by number in the list
     * @param index transferred task number in the list
     * @return task
     */
    public abstract Task getTask(int index);

    /**
     * getting the dimension of a list or array of tasks
     * @return size of list or array
     */
    public abstract int size();

    /**
     * displays a list of tasks in a given period of time
     * @param i initial time value
     * @param j final time value
     * @return task list or array list
     */
    public abstract Task[] incoming(int i, int j);

    @Override
    public Iterator<Task> iterator() {
        AbstractTaskList tasks = this;
        return new Iterator<Task>() {
            private int currentIndex = 0;
            Task currentTask = null;
            int nextIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < tasks.size();
            }

            @Override
            public Task next() {
                if(hasNext()){
                    currentTask = tasks.getTask(currentIndex);
                    currentIndex++;
                    return currentTask;
                }
                else
                    throw new NoSuchElementException("no element");
            }

            @Override
            public void remove() {
                if (nextIndex == currentIndex)
                    throw new IllegalStateException("no element");
                nextIndex = currentIndex;
                tasks.remove(currentTask);
            }
        };
    }

    /**
     * copies the task that we pass through this method
     * @return produces a copy of the given task
     * @throws CloneNotSupportedException Thrown to indicate that the clone method in class Object has been called to clone an object
     */
    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        AbstractTaskList clone = (AbstractTaskList) super.clone();
//        this.forEach(clone::remove);
//        for (int i = 0; i < size(); i++) {
//            clone.add(getTask(i));
//        }
        for (Task task1 : this) {
            clone.remove(task1);
        }
        forEach(task -> {
            clone.add(task);
        });
        return clone;
    }

    /**
     * implements a task string
     * @return tasks string
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < size(); i++)
            text = new StringBuilder(" [ " + text + ", " + " ] ");
        return this.getClass().getSimpleName() + text;
    }

    /**
     * using a number for a task
     * @return number
     */
    @Override
    public int hashCode(){
        int hash = this.size();
        if(hash==0) return hash;
        hash = hash + getTask(0).hashCode();
        return hash;
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
        if (obj == null || hashCode() != obj.hashCode() || getClass() != obj.getClass() || this.size() != this.size())
            return false;
        AbstractTaskList abstractTaskList = (AbstractTaskList) obj;
        for(int i = 0; i < size(); i++)
            if(this.getTask(i) != abstractTaskList.getTask(i))
                return false;
        return
                Objects.equals(this.size(),this.size());
    }
}
