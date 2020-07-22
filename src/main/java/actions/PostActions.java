package actions;

import constants.Endpoints;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Post;

import java.util.List;

import static io.restassured.RestAssured.given;


public class PostActions {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    public PostActions() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }


    public List<Post> getAllPosts() {
        return given(requestSpecification)
                .get("/posts")
                .then().spec(responseSpecification)
                .extract().body()
                .jsonPath().getList("", Post.class);
    }

    public Post getPostByQueryId(Integer id) {
        return given(requestSpecification)
                .pathParam("id", id)
                .get("/posts" + "/{id}")
                .then().spec(responseSpecification)
                .extract().body().as(Post.class);

    }

    public List<Post> getPostByParamId(Integer id) {
        return given(requestSpecification)
                .param("id", id)
                .get("/posts")
                .then().spec(responseSpecification)
                .extract().body().jsonPath().getList("", Post.class);

    }

}
