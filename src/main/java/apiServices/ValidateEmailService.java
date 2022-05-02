package apiServices;

import io.restassured.response.Response;
import pojo.Comments;
import pojo.Posts;
import pojo.RestInvoke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static apiServices.BaseClass.baseUrl;
import static apiServices.BaseClass.response;

public class ValidateEmailService {
    String userId;
    List<Posts> Posts;
    List<Comments> Comments;
    List<String> emails = new ArrayList<>();

    public RestInvoke prepareUserDetailsRequestObj(String username){
        RestInvoke restInvoke;
        String endPoint = "/users?username="+username;
        restInvoke = new RestInvoke();
        restInvoke.setUrlInvoke(baseUrl+endPoint);
        restInvoke.setType("GET");
        return restInvoke;
    }

    public Response getUserDetailsByUsername(String username){
        RestCaller restCaller = new RestCaller();
        response = restCaller.apiRequestSender(prepareUserDetailsRequestObj(username));
       // System.out.println(response.prettyPrint());
        return response;
    }

    public String getUserId(){
        userId = response.jsonPath().get("[0].id").toString();
        return  userId;
    }

    public RestInvoke prepareUserPostDetailsRequestObj(String userId){
        RestInvoke restInvoke;
        String endPoint = "/posts?userId="+userId;
        restInvoke = new RestInvoke();
        restInvoke.setUrlInvoke(baseUrl+endPoint);
        restInvoke.setType("GET");
        return restInvoke;
    }

    public Response getPostsByUser(){
        RestCaller restCaller = new RestCaller();
        response = restCaller.apiRequestSender(prepareUserPostDetailsRequestObj(getUserId()));
       // System.out.println(response.prettyPrint());
        return response;
    }

    public void extractPostIds(){
        Posts = Arrays.asList(response.getBody().as(Posts[].class));

    }

    public RestInvoke prepareUserPostCommentDetailsRequestObj(String postId){
        RestInvoke restInvoke;
        String endPoint = "/comments?postId="+postId;
        restInvoke = new RestInvoke();
        restInvoke.setUrlInvoke(baseUrl+endPoint);
        restInvoke.setType("GET");
        return restInvoke;
    }

    public Response getPostsCommentsByUser(String postId){
        RestCaller restCaller = new RestCaller();
        response = restCaller.apiRequestSender(prepareUserPostCommentDetailsRequestObj(postId));
        // System.out.println(response.prettyPrint());
        return response;
    }

    public void getPostCommentEmailDetails(){
        for (Posts post : Posts) {
            getPostsCommentsByUser(post.getId());
            Comments = Arrays.asList(response.getBody().as(Comments[].class));
            for (Comments comment : Comments) {
                //System.out.println(comment.getEmail());
                emails.add(comment.getEmail());
            }

        }
    }

    public List<String> getEmails(){
        return  emails;
    }

    public boolean validateEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return (matcher.matches());
    }

}
