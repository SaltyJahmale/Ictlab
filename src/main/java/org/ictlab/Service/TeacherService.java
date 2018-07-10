package org.ictlab.Service;

import org.ictlab.domain.Teacher;
import org.ictlab.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * @return List<Teacher>
     */
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
