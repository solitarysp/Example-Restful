package com.higgsup.repo;

import com.higgsup.dto.ClazzDTO;
import com.higgsup.dto.SchoolsDTO;
import com.higgsup.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class SchoolsRepo {
    public static ArrayList<SchoolsDTO> schoolsDTOS = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            SchoolsDTO schoolsDTO = new SchoolsDTO();
            schoolsDTO.setId(i);
            schoolsDTO.setName("schoolses " + i);
            schoolsDTO.setClazzDTOs(setClassFotSchhols(schoolsDTO));
            schoolsDTOS.add(schoolsDTO);
        }
    }

    private static List<ClazzDTO> setClassFotSchhols(SchoolsDTO schoolsDTO) {
        List<ClazzDTO> clazzDTOS = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ClazzDTO clazzDTO = new ClazzDTO();
            clazzDTO.setId(i);
            clazzDTO.setName("class of Schools " + schoolsDTO.getName() + " has id is " + i);
            clazzDTO.setStudentDTOS(setStudentDTO(clazzDTO));
            clazzDTOS.add(clazzDTO);
        }
        return clazzDTOS;

    }

    private static List<StudentDTO> setStudentDTO(ClazzDTO clazzDTO) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(i);
            studentDTO.setName("student of class " + clazzDTO.getName() + " has id is " + i);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
}
