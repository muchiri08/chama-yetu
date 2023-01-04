package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    Page<MemberDto> getAllMembers(Pageable pageable) throws PageNotFoundException, NoDataFoundException;
    MemberDto updateMember(Long id, MemberDto memberDto) throws NoDataFoundException;
    String deleteMember(Long id) throws NoDataFoundException;
    MemberDto findMemberById(Long id) throws NoDataFoundException;

    Boolean checkMemberById(Long id);

}
