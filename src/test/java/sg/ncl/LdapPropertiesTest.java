package sg.ncl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.naming.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dcsacr on 27-Jan-21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LdapPropertiesTest.TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
        "ncl.web.ldap.host=172.18.178.29",
        "ncl.web.ldap.port=389",
        "ncl.web.ldap.dn=dc=dev,dc=ncl,dc=sg",
        "ncl.web.ldap.credential=deterinavm1",
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
        assertThat(properties.getHost()).isEqualTo("172.18.178.29");
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
        assertThat(properties.getCredential()).isEqualTo("deterinavm1");
    }

    @Test
    public void testLdapGid() throws Exception {
        assertThat(properties.getGidnumber()).isEqualTo("5000");
    }

    @Test
    public void testGetUser() throws Exception {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://" + properties.getHost() + ":" + properties.getPort());
        contextSource.setBase("");
        contextSource.setUserDn("cn=admin," + properties.getDn());
        contextSource.setPassword(properties.getCredential());
        LdapTemplate template = new LdapTemplate();
        template.setContextSource(contextSource);

        try {
            contextSource.afterPropertiesSet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<String> user = template.search(
                "dc=dev,dc=ncl,dc=sg",
                "uid=" + "hk",
                (AttributesMapper<String>) attrs ->
                        (String) attrs.get("cn").get());
        System.out.println(user);
    }

    @Test
    public void testCreateUser() throws Exception {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://" + properties.getHost() + ":" + properties.getPort());
        contextSource.setBase("");
        contextSource.setUserDn("cn=admin," + properties.getDn());
        contextSource.setPassword(properties.getCredential());
        LdapTemplate template = new LdapTemplate();
        template.setContextSource(contextSource);

        try {
            contextSource.afterPropertiesSet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String testUser = "hk";
        String password = "hk123";

        Name dn = LdapNameBuilder.newInstance
                ("uid=" + testUser + ",ou=users," + properties.getDn()).build();
        DirContextAdapter context = new DirContextAdapter(dn);

        context.setAttributeValues(
                "objectclass",
                new String[]
                        { "top",
                                "inetOrgPerson",
                                "posixAccount",
                                "shadowAccount" });
        context.setAttributeValue("cn", testUser);
        context.setAttributeValue("sn", testUser);
        context.setAttributeValue("uid", testUser);
        context.setAttributeValue("uidNumber", "10001");
        context.setAttributeValue("gidNumber", properties.getGidnumber());
        context.setAttributeValue("gecos", testUser);
        context.setAttributeValue("homeDirectory", "/home/"+testUser);
        context.setAttributeValue("loginShell", "/bin/bash");
        context.setAttributeValue
                ("userPassword", password);
        System.out.println(template.toString());
        System.out.println(context);
        template.bind(context);
    }
}

