package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.service.interfaces.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping("/create")
    public ResponseEntity<MeetingDto> createMeeting(@RequestBody @Valid MeetingDto meetingDto) throws MemberNotFoundException {
        MeetingDto responseDto = meetingService.createMeeting(meetingDto);
        return ResponseEntity.ok(responseDto);
    }
}
