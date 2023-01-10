package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.MeetingDto;
import com.muchiri.chamayetu.entity.Meeting;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.repository.MeetingRepository;
import com.muchiri.chamayetu.service.interfaces.MeetingService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MemberService memberService;

    @Override
    public MeetingDto createMeeting(MeetingDto meetingDto) throws MemberNotFoundException {
        Meeting meeting = new Meeting();
        meeting.setDate(meetingDto.getDate());
        meeting.setTime(meetingDto.getTime());
        meeting.setLocation(meetingDto.getLocation());
        meeting.setNotes(meetingDto.getNotes());

        Set<Long> memberIds = meetingDto.getMemberIds();
        if (memberIds.isEmpty()) throw new MemberNotFoundException("No member ids found!");

        Set<Member> members = memberService.getMembersByIds(memberIds);
        meeting.setMembers(members);

        meetingRepository.save(meeting);

        return mapMeetingToMeetingDto(meeting);
    }

    @Override
    public Page<MeetingDto> getAllMeetings(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Meeting> meetings = meetingRepository.findAll(sortedPageable);
        int totalPages = meetings.getTotalPages();

        if (pageable.getPageSize() < 0 || pageable.getPageNumber() > totalPages) {
            throw new PageNotFoundException("Invalid Page Size or Page Number");
        }
        if (meetings.getNumberOfElements() == 0) {
            throw new NoDataFoundException("No Data Found!");
        }

        return meetings.map(this::mapMeetingToMeetingDto);
    }

    @Override
    public MeetingDto findMeetingById(Long id) throws NoDataFoundException {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Meeting with ID " + id + " not found!")
        );

        return mapMeetingToMeetingDto(meeting);
    }

    @Override
    public MeetingDto updateMeeting(Long id, MeetingDto meetingDto) throws NoDataFoundException, MemberNotFoundException {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Meeting with ID " + id + " is not found!")
        );

        meeting.setDate(meetingDto.getDate());
        meeting.setTime(meetingDto.getTime());
        meeting.setLocation(meetingDto.getLocation());
        meeting.setNotes(meetingDto.getNotes());

        Set<Long> memberIds = meetingDto.getMemberIds();
        if (memberIds.isEmpty()) throw new MemberNotFoundException("No member ids found!");

        Set<Member> members = memberService.getMembersByIds(memberIds);
        meeting.setMembers(members);

        meetingRepository.save(meeting);

        return mapMeetingToMeetingDto(meeting);
    }

    @Override
    public String deleteMeeting(Long id) throws MemberNotFoundException {
        Meeting meeting = meetingRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Member with ID " + id + " not found!")
        );
        meetingRepository.delete(meeting);

        Meeting deletedMeeting = meetingRepository.findById(id).orElse(null);

        return deletedMeeting == null ? "Deleted Successfully" : "Meeting with ID " + id + " not deleted!";
    }

    @Override
    public MeetingDto findMeetingByDate(LocalDate date) throws NoDataFoundException {
        Meeting meeting = meetingRepository.findMeetingByDate(date).orElseThrow(
                () -> new NoDataFoundException("There was no meeting held on date " + date)
        );
        return mapMeetingToMeetingDto(meeting);
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
