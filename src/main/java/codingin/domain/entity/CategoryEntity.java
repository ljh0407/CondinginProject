package codingin.domain.entity;

import codingin.domain.BaseEntity;
import codingin.domain.dto.CategoryDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 엔티티 정의
@Table(name = "category") // 테이블명 정의
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class CategoryEntity{    //카테고리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;    //카테고리 번호

    @Column(nullable = false)
    private String cname;   //카테고리 이름

    @OneToMany( mappedBy = "categoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList  = new ArrayList<>();

    public CategoryDto toDto(){
        return CategoryDto
                .builder()
                .cno( this.cno )
                .cname(this.cname)
                .build();
    }
}
