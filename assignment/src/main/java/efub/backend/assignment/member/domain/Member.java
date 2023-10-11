package efub.backend.assignment.member.domain;

import efub.backend.assignment.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity { // Member 클래스의 생성시간과 변경시간 기록을 위해 baseTimeEntity 클래스 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;


    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false, updatable = false, length = 16)
    private String nickname;

    @Column(nullable = false)
    private String university;

    @Column(nullable = false)
    private String studentId;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Builder
    public Member(String email, String password, String nickname, String university, String studentId){
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
        this.status = MemberStatus.REGISTERED;
    }

    public void updateMember(String nickname){
        this.nickname = nickname;
    }

    public void withdrawMember(){
        this.status = MemberStatus.UNREGISTERED;
    }
}
