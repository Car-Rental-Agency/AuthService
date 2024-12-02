package ma.yassine.agencyauthservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.yassine.agencyauthservice.dtos.DataCustomerRequest;
import ma.yassine.agencyauthservice.entities.Customer;
import ma.yassine.agencyauthservice.exeptions.UnauthorizedException;
import ma.yassine.agencyauthservice.services.interfaces.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()") @RequestMapping("/api/auth/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCurrentCustomer(@Valid @RequestBody DataCustomerRequest request) throws UnauthorizedException {
        Customer updatedCustomer = customerService.updateAuthenticatedCustomerDetails(request);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
