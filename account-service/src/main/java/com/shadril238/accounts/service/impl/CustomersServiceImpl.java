package com.shadril238.accounts.service.impl;

import com.shadril238.accounts.dto.AccountsDto;
import com.shadril238.accounts.dto.CardsDto;
import com.shadril238.accounts.dto.CustomerDetailsDto;
import com.shadril238.accounts.dto.LoansDto;
import com.shadril238.accounts.entity.Accounts;
import com.shadril238.accounts.entity.Customer;
import com.shadril238.accounts.exception.ResourceNotFoundException;
import com.shadril238.accounts.mapper.AccountsMapper;
import com.shadril238.accounts.mapper.CustomerMapper;
import com.shadril238.accounts.repository.AccountsRepository;
import com.shadril238.accounts.repository.CustomerRepository;
import com.shadril238.accounts.service.ICustomersService;
import com.shadril238.accounts.service.client.CardsFeignClient;
import com.shadril238.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if(null != loansDtoResponseEntity){
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
