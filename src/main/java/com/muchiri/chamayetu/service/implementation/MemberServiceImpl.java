package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.repository.MemberRepository;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member =modelMapper.map(memberDto, Member.class);
        memberRepository.save(member);
        MemberDto responseDto = modelMapper.map(member, MemberDto.class);
        return responseDto;
    }
}
