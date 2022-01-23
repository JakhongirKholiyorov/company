package uz.pdp.springmodule2task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springmodule2task1.entity.Address;
import uz.pdp.springmodule2task1.payload.AddressDto;
import uz.pdp.springmodule2task1.payload.Response;
import uz.pdp.springmodule2task1.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("address")
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = addressService.getAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id){
        Address address = addressService.getAddress(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping("address")
    public ResponseEntity<Response> addAddress(@Valid @RequestBody AddressDto addressDto){
        Response response = addressService.addAddress(addressDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("address/{id}")
    public ResponseEntity<Response> editAddress(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto){
        Response response = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("address/{id}")
    public ResponseEntity<Response> deleteAddress(@PathVariable Integer id){
        Response response = addressService.deleteAddress(id);
        return ResponseEntity.status(response.isSuccess()? 202 : 409).body(response);
    }
}
