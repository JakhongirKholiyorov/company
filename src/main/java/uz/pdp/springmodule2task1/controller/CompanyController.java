package uz.pdp.springmodule2task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springmodule2task1.entity.Address;
import uz.pdp.springmodule2task1.entity.Company;
import uz.pdp.springmodule2task1.payload.AddressDto;
import uz.pdp.springmodule2task1.payload.CompanyDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.service.AddressService;
import uz.pdp.springmodule2task1.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("company")
    public ResponseEntity<List<Company>> getCompanies(){
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id){
        Company company = companyService.getCompany(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping("company")
    public ResponseEntity<Response> addCompany(@RequestBody CompanyDto companyDto){
        Response response = companyService.addCompany(companyDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("company/{id}")
    public ResponseEntity<Response> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        Response response = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("company/{id}")
    public ResponseEntity<Response> deleteCompany(@PathVariable Integer id){
        Response response = companyService.deleteCompany(id);
        return ResponseEntity.status(response.isSuccess()? 202 : 409).body(response);
    }
}
