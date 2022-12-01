package codingin.domain.dto;
import codingin.domain.entity.RereplyEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class RereplyDto {
    private int reno;

    public RereplyEntity toEntity(){
        return RereplyEntity
                .builder()
                .reno( this.reno )
                .build();
    }
}
