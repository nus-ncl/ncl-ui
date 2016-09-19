package sg.ncl;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import sg.ncl.domain.UserType;
import sg.ncl.testbed_interface.Team2;

import javax.inject.Inject;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * @author Te Ye
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class MainControllerTest {

//    @Bean
//    RestTemplate restTemplate() {
//        return Mockito.mock(RestTemplate.class);
//    }

    @Inject
    MainController mainController;

    private MockMvc mockMvc;
//    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    @Inject
    private RestOperations restOperations;

    @Inject
    private ConnectionProperties properties;

    @Inject
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
//        restTemplate = Mockito.mock(RestTemplate.class);
        mockServer = MockRestServiceServer.createServer((RestTemplate) restOperations);
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    //--------------------------------------
    // Test before login HTML pages
    //--------------------------------------
    @Test
    public void testIndexPage() throws Exception {
        // ensure page can load <head>, navigation, <body>, <footer>
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("NATIONAL CYBERSECURITY R&amp;D LAB")))
                .andExpect(content().string(containsString("Features")))
                .andExpect(content().string(containsString("Focus on your")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testOverviewPage() throws Exception {
        mockMvc.perform(get("/overview"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("id=\"joinUs\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testCommunityPage() throws Exception {
        mockMvc.perform(get("/community"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testAboutPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("id=\"about-us\"")))
                .andExpect(content().string(containsString("id=\"joinUs\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testEventPage() throws Exception {
        mockMvc.perform(get("/event"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("div id=\"portfolioSlider\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testPlanPage() throws Exception {
        mockMvc.perform(get("/plan"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testFuturePlanPage() throws Exception {
        mockMvc.perform(get("/futureplan"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("a href=\"/futureplan/download\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testPricingPage() throws Exception {
        mockMvc.perform(get("/pricing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("Pricing")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testResourcesPage() throws Exception {
        mockMvc.perform(get("/resources"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testResearchPage() throws Exception {
        mockMvc.perform(get("/research"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("Research")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testCalendarPage() throws Exception {
        // calendar page display BEFORE login
        mockMvc.perform(get("/calendar"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("iframe src=\"https://calendar.google.com/calendar/embed")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testCalendar1Page() throws Exception {
        // calendar page display AFTER login
        // navigation bar is different so need another html file
        mockMvc.perform(get("/calendar1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("href=\"/teams\""))) // ensure this is indeed the after login navigation bar
                .andExpect(content().string(containsString("iframe src=\"https://calendar.google.com/calendar/embed")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testContactUsPage() throws Exception {
        // calendar page display BEFORE login
        mockMvc.perform(get("/contactus"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("Contact Us")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testToolsPage() throws Exception {
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("navbar-header")))
                .andExpect(content().string(containsString("Tools")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("/login")))
                .andExpect(content().string(containsString("/signup2")))
                .andExpect(content().string(containsString("form method=\"post\" action=\"/login\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")));
    }

    @Test
    public void testPostLoginPageInvalidUserPassword() throws Exception {
        mockServer.expect(requestTo(properties.getSioAuthUrl()))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withBadRequest().body("{\"name\":\"sg.ncl.service.authentication.exceptions.InvalidCredentialsException\"}").contentType(MediaType.APPLICATION_JSON));

        ResultActions perform = mockMvc.perform(
                post("/login")
                        .param("loginEmail", "123456789@nus.edu.sg")
                        .param("loginPassword", "123456789")
        );

        perform
                .andExpect(view().name("login"));
        perform
                .andExpect(model().attributeExists("loginForm"));
    }

    @Test
    public void testGetSignUpPage() throws Exception {
        mockMvc.perform(get("/signup2"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup2"))
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("/login")))
                .andExpect(content().string(containsString("/signup2")))
                .andExpect(content().string(containsString("form action=\"/signup2\" method=\"post\" role=\"form\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")))
                .andExpect(model().attributeExists("signUpMergedForm"));
    }

    @Test
    public void testRedirectNotFoundNotLoggedOn() throws Exception {
        mockMvc.perform(get("/notfound"))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRedirectNotFoundLoggedOn() throws Exception {
        final String id = RandomStringUtils.randomAlphabetic(10);
        mockMvc.perform(get("/notfound").sessionAttr("id", id))
                .andExpect(redirectedUrl("/dashboard"));
    }

    //--------------------------------------
    // Test after login HTML pages
    //--------------------------------------
    @Test
    public void testGetDashboardPage() throws Exception {

        final String id = RandomStringUtils.randomAlphabetic(10);

        mockServer.expect(requestTo(properties.getDeterUid(id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(id, MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/dashboard").sessionAttr("id", id))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("/teams")))
                .andExpect(content().string(containsString("/experiments")))
                .andExpect(content().string(containsString("/calendar1")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/account_settings")))
                .andExpect(content().string(containsString("/logout")))
                .andExpect(content().string(containsString("Dashboard")))
                .andExpect(content().string(containsString("footer id=\"footer\"")))
                .andExpect(model().attribute("deterUid", is(id)));
    }

    @Test
    public void testGetDashboardPageWithAdmin() throws Exception {
        final String id = RandomStringUtils.randomAlphabetic(10);

        mockServer.expect(requestTo(properties.getDeterUid(id)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(id, MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/dashboard").sessionAttr("id", id).sessionAttr("roles", UserType.ADMIN.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("/teams")))
                .andExpect(content().string(containsString("/experiments")))
                .andExpect(content().string(containsString("/admin")))
                .andExpect(content().string(containsString("/calendar1")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/account_settings")))
                .andExpect(content().string(containsString("/logout")))
                .andExpect(content().string(containsString("Dashboard")))
                .andExpect(content().string(containsString("footer id=\"footer\"")))
                .andExpect(model().attribute("deterUid", is(id)));
    }

    @Test
    public void getUserProfileTest() throws Exception {

        JSONObject predefinedUserJson = Util.createUserJson();
        String predefinedJsonStr = predefinedUserJson.toString();

        // uri must be equal to that defined in MainController
        mockServer.expect(requestTo(properties.getSioUsersUrl() + "null"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(predefinedJsonStr, MediaType.APPLICATION_JSON));

        MvcResult result = mockMvc.perform(get("/account_settings"))
                .andExpect(status().isOk())
                .andExpect(view().name("account_settings"))
                .andExpect(model().attribute("editUser", hasProperty("id", is(predefinedUserJson.getString("id")))))
                .andReturn();
    }

    @Test
    public void updateUserProfileTest() throws Exception {
        // TODO to be completed

        // update the phone to test main json
        // update the lastname to test user details json
        // update the address2 to test address json

        JSONObject predefinedUserJson = Util.createUserJson();
        final String id = RandomStringUtils.randomAlphabetic(10);
        String predefinedJsonStr = predefinedUserJson.toString();

        mockServer.expect(requestTo(properties.getSioUsersUrl() + id))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess(predefinedJsonStr, MediaType.APPLICATION_JSON));

        MvcResult result = mockMvc.perform(
                post("/account_settings")
                        .param("email", "apple@nus.edu.sg")
                        .param("firstName", "apple")
                        .param("lastName", "edited")
                        .param("phone", "12345678")
                        .param("jobTitle", "research")
                        .param("institution", "national university")
                        .param("institutionAbbreviation", "nus")
                        .param("institutionWeb", "")
                        .param("address1", "address1")
                        .param("address2", "edited")
                        .param("country", "singapore")
                        .param("city", "sg")
                        .param("province", "west")
                        .param("postalCode", "123456"))
                        .andExpect(redirectedUrl("/account_settings"))
                        .andReturn();
    }

    @Test
    public void signUpNewUserApplyNewTeam() throws Exception {

        String stubUid = "AAAAAA";
        JSONObject predefinedResultJson = new JSONObject();
        predefinedResultJson.put("msg", "user is created");
        predefinedResultJson.put("uid", stubUid);

        mockServer.expect(requestTo(properties.getSioRegUrl()))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(predefinedResultJson.toString(), MediaType.APPLICATION_JSON));

        MvcResult result = mockMvc.perform(
                post("/signup2")
                        .param("email", "apple@nus.edu.sg")
                        .param("password", "appleP@ssword")
                        .param("confirmPassword", "appleP@ssword")
                        .param("firstName", "apple")
                        .param("lastName", "orange")
                        .param("phone", "12345678")
                        .param("jobTitle", "research")
                        .param("institution", "national university")
                        .param("institutionAbbreviation", "nus")
                        .param("website", "http://www.nus.edu.sg")
                        .param("address1", "address1")
                        .param("address2", "address2")
                        .param("country", "singapore")
                        .param("city", "sg")
                        .param("province", "west")
                        .param("postalCode", "123456")
                        .param("teamName", "project")
                        .param("teamDescription", "a simple description")
                        .param("teamWebsite", "http://team.com")
                        .param("organizationType", "academia")
                        .param("isPublic", "PUBLIC")
                        .param("hasAcceptTeamOwnerPolicy", "true"))
                .andExpect(redirectedUrl("/team_application_submitted"))
                .andReturn();
    }

    @Test
    @Ignore
    public void signUpNewUserJoinExistingTeam() throws Exception {
        // TODO TBD need a way to mock two rest operations
        String stubUid = "AAAAAA";
        JSONObject predefinedResultJson = new JSONObject();
        predefinedResultJson.put("msg", "user is created");
        predefinedResultJson.put("uid", stubUid);

        JSONObject predefinedTeamJson = new JSONObject();
        predefinedTeamJson.put("id", "123456789");

        MvcResult result = mockMvc.perform(
                post("/signup2")
                        .param("email", "apple@nus.edu.sg")
                        .param("password", "appleP@ssword")
                        .param("confirmPassword", "appleP@ssword")
                        .param("firstName", "apple")
                        .param("lastName", "orange")
                        .param("phone", "12345678")
                        .param("jobTitle", "research")
                        .param("institution", "national university")
                        .param("institutionAbbreviation", "nus")
                        .param("website", "http://www.nus.edu.sg")
                        .param("address1", "address1")
                        .param("address2", "address2")
                        .param("country", "singapore")
                        .param("city", "sg")
                        .param("province", "west")
                        .param("postalCode", "123456")
                        .param("joinTeamName", "project")
                        .param("hasAcceptTeamOwnerPolicy", "true"))
                .andExpect(redirectedUrl("/join_application_submitted"))
                .andReturn();

//        mockServer.expect(requestTo(properties.getTEAMS_URI() + "name/" + "project"))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withSuccess(predefinedTeamJson.toString(), MediaType.APPLICATION_JSON));
//
//        mockServer.expect(requestTo(properties.getREGISTRATION_URI()))
//                .andExpect(method(HttpMethod.POST))
//                .andRespond(withSuccess(predefinedResultJson.toString(), MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetJoinTeamPageFromTeamPage() throws Exception {
        mockMvc.perform(get("/teams/join_team"))
                .andExpect(status().isOk())
                .andExpect(view().name("team_page_join_team"))
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("/teams")))
                .andExpect(content().string(containsString("/experiments")))
                .andExpect(content().string(containsString("/calendar1")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/account_settings")))
                .andExpect(content().string(containsString("/logout")))
                .andExpect(content().string(containsString("method=\"post\" action=\"/teams/join_team\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")))
                .andExpect(model().attribute("teamPageJoinTeamForm", hasProperty("teamName")));
    }

    @Test
    public void testGetApplyNewTeamPageFromTeamPage() throws Exception {
        mockMvc.perform(get("/teams/apply_team"))
                .andExpect(status().isOk())
                .andExpect(view().name("team_page_apply_team"))
                .andExpect(content().string(containsString("main.css")))
                .andExpect(content().string(containsString("main.js")))
                .andExpect(content().string(containsString("/teams")))
                .andExpect(content().string(containsString("/experiments")))
                .andExpect(content().string(containsString("/calendar1")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/approve_new_user")))
                .andExpect(content().string(containsString("/account_settings")))
                .andExpect(content().string(containsString("/logout")))
                .andExpect(content().string(containsString("method=\"post\" action=\"/teams/apply_team\"")))
                .andExpect(content().string(containsString("footer id=\"footer\"")))
                .andExpect(model().attribute("teamPageApplyTeamForm", hasProperty("teamName")));
    }
}