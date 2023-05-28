package com.example.verifit.service;

import com.example.verifit.dao.IMemberRepository;
import com.example.verifit.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberRepository memberRepository;

    public void generateDBEntries() {
        List<Member> members = new ArrayList<>(Arrays.asList(
                new Member(LocalDateTime.parse("2023-05-17T11:50:32.777"), 5, true),
                new Member(LocalDateTime.parse("2023-05-17T11:50:32.777"), 3, false),
                new Member(LocalDateTime.parse("2023-05-17T11:50:32.777"), 4, true),
                new Member(LocalDateTime.parse("2023-05-17T11:50:32.777"), 0, false),
                new Member(LocalDateTime.parse("2023-05-17T11:50:32.777"), 1, false)
        ));
        members.forEach(m -> memberRepository.save(m));
    }

    public void updateMembersAttendance(Long membershipId, LocalDateTime lastAttendanceDate) {
        if (memberRepository.existsById(membershipId)) {
            Member member = memberRepository.findById(membershipId).get();
            this.checkMembersDiscountEligibility(lastAttendanceDate, member);
        } else {
            throw new IllegalStateException();
        }
    }

    public String getMembersDiscountStatusById(Long membershipId) {
        if (memberRepository.existsById(membershipId)) {
            return "Members discount status: " + memberRepository.findById(membershipId).get().getDiscountStatus();
        }
        else {
            throw new IllegalStateException();
        }
    }

    public String getMembersStreakStatusById(Long membershipId) {
        if (memberRepository.existsById(membershipId)) {
            return "Members current streak: " + memberRepository.findById(membershipId).get().getCurrentStreak();
        } else {
            throw new IllegalStateException();
        }
    }

    private void checkMembersDiscountEligibility(LocalDateTime newDate, Member member) {
        if (newDate.isAfter(member.getLastAttendanceDate())) {

            LocalDateTime testDate = member.getLastAttendanceDate().plusDays(7);
            if (newDate.isAfter(testDate) || newDate.isEqual(testDate)) {
                member.setCurrentStreak(member.getCurrentStreak() + 1);
                if (member.getCurrentStreak() >= 4) {
                    member.setDiscountStatus(true);
                }
            }
            member.setLastAttendanceDate(newDate);
            memberRepository.save(member);
        } else {
            throw new IllegalStateException();
        }
    }


    /* Additional functionality for CRUD operations */
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        memberRepository.findAll()
                .forEach(members::add);

        return members;
    }

    public Member getMemberById(Long membershipId) {
        return memberRepository.findById(membershipId).get();
    }

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public void updateMember(Member member) {
        memberRepository.save(member);
    }

    public void removeMemberById(Long membershipId) {
        memberRepository.deleteById(membershipId);
    }
}
