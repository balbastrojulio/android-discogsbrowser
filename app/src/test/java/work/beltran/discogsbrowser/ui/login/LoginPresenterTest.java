package work.beltran.discogsbrowser.ui.login;

import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import rx.Observable;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricTestRunner.class)
public class LoginPresenterTest {
    LoginPresenter presenter;
    LoginView view;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter();
        presenter.service = mock(DiscogsService.class);
        presenter.settings = mock(Settings.class);
        view = mock(LoginView.class);
        presenter.setView(view);
    }

    @Test
    public void testOnClickLogin() throws Exception {
        final String body = "oauth_token=\"1234\"&oauth_token_secret=\"5678\"";
        ResponseBody response = ResponseBody.create(MediaType.parse("application/type"), body);
        when(presenter.service.requestToken(anyString())).thenReturn(Observable.<ResponseBody>just((ResponseBody) response));

        presenter.loginOnClick();

        Uri uri = Uri.parse("https://discogs.com/oauth/authorize?oauth_token=\"1234\"&oauth_token_secret=\"5678\"");
        ArgumentCaptor<Intent> actualIntent = ArgumentCaptor.forClass(Intent.class);
        verify(view).startActivity(actualIntent.capture());
        assertThat(actualIntent.getValue().getAction()).matches(Intent.ACTION_VIEW);
        assertThat(actualIntent.getValue().getData()).isEqualTo(uri);
        verify(presenter.settings).storeUserToken("\"1234\"");
        verify(presenter.settings).storeUserSecret("\"5678\"");
    }
}
