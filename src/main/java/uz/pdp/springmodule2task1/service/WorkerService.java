package uz.pdp.springmodule2task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springmodule2task1.entity.Address;
import uz.pdp.springmodule2task1.entity.Department;
import uz.pdp.springmodule2task1.entity.Worker;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.payload.WorkerDto;
import uz.pdp.springmodule2task1.repository.AddressRepository;
import uz.pdp.springmodule2task1.repository.DepartmentRepository;
import uz.pdp.springmodule2task1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getWorkers(){
        return workerRepository.findAll();
    }

    public Worker getWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    public Response addWorker(WorkerDto workerDto){
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (existsByPhoneNumber){
            return new Response("This phone number already exits", false);
        }
        if (!optionalAddress.isPresent()){
            return new Response("Address should be exit to add worker", false);
        }
        if (!optionalDepartment.isPresent()) {
            return new Response("Department should be exit to add worker", false);
        }
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new Response("worker successfully added", true);
    }

    public Response editWorker(Integer id, WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()){
            return new Response("There is no such a worker", false);
        }
        boolean exists = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (exists){
            return new Response("This phone number already exits", false);
        }
        if (!optionalAddress.isPresent()){
            return new Response("Address should be exit to edit worker", false);
        }
        if (!optionalDepartment.isPresent()) {
            return new Response("Department should be exit to edit worker", false);
        }
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new Response("worker successfully edited", true);
    }

    public Response deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()){
            workerRepository.deleteById(id);
            return new Response("Worker successfully deleted", true);
        }
        return new Response("There is no such a worker", false);
    }
}
