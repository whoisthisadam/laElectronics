package com.kasperovich.laelectronics.repository;

import com.kasperovich.laelectronics.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByPostcode(String postcode);

}
