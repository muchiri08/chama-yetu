package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.ContributionDto;
import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Contribution;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.exception.MemberNotFoundException;
import com.muchiri.chamayetu.exception.NoDataFoundException;
import com.muchiri.chamayetu.exception.PageNotFoundException;
import com.muchiri.chamayetu.repository.ContributionRepository;
import com.muchiri.chamayetu.service.interfaces.ContributionService;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContributionServiceImpl implements ContributionService {
    private final MemberService memberService;
    private final ContributionRepository contributionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public ContributionDto createContribution(ContributionDto contributionDto) throws MemberNotFoundException {
        Long memberId = contributionDto.getMemberId();
        MemberDto memberDto = memberService.findMemberById(memberId);

        Member member = modelMapper.map(memberDto, Member.class);
        Contribution contribution = new Contribution();
        contribution.setMember(member);
        contribution.setAmount(contributionDto.getAmount());
        contribution.setDateTime(contributionDto.getDateTime());

        contributionRepository.save(contribution);

        return mapContributionToContributionDto(contribution);
    }

    @Override
    public Page<ContributionDto> getAllContributions(Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Contribution> contributions = contributionRepository.findAll(sortedPageable);

        return mapPageableContributionToDto(contributions, sortedPageable);
    }

    @Override
    public ContributionDto findContributionById(Long id) throws NoDataFoundException {
        Contribution contribution = contributionRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Contribution with ID " + id + " not found")
        );

        return mapContributionToContributionDto(contribution);
    }

    @Transactional
    @Override
    public ContributionDto updateContribution(Long id, ContributionDto contributionDto) throws MemberNotFoundException {
        Contribution contribution = contributionRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("Contribution with ID " + id + " not found")
        );

        MemberDto memberDto = memberService.findMemberById(contributionDto.getMemberId());
        Member member = modelMapper.map(memberDto, Member.class);

        contribution.setMember(member);
        contribution.setAmount(contributionDto.getAmount());
        contribution.setDateTime(contributionDto.getDateTime());

        return mapContributionToContributionDto(contribution);
    }

    @Transactional
    @Override
    public String deleteContribution(Long id) throws NoDataFoundException {
        Contribution contribution = contributionRepository.findById(id).orElseThrow(
                () -> new NoDataFoundException("Contribution with ID " + id + " not found")
        );

        contributionRepository.delete(contribution);
        Contribution deletedContribution = contributionRepository.findById(id).orElse(null);

        if (deletedContribution == null) {
            return "Contribution deleted successfully";
        } else {
            log.error("Contribution with ID " + id + " not deleted");
            return "Contribution with ID " + id + " not deleted";
        }
    }

    @Override
    public Page<ContributionDto> findContributionByDateTimeBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        LocalDateTime fromDateTime = fromDate.atStartOfDay();
        LocalDateTime toDateTime = toDate.atTime(23, 59, 59);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Contribution> contributions = contributionRepository.findContributionByDateTimeBetween(fromDateTime, toDateTime, sortedPageable);

        return mapPageableContributionToDto(contributions, sortedPageable);
    }

    @Override
    public Page<ContributionDto> findByMemberId(Long id, Pageable pageable) throws PageNotFoundException, NoDataFoundException {
        if (!memberService.checkMemberById(id)) throw new NoDataFoundException("Member with ID " + id + " not found");

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Contribution> memberContributions = contributionRepository.findByMemberId(id, sortedPageable);

        return mapPageableContributionToDto(memberContributions, sortedPageable);
    }

    @Override
    public BigDecimal getTotalContributionsBetweenDates(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        BigDecimal totalContributions = contributionRepository.getTotalContributionsBetweenDates(startDateTime, endDateTime);

        return totalContributions;
    }

    Page<ContributionDto> mapPageableContributionToDto(Page<Contribution> contributions, Pageable pageable) throws NoDataFoundException, PageNotFoundException {
        if (contributions.isEmpty()) {
            throw new NoDataFoundException("No contributions found!");
        }
        if (pageable.getPageNumber() > contributions.getTotalPages()) {
            throw new PageNotFoundException("Invalid page number");
        }
        if (pageable.getPageSize() < 1) {
            throw new PageNotFoundException("Invalid page size");
        }

        return contributions.map(this::mapContributionToContributionDto);
    }

    private ContributionDto mapContributionToContributionDto(Contribution contribution) {
        ContributionDto contributionDto = new ContributionDto();
        contributionDto.setId(contribution.getId());
        contributionDto.setMemberId(contribution.getMember().getId());
        contributionDto.setAmount(contribution.getAmount());
        contributionDto.setDateTime(contribution.getDateTime());
        return contributionDto;
    }

}
