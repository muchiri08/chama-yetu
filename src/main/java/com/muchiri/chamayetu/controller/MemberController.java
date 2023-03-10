package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MemberDto>> getAllMembers(Pageable pageable) throws PageNotFoundException, MemberNotFoundException {
        Page<MemberDto> members = memberService.getAllMembers(pageable);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findMemberById(@PathVariable("id") Long id) throws MemberNotFoundException {
        MemberDto memberDto = memberService.findMemberById(id);

        return ResponseEntity.ok(memberDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberDto memberDto) throws MemberNotFoundException {
        MemberDto responseDto = memberService.updateMember(id, memberDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) throws MemberNotFoundException {
        String responseString = memberService.deleteMember(id);

        return ResponseEntity.ok(responseString);
    }
}
