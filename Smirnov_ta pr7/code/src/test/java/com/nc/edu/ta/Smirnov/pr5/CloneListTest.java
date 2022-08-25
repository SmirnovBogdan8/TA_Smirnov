package com.nc.edu.ta.Smirnov.pr5;

import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CloneListTest extends AbstractTaskListTest {

    public CloneListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }
    
    @Test
    public void testClone() throws CloneNotSupportedException {
        Task[] elements = { task("A"), task("B"), task("C") };
        addAll(elements);
        AbstractTaskList clone =  tasks.clone();
        
        assertEquals(getTitle(), tasks.size(), clone.size());
        Iterator<?> ti = tasks.iterator();
        Iterator<?> ci = clone.iterator();
        while (ti.hasNext() && ci.hasNext())
            assertEquals(getTitle(), ti.next(), ci.next());
            
        assertNotSame(getTitle(), tasks, clone);
        
        clone.add(task("D"));
        assertEquals(getTitle(), clone.size() - 1, tasks.size());
        
        clone.remove(task("B"));
        assertContains(elements);
    }
}
