package com.nc.edu.ta.Smirnov.pr8;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TaskXMLSerializer{
    public void save(AbstractTaskList abstractTaskList,String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(AbstractTaskList.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String path="./"+filename+".xml";
        mar.marshal(abstractTaskList, new File(path));
    }

    /**
     * unmarshalling repository from xml file
     * @param fileName, name of xml file
     * @return contract repository
     */
    public AbstractTaskList load(String fileName) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(AbstractTaskList.class);
        String path="./"+fileName+".xml";
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (AbstractTaskList) context.createUnmarshaller()
                .unmarshal(new FileReader(path));
    }
}