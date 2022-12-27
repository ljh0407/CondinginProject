package codingin.domain.entity;

import codingin.domain.BaseEntity;
import codingin.domain.dto.RereplyDto;
import lombok.*;

import javax.persistence.*;

@Entity // 엔티티 정의
@Table(name = "rereply") // 테이블명 정의
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class RereplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reno;

    private String rercomment; // 대댓글 내용 최예은 추가

    
    
    @ManyToOne
    @JoinColumn(name = "rno")
    @ToString.Exclude
    private ReplyEntity replyEntity;

    @ManyToOne
    @JoinColumn(name = "mno")
    @ToString.Exclude
    private  MemberEntity memberEntity;




    public RereplyDto toEntity(){
        return RereplyDto
                .builder()
                .reno( this.reno )
                .build();
    }
}
