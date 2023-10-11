package efub.assignment.community.member.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
    // BaseTimeEntity를 상속받아 생성 시각과 수정 시각 저장

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 16)  // 닉네임을 변경하는 로직이 있으므로 updatable=false 삭제
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
        this.password = password;
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
