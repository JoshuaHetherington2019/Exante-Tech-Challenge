package com.example.verifit.controller;

import com.example.verifit.model.Member;
import com.example.verifit.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Validated
public class MemberController {
    @Autowired
    private MemberServiceImpl memberService;

    @PostMapping("/members/populate")
    @ResponseStatus(HttpStatus.CREATED)
    public void populateMembers() {
        memberService.generateDBEntries();
    }

    @PatchMapping("/members/{membershipId}/attendance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMembersAttendance(@PathVariable("membershipId") @Positive Long membershipId, @Valid @RequestBody Member member) {
        memberService.updateMembersAttendance(membershipId, member.getLastAttendanceDate());
    }

    @GetMapping("/members/{membershipId}/discount")
    @ResponseStatus(HttpStatus.OK)
    public String getMembersDiscountById(@PathVariable("membershipId") @Positive Long membershipId) {
        return memberService.getMembersDiscountStatusById(membershipId);
    }

    @GetMapping("/members/{membershipId}/streak")
    @ResponseStatus(HttpStatus.OK)
    public String getMembersStreakById(@PathVariable("membershipId") @Positive(
            message = "Membership ID must be greater than zero") Long membershipId) {
        return memberService.getMembersStreakStatusById(membershipId);
    }


    /**** Exception Handler
     * Can be moved out to a Global Exception Handler class with specific
     * methods for each error case, can log errors, provide custom messages, etc
     * ****/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


    /**** Additional endpoints for basic REST/CRUD operations ****/
    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

}
