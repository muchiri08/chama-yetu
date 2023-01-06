package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.DecisionDto;
import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Decision;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.repository.DecisionRepository;
import com.muchiri.chamayetu.service.interfaces.DecisionService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DecisionServiceImpl implements DecisionService {

    private final DecisionRepository decisionRepository;
    private final MemberService memberService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public DecisionDto createDecision(DecisionDto decisionDto) throws MemberNotFoundException {
        Decision decision = new Decision();
        decision.setDescription(decisionDto.getDescription());
        decision.setStatus(decisionDto.getStatus());
        decision.setDateTime(decisionDto.getDateTime());

        Set<Long> memberIds = decisionDto.getMemberIds();
        if (memberIds.isEmpty()) throw new MemberNotFoundException("No member ids' found");

        Set<Member> members = memberIds.stream().map(memberId -> {
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

        decision.setMembers(members);
        decisionRepository.save(decision);

        return decisionToDecisionDto(decision);
    }

    @Override
    public Page<DecisionDto> getAllDecisions(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Page<Decision> decisions = decisionRepository.findAll(pageable);
        int totalPages = decisions.getTotalPages();

        if (pageable.getPageSize() < 0 || pageable.getPageNumber() > totalPages){
            throw new PageNotFoundException("Invalid Page Size or Page Number");
        }
        if (decisions.getNumberOfElements() == 0){
            throw new NoDataFoundException("No Data Found!");
        }

        return decisions.map(this::decisionToDecisionDto);
    }

    private DecisionDto decisionToDecisionDto(Decision decision) {
        DecisionDto decisionDto = new DecisionDto();
        decisionDto.setId(decision.getId());
        decisionDto.setDescription(decision.getDescription());
        decisionDto.setStatus(decision.getStatus());
        decisionDto.setDateTime(decision.getDateTime());
        decisionDto.setMemberIds(decision.getMembers().stream().map(Member::getId).collect(Collectors.toSet()));

        return decisionDto;
    }
}
