package codingin.domain.dto;
import codingin.domain.entity.ReplyEntity;
import codingin.domain.entity.ReplyRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class ReplyDto {


    private int rno; // 출
    private int bno; //bno 추가함 // 입
    private String rcomment; // 입 , 출
    private String memail; // output 작성자
    private String bfilename;   //output // 프로필
    private String bdate; //output // 작성시간

    private List<RereplyDto> rereplyDtos; // output // 대댓글

    //ttest
    public ReplyEntity toEntity(){
        return ReplyEntity
                .builder()
                .rcomment( this.rcomment )//pk는 집어넣지 말라고 하십니다.
                .build();
    }
}
