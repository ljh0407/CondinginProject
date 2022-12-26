package codingin.domain.dto;
import codingin.domain.entity.ReplyEntity;
import codingin.domain.entity.ReplyRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class ReplyDto {
    private int rno;
    private int bno; //bno 추가함
    private int mno; //mno 추가함
    private String rcomment;


    public ReplyEntity toEntity(){
        return ReplyEntity
                .builder()
                .rcomment( this.rcomment )//pk는 집어넣지 말라고 하십니다.
                .build();
    }
}
