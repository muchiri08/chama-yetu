package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    Page<MemberDto> getAllMembers(Pageable pageable) throws PageNotFoundException, MemberNotFoundException;
    MemberDto updateMember(Long id, MemberDto memberDto) throws MemberNotFoundException;
    String deleteMember(Long id) throws MemberNotFoundException;
    MemberDto findMemberById(Long id) throws MemberNotFoundException;

    Boolean checkMemberById(Long id);
    Set<Member> getMembersByIds(Set<Long> ids);
    Member getMemberById(Long id) throws MemberNotFoundException;

}
