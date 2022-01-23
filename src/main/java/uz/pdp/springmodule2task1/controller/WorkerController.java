package uz.pdp.springmodule2task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springmodule2task1.entity.Department;
import uz.pdp.springmodule2task1.entity.Worker;
import uz.pdp.springmodule2task1.payload.DepartmentDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.payload.WorkerDto;
import uz.pdp.springmodule2task1.service.DepartmentService;
import uz.pdp.springmodule2task1.service.WorkerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping("worker")
    public ResponseEntity<List<Worker>> getWorkers(){
        List<Worker> workers = workerService.getWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("worker/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id){
        Worker worker = workerService.getWorker(id);
        return ResponseEntity.ok(worker);
    }

    @PostMapping("worker")
    public ResponseEntity<Response> addWorker(@RequestBody WorkerDto workerDto){
        Response response = workerService.addWorker(workerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("worker/{id}")
    public ResponseEntity<Response> editWorker(@PathVariable Integer id, @RequestBody WorkerDto workerDto){
        Response response = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("worker/{id}")
    public ResponseEntity<Response> deleteWorker(@PathVariable Integer id){
        Response response = workerService.deleteWorker(id);
        return ResponseEntity.status(response.isSuccess()? 202 : 409).body(response);
    }
}
