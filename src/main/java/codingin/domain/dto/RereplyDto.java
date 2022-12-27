package codingin.domain.dto;
import codingin.domain.entity.RereplyEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class RereplyDto {
    private int reno; // 대댓글번호
    private int rno; // 댓글번호 추가
    private String rercomment; // 대댓글 입력  //입,출

    private String memail; // 출력할 때 필요함
    private String bfilename;   //output
    private String bdate; //작성시간

    public RereplyEntity toEntity(){
        return RereplyEntity
                .builder()
                .rercomment( this.rercomment ) //pk 넣지 말라고 하셨으니까..
                .build();
    }
}
