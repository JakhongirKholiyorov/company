package uz.pdp.springmodule2task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springmodule2task1.entity.Address;
import uz.pdp.springmodule2task1.payload.AddressDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Response addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new Response("address successfully added", true);
    }

    public Response editAddress(Integer id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            if (addressDto.getStreet() != null)
                address.setStreet(addressDto.getStreet());
            if (addressDto.getHomeNumber() != null)
                address.setHomeNumber(addressDto.getHomeNumber());
            addressRepository.save(address);
            return new Response("address successfully edited", true);
        }
        return new Response("There is no such an address", false);
    }

    public Response deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.deleteById(id);
            return new Response("address successfully deleted", true);
        }
        return new Response("There is no such an address", false);
    }
}
