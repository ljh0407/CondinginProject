package codingin.domain.dto;
import codingin.domain.entity.ReplyEntity;
import codingin.domain.entity.ReplyRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class ReplyDto {

    //
    private int rno; // 출
    private int bno; //bno 추가함 // 입
    private String rcomment; // 입 , 출
    private String memail; // output
    private String bfilename;   //output
    private String bdate; //output

    //ttest
    public ReplyEntity toEntity(){
        return ReplyEntity
                .builder()
                .rcomment( this.rcomment )//pk는 집어넣지 말라고 하십니다.
                .build();
    }
}
