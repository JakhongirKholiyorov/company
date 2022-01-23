package uz.pdp.springmodule2task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.springmodule2task1.entity.Address;
import uz.pdp.springmodule2task1.entity.Company;
import uz.pdp.springmodule2task1.payload.CompanyDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.repository.AddressRepository;
import uz.pdp.springmodule2task1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public Response addCompany(CompanyDto companyDto) {
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isPresent()) {
            Company company = new Company();
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());
            Address address = optionalAddress.get();
            company.setAddress(address);
            return new Response("Company successfully added", true);
        }
        return new Response("There is no such a company", false);
    }

    public Response editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
            if (optionalAddress.isPresent()) {
                Company company = optionalCompany.get();
                company.setCorpName(companyDto.getCorpName());
                company.setDirectorName(companyDto.getDirectorName());
                Address address = optionalAddress.get();
                company.setAddress(address);
                return new Response("Company successfully edited", true);
            }
            return new Response("Company's address should be exist", false);
        }
        return new Response("There is no such company", false);
    }

    public Response deleteCompany(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            companyRepository.deleteById(id);
            return new Response("company successfully deleted", false);
        }
        return new Response("There is no such company", false);
    }
}

