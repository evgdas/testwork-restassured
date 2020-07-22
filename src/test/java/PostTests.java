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
        for (Post post : posts) {
            assertThat(post.getUserId(), is(notNullValue()));
            assertThat(post.getId(), is(notNullValue()));
            assertThat(post.getTitle(), is(not(emptyString())));
            assertThat(post.getBody(), is(not(emptyString())));
        }
    }

    @Test(dataProvider = "postId")
    public void getPostsByParamIdTest(Integer id) {
        List<Post> postList = postActions.getPostByParamId(id);
        for (Post post : postList) {
            assertThat(post.getId(), equalTo(id));
            assertThat(post.getTitle(), is(not(emptyString())));
            assertThat(post.getBody(), is(not(emptyString())));
        }
    }

    @Test(dataProvider = "postId")
    public void getPostByQueryIdTest(Integergit id) {
        Post post = postActions.getPostByQueryId(id);
        assertThat(post.getId(), equalTo(id));
        assertThat(post.getTitle(), is(not(emptyString())));
        assertThat(post.getBody(), is(not(emptyString())));
    }


}