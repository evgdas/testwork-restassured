import actions.PostActions;
import models.Post;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class PostTests {
    private PostActions postActions;

    @DataProvider(name = "postId")
    public Object[][] postId() {
        Integer id[][] = {{1}, {2}, {3}};
        return id;
    }

    @BeforeClass
    public void beforeClass() {
        postActions = new PostActions();
    }

    @Test
    public void getAllPostsTest() {
        List<Post> posts = postActions.getAllPosts();

        assertThat(posts, everyItem(hasProperty("userId", is(notNullValue()))));
        assertThat(posts, everyItem(hasProperty("id", is(notNullValue()))));
        assertThat(posts, everyItem(hasProperty("title", is(notNullValue()))));
        assertThat(posts, everyItem(hasProperty("body", is(notNullValue()))));
    }

    @Test(dataProvider = "postId")
    public void getPostsByParamIdTest(Integer id) {
        List<Post> postsList = postActions.getPostByParamId(id);
        
        assertThat(postsList, everyItem(hasProperty("id", is(equalTo(id)))));
    }

    @Test(dataProvider = "postId")
    public void getPostByQueryIdTest(Integer id) {
        Post post = postActions.getPostByQueryId(id);
        
        assertThat(post.getId(), equalTo(id));
        assertThat(post.getTitle(), is(not(emptyString())));
        assertThat(post.getBody(), is(not(emptyString())));
    }
}
