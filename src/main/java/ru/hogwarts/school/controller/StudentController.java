package ru.hogwarts.school.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
@Tag(name = "API для работы со студентами")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение студента по id")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping()
    @Operation(summary = "Создание студента")
    public ResponseEntity<Student> create(@RequestBody Student studentR) {
        Student student = studentService.addStudent(studentR);
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    @Operation(summary = "Обновление студента")
    public ResponseEntity<Student> update(@RequestBody Student studentR) {
        Student student = studentService.updateStudent(studentR);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление студента")
    public ResponseEntity<Student> remove(@PathVariable Long id) {
        Student student = studentService.deleteStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("all")
    @Operation(summary = "Получение всех студентов")
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping()
    @Operation(summary = "Студенты одного возраста")
    public ResponseEntity<Collection<Student>> getByAge(@RequestBody Integer age) {
        Collection<Student> students = studentService.findStudentsByAge(age);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/find/age")
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam(required = false) Integer age,
                                                                 @RequestParam(required = false) Integer min,
                                                                 @RequestParam(required = false) Integer max) {
        if (age != null) {
            return ResponseEntity.ok(studentService.findStudentsByAge(age));
        } else if (min != null && max != null) {
            return ResponseEntity.ok(studentService.findStudentsByAge(min, max));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find/faculty/{id}")
    public ResponseEntity<Collection<Student>> findStudentsByFacultyId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentsByFacultyId(id));
    }
}
