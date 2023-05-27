package com.example.demo.member.domain;

import com.example.demo.global.entity.BaseTimeEntity;
import com.example.demo.messageroom.domain.Messageroom;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.member.domain.MemberStatus.REGISTERED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 15)
    private String studentId;

    @Column(nullable = false, length = 25)
    private String university;

    @Column(nullable = false, length = 25)
    private String nickname;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    // 내가 메시지를 보내서 시작된 쪽지방
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messageroom> messageRoomAsSenderList = new ArrayList<>();

    // 내가 메시지를 받아서 시작된 쪽지방
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messageroom> messageRoomAsReceiverList = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname, String university, String studentId) {
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
        this.status = REGISTERED;
    }

    // 닉네임 수정
    public void updateMember(String nickname) {
        this.nickname = nickname;
    }

    // 회원탈퇴 (논리적 삭제)
    public void withdrawMember() {
        this.status = MemberStatus.UNREGISTERED;
    }



}
