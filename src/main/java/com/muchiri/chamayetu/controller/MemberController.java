package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<MemberDto> createMember(@RequestBody @Valid MemberDto memberDto) {
        MemberDto responseDto = memberService.createMember(memberDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<MemberDto>> getAllMembers(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<MemberDto> members = memberService.getAllMembers(pageable);
        return ResponseEntity.ok(members);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberDto memberDto) throws NoDataFoundException {
        MemberDto responseDto = memberService.updateMember(id, memberDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable("id") Long id) throws NoDataFoundException{
        return memberService.deleteMember(id);
    }
}
