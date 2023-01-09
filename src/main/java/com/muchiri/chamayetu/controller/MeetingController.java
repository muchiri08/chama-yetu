package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping("/create")
    public ResponseEntity<MeetingDto> createMeeting(@RequestBody @Valid MeetingDto meetingDto) throws MemberNotFoundException {
        MeetingDto responseDto = meetingService.createMeeting(meetingDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MeetingDto>> getAllMeetings(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<MeetingDto> responseDto = meetingService.getAllMeetings(pageable);

        return ResponseEntity.ok(responseDto);
    }
}
