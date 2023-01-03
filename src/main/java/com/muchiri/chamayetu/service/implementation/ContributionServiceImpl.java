package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Contribution;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.repository.ContributionRepository;
import com.muchiri.chamayetu.service.interfaces.ContributionService;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContributionServiceImpl implements ContributionService {
    private final MemberService memberService;
    private final ContributionRepository contributionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public ContributionDto createContribution(ContributionDto contributionDto) throws NoDataFoundException {
        Long memberId = contributionDto.getMemberId();
        MemberDto memberDto = memberService.findMemberById(memberId);

        Member member = modelMapper.map(memberDto, Member.class);
        Contribution contribution = new Contribution();
        contribution.setMember(member);
        contribution.setAmount(contributionDto.getAmount());
        contribution.setDateTime(contributionDto.getDateTime());

        contributionRepository.save(contribution);

        ContributionDto responseDto = new ContributionDto();
        responseDto.setId(contribution.getId());
        responseDto.setMemberId(member.getId());
        responseDto.setAmount(contribution.getAmount());
        responseDto.setDateTime(contribution.getDateTime());

        return responseDto;
    }
}
