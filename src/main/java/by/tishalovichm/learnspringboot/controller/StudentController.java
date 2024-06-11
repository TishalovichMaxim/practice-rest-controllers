package by.tishalovichm.learnspringboot.controller;

import by.tishalovichm.learnspringboot.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        var student = new Student(1, "Maxim", "Tishalovich");
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("Some", "Value")
                .body(student);
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {
        var students = List.of(
            new Student(1, "Maxim", "Tishalovich"),
            new Student(2, "Nikita", "Stahnov"),
            new Student(3, "Danik", "Prokopchuk")
        );

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable int id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {

        return new Student(id, firstName, lastName);
    }

    @GetMapping("/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {

        return new Student(id, firstName, lastName);
    }

    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id) {
        System.out.println("Student with id " + id + " deleted");
    }

}
