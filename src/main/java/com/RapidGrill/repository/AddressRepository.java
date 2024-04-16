package com.RapidGrill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RapidGrill.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
