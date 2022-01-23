package uz.pdp.springmodule2task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springmodule2task1.entity.Company;
import uz.pdp.springmodule2task1.entity.Department;
import uz.pdp.springmodule2task1.payload.CompanyDto;
import uz.pdp.springmodule2task1.payload.DepartmentDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.service.CompanyService;
import uz.pdp.springmodule2task1.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("department")
    public ResponseEntity<List<Department>> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Integer id){
        Department department = departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping("department")
    public ResponseEntity<Response> addDepartment(@RequestBody DepartmentDto departmentDto){
        Response response = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("department/{id}")
    public ResponseEntity<Response> editDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto){
        Response response = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("department/{id}")
    public ResponseEntity<Response> deleteDepartment(@PathVariable Integer id){
        Response response = departmentService.deleteDepartment(id);
        return ResponseEntity.status(response.isSuccess()? 202 : 409).body(response);
    }
}
