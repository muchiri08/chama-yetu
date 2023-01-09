package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.exception.MemberNotFoundException;

public interface MeetingService {
    MeetingDto createMeeting(MeetingDto meetingDto) throws MemberNotFoundException;
}
