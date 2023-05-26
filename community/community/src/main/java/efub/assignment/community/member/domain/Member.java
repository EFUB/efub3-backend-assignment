package efub.assignment.community.member.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static efub.assignment.community.member.domain.MemberStatus.REGISTERED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
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

    @Column(nullable = false, length = 30)
    private String university;

    @Column(nullable = false, length = 16)
    private String studentId;

    private String bio;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Builder
    public Member(String email, String password, String nickname, String university, String studentId, String bio) {
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
        this.bio = bio;
        this.status = REGISTERED;
    }

    public void updateMember(String bio, String nickname) {
        this.bio = bio;
        this.nickname = nickname;
    }

    public void withdrawMember() {
        this.status = MemberStatus.UNREGISTERED;
    }
}
