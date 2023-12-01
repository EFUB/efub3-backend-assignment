package com.efub.community.domain.member.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efub.community.domain.member.domain.Member;
import com.efub.community.domain.member.dto.request.MemberUpdateRequestDto;
import com.efub.community.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public Long update(Long memberId, MemberUpdateRequestDto requestDto) {
		if (isExistedNickname(requestDto.getNickname())) {
			throw new IllegalArgumentException("중복된 닉네임이 있습니다. " + requestDto.getNickname());
		}
		Member member = findById(memberId);
		member.updateMember(requestDto.getNickname());
		return member.getMemberId();
	}

	public void delete(Long memberId) {
		Member member = findById(memberId);
		memberRepository.delete(member);
	}

	public void withdraw(Long memberId) {
		Member member = findById(memberId);
		member.withdraw();
	}

	@Transactional(readOnly = true)
	public Member findByStudentNo(Integer studentNo) {
		return memberRepository.findByStudentNo(studentNo)
			.orElseThrow(() -> new EntityNotFoundException("해당 학번을 가진 Member 를 찾을 수 없습니다."));
	}

	@Transactional(readOnly = true)
	public Member findById(Long id) {
		return memberRepository.findByMemberId(id)
			.orElseThrow(() -> new EntityNotFoundException("해당 id 를 가진 member 를 찾을 수 없습니다. id =" + id));
	}

	@Transactional(readOnly = true)
	public boolean isExistedStudentNo(Integer studentNo) {
		return memberRepository.existsByStudentNo(studentNo);
	}

	@Transactional(readOnly = true)
	public boolean isExistedNickname(String nickname) {
		return memberRepository.existsByNickname(nickname);
	}
}
