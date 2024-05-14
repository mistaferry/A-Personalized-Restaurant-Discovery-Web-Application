package ua.huryn.elasticsearch.entity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "review")
public class ReviewModel {
    @Id
    @Field(type = FieldType.Long, name = "review_id")
    private Long reviewId;
    @Field(type = FieldType.Keyword, name = "text")
    private String text;
}
