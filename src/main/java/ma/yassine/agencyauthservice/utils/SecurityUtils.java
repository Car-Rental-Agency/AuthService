package ma.yassine.agencyauthservice.utils;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.yassine.agencyauthservice.entities.Customer;
import ma.yassine.agencyauthservice.exceptions.UnauthorizedException;
import ma.yassine.agencyauthservice.repositories.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityUtils {
    private final CustomerRepository customerRepository;

    public Customer getAuthenticatedCustomer() throws EntityNotFoundException, UnauthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UnauthorizedException("No authentication found");
        }

        String email = null;
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2User) {
            email = ((OAuth2User) principal).getAttribute("email");
        } else if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            email = (String) principal;
        }

        if (email == null) {
            throw new UnauthorizedException("Unable to extract email from authentication");
        }

        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new EntityNotFoundException("No customer found with email: " + email);
    }
}
