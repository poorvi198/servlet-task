package com.convertor;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.student.Student;

import java.io.IOException;

public class XMLConvertor {
    public String convert(Student student) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(student);
    }
}
