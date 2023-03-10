package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.DecisionDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DecisionServiceImpl implements DecisionService {

    private final DecisionRepository decisionRepository;
    private final MemberService memberService;

    @Transactional
    @Override
    public DecisionDto createDecision(DecisionDto decisionDto) throws MemberNotFoundException {
        Decision decision = new Decision();
        decision.setDescription(decisionDto.getDescription());
        decision.setStatus(decisionDto.getStatus());
        decision.setDateTime(decisionDto.getDateTime());

        Set<Long> memberIds = decisionDto.getMemberIds();
        if (memberIds.isEmpty()) throw new MemberNotFoundException("No member ids' found");

        Set<Member> members = memberService.getMembersByIds(memberIds);

        decision.setMembers(members);
        decisionRepository.save(decision);

        return decisionToDecisionDto(decision);
    }

    @Override
    public Page<DecisionDto> getAllDecisions(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Decision> decisions = decisionRepository.findAll(sortedPageable);

        if (decisions.isEmpty()){
            throw new NoDataFoundException("No decisions found!");
        }
        if (sortedPageable.getPageNumber() > decisions.getTotalPages()){
            throw new PageNotFoundException("Invalid pae number!");
        }
        if (sortedPageable.getPageSize() < 1){
            throw new PageNotFoundException("Invalid page size");
        }

        return decisions.map(this::decisionToDecisionDto);
    }

    @Override
    public DecisionDto findDecisionById(Long id) throws NoDataFoundException {
        Decision decision = decisionRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Decision with ID " + id + " not found!")
        );

        return decisionToDecisionDto(decision);
    }

    @Override
    public DecisionDto updateDecision(Long id, DecisionDto decisionDto) throws NoDataFoundException, MemberNotFoundException {
        Decision decision = decisionRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Decision with ID " + id + " is not found!")
        );
        decision.setDescription(decisionDto.getDescription());
        decision.setStatus(decisionDto.getStatus());
        decision.setDateTime(decisionDto.getDateTime());

        Set<Long> membersIds = decisionDto.getMemberIds();
        if (membersIds.isEmpty()) throw new MemberNotFoundException("No member Ids' found!");

        Set<Member> members = memberService.getMembersByIds(membersIds);

        decision.setMembers(members);
        return decisionToDecisionDto(decision);
    }

    @Override
    public String deleteDecision(Long id) throws NoDataFoundException {
        Decision decision = decisionRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Decision with ID " + id + " is not found!")
        );
        decisionRepository.delete(decision);

        Decision deletedDecision = decisionRepository.findById(id).orElse(null);

        return deletedDecision == null ? "Deleted Successfully" : "Decision with ID " + id + " not deleted!";
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
