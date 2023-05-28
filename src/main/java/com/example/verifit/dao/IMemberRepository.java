package com.example.verifit.dao;

import com.example.verifit.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends CrudRepository<Member, Long> {
}
