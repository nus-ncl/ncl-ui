package sg.ncl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@ConfigurationProperties(prefix = LdapProperties.PREFIX)
@Getter
@Setter
public class LdapProperties {

    public static final String PREFIX = "ncl.web.ldap";

    private String host;
    private String port;
    private String dn;
    private String credential;
    private String gidnumber;


    public LdapTemplate getTemplate() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://" + host + ":" + port);
        contextSource.setBase("");
        contextSource.setUserDn("cn=admin," + dn);
        contextSource.setPassword(credential);

        try {
            contextSource.afterPropertiesSet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LdapTemplate template = new LdapTemplate();
        template.setContextSource(contextSource);
        return template;
    }

    public String getHost(){ return host; }

    public String getPort(){ return port; }

    public String getDn(){return dn; }

    public String getCredential(){return dn; }

    public String getGidnumber(){return gidnumber; }

}
