package br.com.wandaymo.awesome.endpoint;


import br.com.wandaymo.awesome.model.Student;
import br.com.wandaymo.awesome.util.DateUtil;
import error.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("students")
public class StudentEndPoint {
    //Configura as dependências e instacia o objeto
    private DateUtil dateUtil;

    @Autowired
    public StudentEndPoint(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }
        
    //@RequestMapping(method = RequestMethod.GET) ou
    @GetMapping
    public ResponseEntity<?> listALL(){
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }
    
    //@RequestMapping(method = RequestMethod.GET, path = "/{id}") ou
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);
        if(index == -1) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
        }
    }
    
    //@RequestMapping(method = RequestMethod.POST) ou
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    
    //@RequestMapping(method = RequestMethod.PUT) ou
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        Student.studentList.remove(student);
        Student.studentList.add(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //@RequestMapping(method = RequestMethod.DELETE) ou
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Student student){
        Student.studentList.remove(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
