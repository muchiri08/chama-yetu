package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.DecisionDto;
import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Decision;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.repository.DecisionRepository;
import com.muchiri.chamayetu.service.interfaces.DecisionService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public DecisionDto createDecision(DecisionDto decisionDto) throws NoDataFoundException {
        Decision decision = new Decision();
        decision.setDescription(decisionDto.getDescription());
        decision.setStatus(decisionDto.getStatus());
        decision.setDateTime(decisionDto.getDateTime());

        Set<Long> memberIds = decisionDto.getMemberIds();
        if (memberIds.isEmpty()) throw new NoDataFoundException("No member ids' found");

        Set<Member> members = memberIds.stream().map(memberId -> {
            MemberDto memberDto = null;
            try {
                memberDto = memberService.findMemberById(memberId);
            } catch (NoDataFoundException e) {
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
