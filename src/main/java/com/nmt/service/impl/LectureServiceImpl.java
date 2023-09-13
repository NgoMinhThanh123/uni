/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.service.impl;

import com.nmt.model.Lecturer;
import com.nmt.repository.LecturerRepository;
import com.nmt.service.LecturerService;
import dto.LecturerDto;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author acer
 */


@Service
public class LectureServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturersRepo;

    @Override
    public List<Lecturer> getLecturers(Map<String, String> params) {
        return this.lecturersRepo.getLecturers(params);
    }

    @Override
    public int countLecturers() {
        return this.lecturersRepo.countLecturers();
    }

    @Override
    public boolean addLeturer(Lecturer l) {
        return this.lecturersRepo.addLeturer(l);
    }

    @Override
    public boolean updateLeturer(Lecturer l) {
        return this.lecturersRepo.updateLeturer(l);
    }

    @Override
    public Lecturer getLecturerById(String id) {
        return this.lecturersRepo.getLecturerById(id);
    }

    @Override
    public boolean deleteLecturer(String id) {
        return this.lecturersRepo.deleteLecturer(id);

    }

    @Override
    public LecturerDto getLecturerByUsername(String username) {
        Lecturer u = this.lecturersRepo.getLecturerByUsername(username);
        LecturerDto lecturerDto = new LecturerDto();
        lecturerDto.setId(u.getId());
        lecturerDto.setName(u.getName());
        lecturerDto.setBirthday(u.getBirthday());
        lecturerDto.setGender(u.getGender());
        lecturerDto.setPhone(u.getPhone());
        lecturerDto.setAddress(u.getAddress());
        lecturerDto.setFacultyId(u.getFacultyId());
        
        return lecturerDto;
    }

}
