package com.example.verifit.service;

import com.example.verifit.model.Member;

import java.util.List;

public interface IMemberService {
    List<Member> getAllMembers();

    Member getMemberById(Long membershipId);

    void addMember(Member member);

    void updateMember(Member member);

    void removeMemberById(Long membershipId);
}
