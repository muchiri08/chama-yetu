package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MeetingService {
    MeetingDto createMeeting(MeetingDto meetingDto) throws MemberNotFoundException;
    Page<MeetingDto> getAllMeetings(Pageable pageable) throws PageNotFoundException, NoDataFoundException;
    MeetingDto findMeetingById(Long id) throws NoDataFoundException;
    MeetingDto updateMeeting(Long id, MeetingDto meetingDto) throws NoDataFoundException, MemberNotFoundException;
    String deleteMeeting(Long id) throws MemberNotFoundException;
    MeetingDto findMeetingByDate(LocalDate date) throws NoDataFoundException;
}
