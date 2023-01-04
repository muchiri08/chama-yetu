package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.repository.MemberRepository;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<MemberDto> getAllMembers(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<Member> members = memberRepository.findAll(pageable);
        int totalPages = members.getTotalPages();

        if (pageable.getPageSize() < 0 || pageable.getPageNumber() > totalPages) {
            throw new PageNotFoundException("Invalid Page Size or Page Number");
        }

        if (members.getNumberOfElements() == 0) {
            throw new NoDataFoundException("No Data Found");
        }

        Page<MemberDto> memberDtos = members.map(member -> modelMapper.map(member, MemberDto.class));
        return memberDtos;
    }

    @Transactional
    @Override
    public MemberDto updateMember(Long id, MemberDto memberDto) throws NoDataFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Member with ID " + id + " not found")
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
    public String deleteMember(Long id) throws NoDataFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Member with ID " + id + " not found")
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
    public MemberDto findMemberById(Long id) throws NoDataFoundException {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Member with ID " + id + " not found")
        );
        MemberDto responseDto = modelMapper.map(member, MemberDto.class);

        return responseDto;
    }

    @Override
    public Boolean checkMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null){
            return false;
        } else {
            return true;
        }
    }
}
