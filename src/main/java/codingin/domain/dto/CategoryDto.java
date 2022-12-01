package codingin.domain.dto;
import codingin.domain.entity.CategoryEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class CategoryDto {
    private int cno;

    public CategoryEntity toEntity(){
        return CategoryEntity
                .builder()
                .cno( this.cno )
                .build();
    }
}
