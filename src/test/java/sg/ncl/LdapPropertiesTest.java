package sg.ncl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dcsacr on 27-Jan-21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LdapPropertiesTest.TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
        "ncl.web.ldap.host=172.18.128.29",
        "ncl.web.ldap.port=389",
        "ncl.web.ldap.dn=dc=dev,dc=ncl,dc=sg",
        "ncl.web.ldap.credential=deterinavm",
        "ncl.web.ldap.gidnumber=5000"
})
public class LdapPropertiesTest {

    @Configuration
    @EnableConfigurationProperties(LdapProperties.class)
    static class TestConfig {
    }

    @Inject
    private LdapProperties properties;

    @Test
    public void testLdapHost() throws Exception {
        assertThat(properties.getHost()).isEqualTo("172.18.128.29");
    }

    @Test
    public void testLdapPort() throws Exception {
        assertThat(properties.getPort()).isEqualTo("389");
    }

    @Test
    public void testLdapDn() throws Exception {
        assertThat(properties.getDn()).isEqualTo("dc=dev,dc=ncl,dc=sg");
    }

    @Test
    public void testLdapCredential() throws Exception {
        assertThat(properties.getCredential()).isEqualTo("deterinavm");
    }

    @Test
    public void testLdapGid() throws Exception {
        assertThat(properties.getGidnumber()).isEqualTo("5000");
    }

}
