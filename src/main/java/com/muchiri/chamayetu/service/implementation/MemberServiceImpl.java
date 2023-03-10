package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.repository.MemberRepository;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        memberRepository.save(member);
        MemberDto responseDto = modelMapper.map(member, MemberDto.class);
        return responseDto;
    }

    @Override
    public Page<MemberDto> getAllMembers(Pageable pageable) throws PageNotFoundException, MemberNotFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Member> members = memberRepository.findAll(sortedPageable);
        int totalPages = members.getTotalPages();

        if (pageable.getPageSize() < 0 || pageable.getPageNumber() > totalPages) {
            throw new PageNotFoundException("Invalid Page Size or Page Number");
        }

        if (members.getNumberOfElements() == 0) {
            throw new MemberNotFoundException("No Members Found");
        }

        Page<MemberDto> memberDtos = members.map(member -> modelMapper.map(member, MemberDto.class));
        return memberDtos;
    }

    @Transactional
    @Override
    public MemberDto updateMember(Long id, MemberDto memberDto) throws MemberNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Member with ID " + id + " not found")
        );
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getLastName());
        member.setDateOfBirth(memberDto.getDateOfBirth());
        member.setEmailAddress(memberDto.getEmailAddress());
        member.setGender(memberDto.getGender());
        member.setPhoneNumber(memberDto.getPhoneNumber());
        member.setLocation(memberDto.getLocation());
        member.setOccupation(memberDto.getOccupation());

        Member updatedMember = memberRepository.save(member);
        MemberDto responseDto = modelMapper.map(updatedMember, MemberDto.class);

        return responseDto;
    }

    @Transactional
    @Override
    public String deleteMember(Long id) throws MemberNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Member with ID " + id + " not found")
        );
        memberRepository.delete(member);

        Member deletedMember = memberRepository.findById(id).orElse(null);
        if (deletedMember == null) {
            log.info("Member deleted successfully");
            return "Member deleted successfully";
        } else {
            log.error("Member with id " + id + " not deleted");
            return "Member with id " + id + " not deleted";
        }
    }

    @Override
    public MemberDto findMemberById(Long id) throws MemberNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Member with ID " + id + " not found")
        );
        MemberDto responseDto = modelMapper.map(member, MemberDto.class);

        return responseDto;
    }

    @Override
    public Boolean checkMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Set<Member> getMembersByIds(Set<Long> ids) {
        Set<Member> members = ids.stream().map(memberId -> {
            Member member = null;
            try {
                member = getMemberById(memberId);
            } catch (MemberNotFoundException e) {
                log.error("Member with ID " + memberId + " not found!");
                throw new RuntimeException(e);
            }
            return member;
        }).collect(Collectors.toSet());

        return members;
    }

    @Override
    public Member getMemberById(Long id) throws MemberNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Member with ID " + id + " not found")
        );
        return member;
    }
}
