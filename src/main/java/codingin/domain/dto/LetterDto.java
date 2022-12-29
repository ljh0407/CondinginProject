package codingin.domain.dto;
import codingin.domain.entity.LetterEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class LetterDto {    //쪽지
    private int lno;    //쪽지 번호
    private String lfrom; //보내는 사람
    private String lto;    //받는 사람
    private String lcontent;   //내용
    private String ldata; // 작성날짜
    private Long totalletter;; // 전체쪽지수
    public LetterEntity toEntity(){
        return LetterEntity
                .builder()
                .lno( this.lno )
                .lcontent( this.lcontent)
                .build();
    }
}
