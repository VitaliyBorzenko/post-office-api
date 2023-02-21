package kz.dar.academy.backend.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PostResponce {
    private String postId;
    private ClientResponce client;
    private ClientResponce reseiver;
    private String postItem;
    private String status;

}
