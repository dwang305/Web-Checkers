package com.webcheckers.ui;

import com.webcheckers.appl.CurrentGames;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.ui.GetSignoutRoute;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * file: checkers-app
 * language: java
 * author: erl3193@rit.edu Emily Lederman
 * description:
 */
public class GetSignoutRouteTest {
    /**
     * The component-under-test (CuT).
     */
    private GetSignoutRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    /**
     * Setup new mock objects for each test.
     */
    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        playerLobby = mock(PlayerLobby.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);
        //when(playerLobby.getNumOfUsers()).thenReturn("2");
        //when(playerLobby.getUserList()).thenReturn(null);

        // create a unique CuT for each test
        CuT = new GetSignoutRoute(engine, playerLobby);
    }

    /**
     * Test that the Game view will sign out of a game successfully.
     * */
    @Test
    public void signout_Success() throws Exception {
        final Response response = mock(Response.class);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        // Invoke the test
        CuT.handle(request, response);

        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);
        //check that model data is correct.
        @SuppressWarnings("unchecked")
        final Map<String, Object> vm = (Map<String, Object>) model;
        //assertEquals("1", vm.get(GetSignoutRoute.PLAYERS_ONLINE_ATTR));
        assertEquals(null, vm.get(GetSignoutRoute.CURR_PLAYER));
    }

}
