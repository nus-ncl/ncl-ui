package sg.ncl.testbed_interface;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * @author Aris C. Risdianto
 */
public class GpuUserFormTest {

    @Test
    public void testUsername() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        assertThat(gpuUserForm.getUsername(), is(nullValue()));
    }

    @Test
    public void setTestUsername() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        final String str = RandomStringUtils.randomAlphanumeric(20);
        gpuUserForm.setUsername(str);
        assertThat(gpuUserForm.getUsername(), is(str));
    }

    @Test
    public void testFullname() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        assertThat(gpuUserForm.getFullname(), is(nullValue()));
    }

    @Test
    public void setTestFullname() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        final String str = RandomStringUtils.randomAlphanumeric(20);
        gpuUserForm.setFullname(str);
        assertThat(gpuUserForm.getFullname(), is(str));
    }

    @Test
    public void testPassword() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        assertThat(gpuUserForm.getPassword(), is(nullValue()));
    }

    @Test
    public void setTestPassword() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        final String str = RandomStringUtils.randomAlphanumeric(20);
        gpuUserForm.setPassword(str);
        assertThat(gpuUserForm.getPassword(), is(str));
    }

    @Test
    public void testConfirmPassword() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        assertThat(gpuUserForm.getPassword(), is(nullValue()));
    }

    @Test
    public void setTestConfirmPassword() {
        final GpuUserForm gpuUserForm = new GpuUserForm();
        final String str = RandomStringUtils.randomAlphanumeric(20);
        gpuUserForm.setConfirmPassword(str);
        assertThat(gpuUserForm.getConfirmPassword(), is(str));
    }
}
