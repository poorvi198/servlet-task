package com.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.Student;

import java.io.IOException;

public class JSONConvertor {
    public String convert(Student student) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }
}
