package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.Account;


@Repository("accountRepository")
public interface AccountRepository  extends  JpaRepository<Account,Integer>{

}
