package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Meeting;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.repository.MeetingRepository;
import com.muchiri.chamayetu.service.interfaces.MeetingService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MemberService memberService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public MeetingDto createMeeting(MeetingDto meetingDto) throws MemberNotFoundException {
        Meeting meeting = new Meeting();
        meeting.setDate(meetingDto.getDate());
        meeting.setTime(meetingDto.getTime());
        meeting.setLocation(meetingDto.getLocation());
        meeting.setNotes(meetingDto.getNotes());

        Set<Long> memberIds = meetingDto.getMemberIds();
        if (memberIds.isEmpty()) throw new MemberNotFoundException("No member ids found!");

        Set<Member> members = getMembersByIds(memberIds);
        meeting.setMembers(members);

        meetingRepository.save(meeting);

        return mapMeetingToMeetingDto(meeting);
    }

    private Set<Member> getMembersByIds(Set<Long> ids) {
        Set<Member> members = ids.stream().map(memberId -> {
            MemberDto memberDto = null;
            try {
                memberDto = memberService.findMemberById(memberId);
            } catch (MemberNotFoundException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
            Member member = modelMapper.map(memberDto, Member.class);
            return member;
        }).collect(Collectors.toSet());

        return members;
    }

    private MeetingDto mapMeetingToMeetingDto(Meeting meeting) {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setId(meeting.getId());
        meetingDto.setDate(meeting.getDate());
        meetingDto.setTime(meeting.getTime());
        meetingDto.setLocation(meeting.getLocation());
        meetingDto.setNotes(meeting.getNotes());
        meetingDto.setMemberIds(meeting.getMembers().stream().map(Member::getId).collect(Collectors.toSet()));

        return meetingDto;
    }
}
