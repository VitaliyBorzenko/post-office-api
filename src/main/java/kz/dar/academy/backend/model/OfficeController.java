package kz.dar.academy.backend.model;

import kz.dar.academy.backend.feign.ClientFeign;
import kz.dar.academy.backend.feign.PostFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private PostDTO postDTO;

    @Autowired
    private ClientResponce clientDTO;

    @Autowired
    private PostFeign postFeign;

    @Autowired
    private ClientFeign clientFeign;

    @GetMapping("/check")
    public String checkPostOffice() {
        return "office-core-api is working";
    }

    @GetMapping("/post/check")
    public String checkPost() {
        return postFeign.checkPost();
    }

    @GetMapping("/post/all")
    public List<PostDTO> getAllPosts() {
        return postFeign.getAllPosts();
    }

    @GetMapping("/post/{postId}")
    public PostDTO getPostById(@PathVariable String postId) {
        return postFeign.getPostById(postId);
    }

    @GetMapping("/client/check")
    public String checkClients() {
        return clientFeign.checkClient();
    }

    @GetMapping("/client/all")
    public List<ClientResponce> getAllClients() {
        return clientFeign.getAllClients();
    }

    @GetMapping("/client/{clientId}")
    public ClientResponce getClientById(@PathVariable String clientId) {
        return clientFeign.getClientById(clientId);
    }

    @GetMapping("/client/info")
    public List<PostResponce> getAllClientsFullInfo() {


        List<PostResponce> result = new ArrayList<>();
        //1.Получить информацию о всех посылках
        List<PostDTO> postList = postFeign.getAllPosts();
        for (PostDTO post : postList) {
            //2.Получить информацию по ИД
            String clientId = post.getClientId();
            String postRecipientId = post.getPostRecipientId();
            ClientResponce client = clientFeign.getClientById(clientId);
            ClientResponce recipient = clientFeign.getClientById(postRecipientId);
            //3.Сопоставить информацию о клиенте и посылке
            PostResponce postResponce = new PostResponce();
            postResponce.setPostId(post.getPostId());
            postResponce.setClient(client);
            postResponce.setReseiver(recipient);
            postResponce.setPostItem(post.getPostItem());
            postResponce.setStatus(post.getStatus());

            result.add(postResponce);
        }
        //4.Вывести данную информацию
        return result;
    }
}