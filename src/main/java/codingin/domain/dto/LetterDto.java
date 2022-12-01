package codingin.domain.dto;
import codingin.domain.entity.LetterEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class LetterDto {
    private int lno;
    private String lfrom; //보내는사람
    private String  lto;    //받는 사람
    private String  ltitle; //제목
    private String  lcontent;   //내용

    public LetterEntity toEntity(){
        return LetterEntity
                .builder()
                .lno( this.lno )
                .ltitle( this.ltitle)
                .lcontent( this.lcontent)
                .build();
    }
}
