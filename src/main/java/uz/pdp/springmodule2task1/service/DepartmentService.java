package uz.pdp.springmodule2task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springmodule2task1.entity.Company;
import uz.pdp.springmodule2task1.entity.Department;
import uz.pdp.springmodule2task1.payload.DepartmentDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.repository.CompanyRepository;
import uz.pdp.springmodule2task1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Response addDepartment(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isPresent()){
            Department department = new Department();
            department.setName(departmentDto.getName());
            department.setCompany(optionalCompany.get());
            departmentRepository.save(department);
            return new Response("Department successfully added", true);
        }
        return new Response("There should be company in order to add department", false);
    }

    public Response editDepartment(Integer id, DepartmentDto departmentDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
            if (optionalCompany.isPresent()){
                Department department = optionalDepartment.get();
                department.setName(departmentDto.getName());
                department.setCompany(optionalCompany.get());
                departmentRepository.save(department);
                return new Response("Department successfully edited", true);
            }
            return new Response("There should be company in order to add department", false);
        }
        return new Response("There is no such a department", false);
    }

    public Response deleteDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            departmentRepository.deleteById(id);
            return new Response("Department successfully deleted", true);
        }
        return new Response("There is no such a department", false);
    }
}
