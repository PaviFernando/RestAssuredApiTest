package pojo;

import lombok.Data;

@Data
public class Comments {
    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;
}
