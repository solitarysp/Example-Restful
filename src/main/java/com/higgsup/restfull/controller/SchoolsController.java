package com.higgsup.restfull.controller;

import com.higgsup.dto.ClazzDTO;
import com.higgsup.dto.Response;
import com.higgsup.dto.SchoolsDTO;
import com.higgsup.model.Schools;
import com.higgsup.repo.SchoolsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/schoolses")
public class SchoolsController {
    @RequestMapping(method = RequestMethod.GET)
    public Response schools() {
        Response response = new Response();
        response.setData(SchoolsRepo.schoolsDTOS);
        response.setStatus(true);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response schoolsByID(@PathVariable("id") String id) {
        Response response = new Response();
        Optional<SchoolsDTO> schoolsDTO = SchoolsRepo.schoolsDTOS.stream().filter(schoolsDTO1 -> Objects.equals(String.valueOf(schoolsDTO1.getId()), id)).findFirst();
        if (schoolsDTO.isPresent()) {
            response.setData(schoolsDTO.get());
            response.setStatus(true);
        } else {
            response.setStatus(false);
            response.setMessage("not find schools by id");
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response deleteSchoolsById(@PathVariable("id") String id) throws CloneNotSupportedException {
        Response response = new Response();

        Optional<SchoolsDTO> schoolsDTO = SchoolsRepo.schoolsDTOS.stream().filter(schoolsDTO1 -> Objects.equals(String.valueOf(schoolsDTO1.getId()), id)).findFirst();
        if (schoolsDTO.isPresent()) {
            response.setData(schoolsDTO.get().clone());
            response.setStatus(true);
            SchoolsRepo.schoolsDTOS.remove(schoolsDTO.get());
        } else {
            response.setStatus(false);
            response.setMessage("not find schools by id");

        }
        return response;
    }

    @RequestMapping(value = "/{idSchool}/classes/{idClass}", method = RequestMethod.GET)
    public Response schoolsByIDAndClassesID(@PathVariable("idSchool") String idSchool, @PathVariable("idClass") String idClass) throws CloneNotSupportedException {
        Response response = new Response();

        Optional<SchoolsDTO> schoolsDTO = SchoolsRepo.schoolsDTOS.stream().filter(
                schoolsDTO1 -> Objects.equals(String.valueOf(schoolsDTO1.getId()), idSchool)
        ).findFirst();
        if (schoolsDTO.isPresent()) {
            SchoolsDTO schoolsDTO1 = schoolsDTO.get();
            schoolsDTO1 = (SchoolsDTO) schoolsDTO1.clone();
            List<ClazzDTO> clazzDTOS = (List<ClazzDTO>) ((ArrayList<ClazzDTO>) schoolsDTO1.getClazzDTOs()).clone();
            Optional<ClazzDTO> clazzDTOOptional = clazzDTOS.stream().filter(clazzDTO -> String.valueOf(clazzDTO.getId()).equals(idClass)).findFirst();
            if (clazzDTOOptional.isPresent()) {
                schoolsDTO1.setClazzDTOs(new ArrayList<ClazzDTO>(Arrays.asList(clazzDTOOptional.get())));

            } else {
                schoolsDTO1.setClazzDTOs(new ArrayList<>());
            }
            response.setData(schoolsDTO1);
            response.setStatus(true);
        } else {
            response.setStatus(false);
            response.setMessage("not find");
        }
        return response;

    }

    @RequestMapping(value = "/{idSchool}/classes", method = RequestMethod.GET)
    public Response schoolsByIDAndClasses(@PathVariable("idSchool") String idSchool) throws CloneNotSupportedException {
        Response response = new Response();
        Optional<SchoolsDTO> schoolsDTO = SchoolsRepo.schoolsDTOS.stream().filter(schoolsDTO1 -> Objects.equals(String.valueOf(schoolsDTO1.getId()), idSchool)).findFirst();
        if (schoolsDTO.isPresent()) {
            response.setData(schoolsDTO.get().clone());
            response.setStatus(true);
            SchoolsRepo.schoolsDTOS.remove(schoolsDTO.get());
        } else {
            response.setStatus(false);
            response.setMessage("not find schools by id");

        }
        return response;
    }

    @RequestMapping(value = "/{idSchool}", method = RequestMethod.PUT)
    public Response updateNameSchoolsBy(@PathVariable("idSchool") String idSchool, @RequestParam("name") String name) throws CloneNotSupportedException {
        Response response = new Response();
        Optional<SchoolsDTO> schoolsDTO = SchoolsRepo.schoolsDTOS.stream().filter(schoolsDTO1 -> Objects.equals(String.valueOf(schoolsDTO1.getId()), idSchool)).findFirst();
        if (schoolsDTO.isPresent()) {
            SchoolsDTO schoolsDTO1 = schoolsDTO.get();
            schoolsDTO1.setName(name);
            response.setData(schoolsDTO1);
            response.setStatus(true);
        } else {
            response.setStatus(false);
            response.setMessage("not find schools by id");
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object createSchools(@RequestBody Schools schools) throws CloneNotSupportedException {
        Response response = new Response();
        Optional<SchoolsDTO> schoolsDTO = SchoolsRepo.schoolsDTOS.stream().filter(schoolsDTO1 -> Objects.equals(String.valueOf(schoolsDTO1.getId()), schools.getId())).findFirst();
        if (schoolsDTO.isPresent()) {
            response.setMessage("id is exist");
            response.setStatus(false);
        } else {
            SchoolsDTO schoolsDTO1 = new SchoolsDTO();
            schoolsDTO1.setId(schools.getId());
            schoolsDTO1.setName(schools.getName());
            SchoolsRepo.schoolsDTOS.add(schoolsDTO1);

            response.setData(schoolsDTO1);
            response.setStatus(true);
        }
        return response;
    }
}
