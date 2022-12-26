package codingin.domain.dto;
import codingin.domain.entity.ReplyEntity;
import codingin.domain.entity.ReplyRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class ReplyDto {
    private int rno;
    private String rcomment;



    public ReplyEntity toEntity(){
        return ReplyEntity
                .builder()
                .rno( this.rno )
                .rcomment( this.rcomment )
                .build();
    }
}
