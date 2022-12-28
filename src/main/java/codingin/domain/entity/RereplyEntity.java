package codingin.domain.entity;

import codingin.domain.BaseEntity;
import codingin.domain.dto.RereplyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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


    public RereplyDto toDto(){
        return RereplyDto
                .builder()
                .reno( this.reno )
                .rercomment(this.rercomment)
                //rerecommt작성하기
                .bdate(
                    this.getCdate().toLocalDate().toString().equals(LocalDateTime.now().toString())
                            ?
                            this.getCdate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            :
                            this.getCdate().toLocalDate().toString()
                )
                .build();
    }
}
