package com.muchiri.chamayetu;

import com.muchiri.chamayetu.dto.MemberDto;
import com.muchiri.chamayetu.entity.Member;
import com.muchiri.chamayetu.enums.Gender;
import com.muchiri.chamayetu.repository.MemberRepository;
import com.muchiri.chamayetu.service.interfaces.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @MockBean
    private MemberRepository memberRepository;

    @Test
    public void creatMemberTest() {
        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName("Kennedy");
        memberDto.setLastName("Mbogo");
        memberDto.setPhoneNumber("+254788501613");
        memberDto.setEmailAddress("mbogokennedy08@gmail.com");
        memberDto.setDateOfBirth(LocalDate.of(1998, 8, 25));
        memberDto.setOccupation("Software Engineering");
        memberDto.setGender(Gender.MALE);
        memberDto.setLocation("Nairobi");

        Member member = new Member();
        member.setFirstName("Kennedy");
        member.setLastName("Mbogo");
        member.setPhoneNumber("+254788501613");
        member.setEmailAddress("mbogokennedy08@gmail.com");
        member.setDateOfBirth(LocalDate.of(1998, 8, 25));
        member.setOccupation("Software Engineering");
        member.setGender(Gender.MALE);
        member.setLocation("Nairobi");


        when(memberRepository.save(any(Member.class))).thenReturn(member);
        MemberDto result = memberService.createMember(memberDto);

        assertEquals(result.getFirstName(), "Kennedy", member.getFirstName());
        assertEquals(result.getLastName(), "Mbogo", member.getLastName());
        assertEquals(result.getPhoneNumber(), "+254788501613", member.getPhoneNumber());
        assertEquals(result.getEmailAddress(), "mbogokennedy08@gmail.com", member.getEmailAddress());
        assertEquals(result.getDateOfBirth().toString(), LocalDate.of(1998, 8, 25).toString(), member.getDateOfBirth().toString());
        assertEquals(result.getGender().toString(), Gender.MALE, member.getGender());
        assertEquals(result.getOccupation(), "Software Engineering", member.getOccupation());
        assertEquals(result.getLocation(), "Nairobi", member.getLocation());

    }

}
