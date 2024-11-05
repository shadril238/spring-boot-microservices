package com.shadril238.accounts.service;

import com.shadril238.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    /**
     * Fetches the customer details based on the mobile number
     * @param mobileNumber - Input mobile number
     * @return CustomerDetailsDto based on the mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
