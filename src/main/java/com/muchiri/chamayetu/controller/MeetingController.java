package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.service.interfaces.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDto> findMeetingById(@PathVariable Long id) throws NoDataFoundException {
        MeetingDto responseDto = meetingService.findMeetingById(id);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingDto> updateMeeting(@PathVariable("id") Long id, @RequestBody @Valid MeetingDto meetingDto) throws NoDataFoundException, MemberNotFoundException {
        MeetingDto responseDto = meetingService.updateMeeting(id, meetingDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable("id") Long id) throws NoDataFoundException {
        String responseString = meetingService.deleteMeeting(id);

        return ResponseEntity.ok(responseString);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<MeetingDto> findMeetingByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws NoDataFoundException {
        MeetingDto responseDto = meetingService.findMeetingByDate(date);

        return ResponseEntity.ok(responseDto);
    }
}
