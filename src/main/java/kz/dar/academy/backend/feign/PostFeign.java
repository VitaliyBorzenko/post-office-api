package kz.dar.academy.backend.feign;
import kz.dar.academy.backend.model.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient("post-core-api")
public interface PostFeign {

    @GetMapping("/post/check")
    String checkPost();

    @GetMapping("/post/all")
    List<PostDTO> getAllPosts();

    @GetMapping("/post/{postId}")
    PostDTO getPostById(@PathVariable String postId);
}
