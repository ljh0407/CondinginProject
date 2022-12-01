package codingin.domain.dto;
import codingin.domain.entity.ReplyEntity;
import lombok.*;

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
