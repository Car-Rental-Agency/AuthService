package ma.yassine.agencyauthservice.services.implementations;

import lombok.RequiredArgsConstructor;
import ma.yassine.agencyauthservice.dtos.DataCustomerRequest;
import ma.yassine.agencyauthservice.entities.Customer;
import ma.yassine.agencyauthservice.exceptions.UnauthorizedException;
import ma.yassine.agencyauthservice.repositories.CustomerRepository;
import ma.yassine.agencyauthservice.services.interfaces.CustomerService;
import ma.yassine.agencyauthservice.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final SecurityUtils securityUtils;

    @Override
    public Customer updateAuthenticatedCustomerDetails(DataCustomerRequest request) throws UnauthorizedException {
        Customer customer = securityUtils.getAuthenticatedCustomer();

        Optional.ofNullable(request.fullName())
                .filter(fullName -> !fullName.trim().isEmpty())
                .ifPresent(customer::setFullName);

        Optional.ofNullable(request.recoveryEmail())
                .filter(email -> !email.trim().isEmpty())
                .ifPresent(customer::setRecoveryEmail);

        Optional.ofNullable(request.phone())
                .filter(phone -> !phone.trim().isEmpty())
                .ifPresent(customer::setPhone);

        return customerRepository.save(customer);
    }
}