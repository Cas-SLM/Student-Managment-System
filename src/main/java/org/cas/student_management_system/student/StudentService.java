package org.cas.student_management_system.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service //makes the class a component/spring "bean"
public class StudentService {

    public List<Student> getStudents() {
        return List.of(new Student(
                1L,
                "Mariam",
                "mariam.jamal@gmail.com",
                LocalDate.of(2000, Month.APRIL, 15)
        ));
    }
}
