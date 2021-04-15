package br.com.wandaymo.awesome.endpoint;


import br.com.wandaymo.awesome.model.Student;
import br.com.wandaymo.awesome.repository.StudentRepository;
import error.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndPoint {

    private final StudentRepository studentDAO;
    @Autowired
    public StudentEndPoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }
        
    //@RequestMapping(method = RequestMethod.GET) or
    @GetMapping
    public ResponseEntity<?> listALL() {
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }
    
    //@RequestMapping(method = RequestMethod.GET, path = "/{id}") or
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        Optional<Student> student = studentDAO.findById(id);
        if(student == null) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }
    
    //@RequestMapping(method = RequestMethod.POST) or
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.OK);
    }
    
    //@RequestMapping(method = RequestMethod.PUT) or
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //@RequestMapping(method = RequestMethod.DELETE) or
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
