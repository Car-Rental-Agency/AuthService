package ma.yassine.agencyauthservice.services.interfaces;

import ma.yassine.agencyauthservice.dtos.DataCustomerRequest;
import ma.yassine.agencyauthservice.entities.Customer;
import ma.yassine.agencyauthservice.exceptions.UnauthorizedException;

public interface CustomerService {
    Customer updateAuthenticatedCustomerDetails(DataCustomerRequest request) throws UnauthorizedException;
}
