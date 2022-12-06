package codingin.domain.dto;


import codingin.domain.entity.UpdownEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class UpdownDto {

    // 12-06 주혁 좋아요 싫어요 Dto  생성
    private  int uno; // pk

    private  int likeno; //좋아요

    private  int dno; // 싫어요

    private  int upview; // 좋아요 싫어요 눌렀을때 증가


    public UpdownEntity toEntity(){
        return  UpdownEntity
                .builder()
                .uno(this.uno)
                .likeno(this.likeno)
                .dno(this.dno)
                .upview(this.upview)
                .build();
    }
}
