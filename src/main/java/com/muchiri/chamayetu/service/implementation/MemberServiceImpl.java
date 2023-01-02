package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.repository.MemberRepository;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.muchiri.chamayetu.exception.PageNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final MemberRepository memberRepository;

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
}
