package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
